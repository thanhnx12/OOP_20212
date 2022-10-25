package project.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    AnchorPane anchorPane;
    @FXML
    VBox vbox;
    @FXML Button banHangButton;
    @FXML Button hoaDonButton;
    @FXML Button sanPhamButton;
    @FXML Button nhanVienButton;
    @FXML Button khachHangButton;
    @FXML Button thongKeButton;
    @FXML Button thoatButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Main.employee.getRole().equals("Nhân viên")) {
            nhanVienButton.setDisable(true);
            thongKeButton.setDisable(true);
        }
        banHang();
    }

    public void banHang(){
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("Sell.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void sanPham(){
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("ProductController.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void hoaDon(){
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("BillController.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void nhanVien(){
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("EmployeeController.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void khachHang(){
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("CustomerController1.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void thongKe(){
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("Statistic.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void thoat(){
        Stage stage = (Stage)thoatButton.getScene().getWindow();
        stage.close();
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("One Media");
            primaryStage.show();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
