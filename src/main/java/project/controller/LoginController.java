package project.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import project.Manager.EmployeeManager;
import project.jdbcutil.JDBCUtil;
import project.model.Employee;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement sta = null;
        try {
            sta = conn.prepareStatement("update employee set login = 'false' ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            sta.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField UserID;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {


        Window owner = submitButton.getScene().getWindow();

        if (UserID.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String username = UserID.getText();
        String password = passwordField.getText();

        boolean check = false;
        for(Employee x : EmployeeManager.employeeList){
            if(x.getId().equals(username) && x.getPassword().equals(password)){
                check = true;
                Connection con = JDBCUtil.getConnection();
                PreparedStatement st = con.prepareStatement("update employee set login = 'true' where id =  ?");
                st.setString(1, x.getId());
                Main.employee= EmployeeManager.get(x.getId());
                st.executeUpdate();
                break;
            }
        }
        if(check){
            // Chuyển cảnh
            Node source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(this.getClass().getResource("MainController.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("One Media");

            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông báo:");
            alert.setContentText("Thôn tin đăng nhập không chính xác !");
            alert.showAndWait();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}