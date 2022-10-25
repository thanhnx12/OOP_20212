package project.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import project.Manager.CustomerManager;
import project.dao.CustomerManagerDAO;
import project.model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {


    public Customer customer = new Customer("NULL", "Hai", "Nam", "adsf", "0123123","Đồng","askdf@gmail.com","asdf", 0);
    @FXML
    private Button addButton;

    @FXML
    private TextArea addressText;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField emailText;

    @FXML
    private ComboBox<String> genderBox;

    @FXML
    private HBox hBox;

    @FXML
    private TextField idText;

    @FXML
    private TextArea informationTextArea;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;

    @FXML
    void add(ActionEvent event) {
       try{
           int id = CustomerManager.customerList.size() + 1;
           customer.setId(id + "");
           customer.setName(nameText.getText());
           customer.setGender(genderBox.getValue().toString());
           customer.setAddress(addressText.getText());
           customer.setPhone(phoneText.getText());
           customer.setStatus("Đồng");
           customer.setEmail(emailText.getText());
           customer.setInformation(informationTextArea.getText());
           customer.setTotalAmountPaid(0);
           CustomerManager.add(customer);
           CustomerManagerDAO.insert(customer);

           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Thông báo");
           alert.setHeaderText("Thông báo:");
           alert.setContentText("Thêm khách hàng thành công !");
           alert.showAndWait();
       }catch (Exception e){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Thông báo");
           alert.setHeaderText("Thông báo:");
           alert.setContentText("Thông tin thiếu !");
           alert.showAndWait();
       }

    }

    @FXML
    void cancel(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderBox.getItems().addAll("Nam", "Nữ");

    }
}
