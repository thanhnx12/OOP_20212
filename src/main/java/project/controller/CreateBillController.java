package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.Manager.BillManager;
import project.jdbcutil.JDBCUtil;
import project.model.Bill;
import project.model.Customer;
import project.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CreateBillController implements Initializable {


    @FXML
    private Button buttonXacNhan;

    @FXML
    private Button chonKH;

    @FXML
    private Label labelKH;

    @FXML
    private Label labelKH2;

    @FXML
    private Label labelMaHD;

    @FXML
    private Label labelMaHD2;

    @FXML
    private Label labelMaKH;

    @FXML
    private Label labelNgay;

    @FXML
    private Label labelNgay2;

    @FXML
    private Label labelTenKH;

    @FXML
    private Button themKH;

    @FXML
    private TextField txtMaKH;

    @FXML
    private TextField txtTenKH;
    @FXML
    private Label lblMaKH;

    @FXML
    private Label lblTenKH;

    Customer customer = new Customer();
    Bill bill = new Bill();

    @FXML
    void actionChonKH(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chooseCustomer.fxml"));
        Parent parent = fxmlLoader.load();
        ChooseCustomer controller = (ChooseCustomer) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("MediaOne");
        stage1.setScene(scene1);
        stage1.showAndWait();
        customer = controller.inflateUI();

        bill.setCustomer(customer);
        lblMaKH.setText(customer.getId());
        lblTenKH.setText(customer.getName());

    }

    @FXML
    void actionThemKH(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addCustomer.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("MediaOne");
        stage1.setScene(scene1);
        stage1.showAndWait();
    }

    @FXML
    void actionXacNhan(ActionEvent event) {
        if(bill.getCustomer().getId() != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông báo:");
            alert.setContentText("Tạo hóa đơn  thành công !");
            alert.showAndWait();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông báo:");
            alert.setContentText(" Thông tin không chính xác!");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int id = BillManager.billList.size() + 1;
        bill.setId(id + ""); labelMaHD2.setText(id+"");
        bill.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        Connection con = JDBCUtil.getConnection();
        try {
            String sql1="Select * from employee where login ='true'";
            PreparedStatement getCount=con.prepareStatement(sql1,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=getCount.executeQuery();
            if (rs.absolute(1)) {
                Employee employee = new Employee();
                employee.setId(rs.getString("id"));
                employee.setName(rs.getString("name"));
                bill.setEmployee(employee);
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        labelNgay2.setText(bill.getDate().toString());
    }

    public Bill getBill(){
       return bill;
    }
}
