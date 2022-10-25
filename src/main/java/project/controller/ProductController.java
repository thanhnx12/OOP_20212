package project.controller;

import project.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.Manager.ProductManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    static ObservableList<Product> productListController=FXCollections.observableArrayList();

    //TableView
    @FXML
    TableView<Product> table;
    @FXML
    private TableColumn<Product,String> idColumn;
    @FXML
    private TableColumn<Product,String> nameColumn;
    @FXML
    private TableColumn<Product,Integer> priceColumn;
    @FXML
    private TableColumn<Product,String> categoryColumn;
    @FXML
    private TableColumn<Product,Integer> countColumn;
    @FXML
    private TableColumn<Product, Integer> importPriceColumn;
    @FXML private TableColumn<Product, String> statusColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        importPriceColumn.setCellValueFactory(new PropertyValueFactory<Product,Integer>("importPrice"));
        countColumn.setCellValueFactory(new PropertyValueFactory<Product,Integer>("count"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("status"));
        productListController.clear();
        table.setItems(productListController);
        productListController.addAll(ProductManager.productList);
        ProductController2.cloneTable=table;
    }
    static int noScene=0;
    //noScene=0: chi tiết
    //noScene=1: thêm sản phẩm
    //noScene=2: sửa sản phẩm
    static Product curentProduct=null;
    public void detail(ActionEvent e){
        noScene=0;
        curentProduct = table.getSelectionModel().getSelectedItem();
        Stage stage2 = new Stage();
        try{
            Parent root2=FXMLLoader.load(getClass().getResource("ProductController2.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.setTitle("Chi tiết sản phẩm");
            stage2.show();
        }catch (Exception ex){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Chọn một sản phẩm");
            alert.setHeaderText("Chưa chọn sản phẩm");
            alert.show();
        }
    }

    public void add(ActionEvent e){
        noScene=1;
        try{
            Stage stage2 = new Stage();
            Parent root2=FXMLLoader.load(getClass().getResource("ProductController2.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.setTitle("Thêm sản phẩm");
            stage2.show();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
//    public void delete(ActionEvent e){
//        Product selected = table.getSelectionModel().getSelectedItem();
//        productListController.remove(selected);
//        ProductManagerDAO.delete(selected);
//        ProductManager.delete(selected);
//    }
    public void fix(ActionEvent e){
        noScene=2;
        curentProduct = table.getSelectionModel().getSelectedItem();
        Stage stage2 = new Stage();
        try{
            Parent root2=FXMLLoader.load(getClass().getResource("ProductController2.fxml"));
            stage2.setScene(new Scene(root2));
            stage2.initModality(Modality.APPLICATION_MODAL);
            stage2.setTitle("Thay đổi sản phẩm");
            stage2.show();
        }catch (Exception ex){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Chọn một sản phẩm");
            alert.setHeaderText("Chưa chọn sản phẩm");
            alert.show();
        }
    }
    @FXML TextField idTextField;
    @FXML TextField nameTextField;
    public void filt1(){
        productListController.clear();
        String id=idTextField.getText();
        String name=nameTextField.getText();
        for (Product product : ProductManager.productList)
            if (product.getName().contains(name) && product.getId().contains(id))
                productListController.add(product);
    }
    public void filt2(){
        productListController.clear();
        String id=idTextField.getText();
        String name=nameTextField.getText();
        for (Product product : ProductManager.productList)
            if (product.getId().contains(id) && product.getName().contains(name))
                productListController.add(product);
    }
}
