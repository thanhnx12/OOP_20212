package project.controller;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import project.Manager.BillManager;
import project.Manager.ProductManager;
import project.dao.BillManagerDAO;
import project.dao.CustomerManagerDAO;
import project.dao.ProductManagerDAO;
import project.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellController implements Initializable {


    @FXML
    private TableColumn<?, ?> TableColumnDonGia;

    @FXML
    private TableColumn<?, ?> TableColumnSoLuong;

    @FXML
    private TableColumn<?, ?> TableColumnSoLuongTon;

    @FXML
    private TableColumn<?, ?> TableColumnTenSanPham;

    @FXML
    private Button buttonHuyHoaDon;

    @FXML
    private Button buttonLamMoi;

    @FXML
    private Button buttonTao;

    @FXML
    private Button buttonThanhToan;

    @FXML
    private Button buttonTinhTien;

    @FXML
    private Button buttonXoaSP;

    @FXML
    private Button buttonXoaTatCA;

    @FXML
    private TableColumn<?, ?> tableColumnDonGia;

    @FXML
    private TableColumn<?, ?> tableColumnMaSanPham1;

    @FXML
    private TableColumn<?, ?> tableColumnMaSanPham2;

    @FXML
    private TableColumn<?, ?> tableColumnTenSanPham;

    @FXML
    private TableView<SoldProduct> tableGiohang;

    @FXML
    private TableView<Product> tableSanPham;

    @FXML
    private Text textDonHang;

    @FXML
    private TextField textFieldTimKiem;

    @FXML
    private Text textGiamGia;

    @FXML
    private Text textGiamGia1;

    @FXML
    private Text textGioHang;

    @FXML
    private Text textMaHoaDon;

    @FXML
    private Text textMaHoaDon1;

    @FXML
    private Text textThanhToan;

    @FXML
    private Text textThanhToan1;

    @FXML
    private Text textTimKiemSanPham;

    @FXML
    private Text textTongTien;

    @FXML
    private Text textTongTien1;

    @FXML
    private Text textvn3;

    @FXML
    private Text textvnd1;

    @FXML
    private Text textvnd2;

    ObservableList<Product> data = FXCollections.observableArrayList(); // list d??? li???u c???a b???ng s???n ph???m
    ObservableList<SoldProduct> data2 = FXCollections.observableArrayList(); // list d??? li???u c???a b???ng gi??? h??ng

    private Bill bill = new Bill();
    int Tongtien,tienThanhToan;
    @FXML
    void actionTinhTien(ActionEvent event) {

        try{
            Tongtien = 0; tienThanhToan = 0;
            ArrayList<SoldProduct> list = new ArrayList<>();
            list.addAll(data2);
            Order order = new Order();
            order.setList(list);
            Tongtien = order.totalPrice();
            tienThanhToan = Tongtien/100 * (100 - bill.getCustomer().getDiscount()) ;

            textTongTien1.setText((int)Tongtien + "");
            textThanhToan1.setText((int)tienThanhToan + "");
        }catch (Exception e){

        }

    }

    @FXML
    void buttonActionHuyHoaDon(ActionEvent event) {
        bill = null;
        textMaHoaDon1.setText("Ch??a t???o");
        textGiamGia1.setText("0");
        textTongTien1.setText("0");
        textThanhToan1.setText("0");
    }

    @FXML
    void buttonActionLamMoi(ActionEvent event) throws SQLException {
        readDataFromDB();
        data2.clear();
        bill = null;
        textMaHoaDon1.setText("Ch??a t???o");
        textGiamGia1.setText("0");
        textTongTien1.setText("0");
        textThanhToan1.setText("0");
    }

    @FXML
    void buttonActionTao(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CreateBill.fxml"));
        Parent parent = fxmlLoader.load();
        CreateBillController controller = (CreateBillController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("MediaOne");
        stage1.setScene(scene1);
        stage1.showAndWait();
        bill = controller.getBill();

        textMaHoaDon1.setText(bill.getId());
        textGiamGia1.setText(bill.getCustomer().getDiscount() + "");

    }

    @FXML
    void buttonActionThanhToan(ActionEvent event) throws SQLException {
        if (!textMaHoaDon1.getText().equals("0") && !textThanhToan1.getText().equals("0")) {
            // c???p nh???t ti???n v??o bill v?? l??u v??o db
            bill.setTotalPayout(tienThanhToan);
            ArrayList<SoldProduct> list = new ArrayList<>();
            list.addAll(data2);
            bill.setOrder(new Order(list));
            BillManager.add(bill);
            BillManagerDAO.insert(bill);
            Customer customer= bill.getCustomer();
            customer.setTotalAmountPaid(customer.getTotalAmountPaid()+bill.getTotalPayout());
            CustomerManagerDAO.update(customer);

            System.out.println(customer);
            // c???p nh???t s??? l?????ng c??c s???n ph???m trong kho
            for (SoldProduct sp : list) {
                String id = sp.getIdProduct();
                Product pr = ProductManager.get(id);
                pr.setCount(pr.getCount() - sp.getCount());
                ProductManagerDAO.update(pr);
                data.clear();
                data.addAll(ProductManager.productList);
                tableSanPham.setItems(data);
                tableSanPham.refresh();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Th??ng b??o");
            alert.setHeaderText("Th??ng b??o:");
            alert.setContentText("Thanh to??n th??nh c??ng !");
            alert.showAndWait();
            bill = null;
            textMaHoaDon1.setText("Ch??a t???o");
            textGiamGia1.setText("0");
            textTongTien1.setText("0");
            textThanhToan1.setText("0");
            data2.clear();
        }
    }

    @FXML
    void buttonActionXoaSP(ActionEvent event) {
        SoldProduct selected = tableGiohang.getSelectionModel().getSelectedItem();
        data2.remove(selected);
    }

    @FXML
    void buttonActionXoaTatCa(ActionEvent event) {
        data2.clear();
    }

    @FXML
    void clickAction1(MouseEvent event) {

    }
    @FXML
    void clickAction2(MouseEvent event) {
        if(event.getClickCount() == 2){ // Checkint double click

            int index = tableSanPham.getSelectionModel().getSelectedIndex();
            // x??? l?? cho tr?????ng h???p s???n ph???m n??y ???? ???????c ch???n tr?????c ????
            for(SoldProduct x : data2){
                if(x.getProduct().getId().equals(tableSanPham.getSelectionModel().getSelectedItem().getId())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Th??ng b??o");
                    alert.setHeaderText("Th??ng b??o:");
                    alert.setContentText("???? ch???n s???n ph???m n??y r???i , n???u mu???n ch???n l???i vui l??ng x??a ??i ??? gi??? h??ng");

                    alert.showAndWait();
                    return;
                }
            }


            // Hi???n ra stage nh???p s??? l?????ng s???n ph???m
            Stage stage1 = new Stage();
            TextField num = new TextField("Nh???p s??? l?????ng");
            num.setMinWidth(120);

            FlowPane root = new FlowPane();
            root.setPadding(new Insets(10));

            Scene scene = new Scene(root, 300, 100);

            //button OK
            Button buttonOK = new Button("OK");
            buttonOK.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(isInteger(num.getText())){ // N???u nh???p v??o l?? s???
                        int sl = Integer.parseInt(num.getText());
                        if(sl >= 1  && sl <= tableSanPham.getSelectionModel().getSelectedItem().getCount()) {// s??? l?????ng >= 1  v?? nh??? h??n s??? l?????ng trong kho
                            SoldProduct p = new SoldProduct(tableSanPham.getSelectionModel().getSelectedItem(), sl, tableSanPham.getSelectionModel().getSelectedItem().getPrice());
                            data2.add(p);
                            tableGiohang.setItems(data2);
                            stage1.close();
                        }else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Th??ng b??o");
                            alert.setHeaderText("Th??ng b??o:");
                            alert.setContentText("S??? l?????ng kh??ng h???p l??? !");
                            alert.showAndWait();
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Th??ng b??o");
                        alert.setHeaderText("Th??ng b??o:");
                        alert.setContentText("S??? l?????ng kh??ng h???p l??? !");

                        alert.showAndWait();
                    }
                }
            });

            //button H???y
            Button buttonHuy = new Button("H???y");
            buttonHuy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stage1.close();
                }
            });

            root.getChildren().addAll(num, buttonHuy,buttonOK);
            stage1.setTitle("Nh???p s??? l?????ng SP");
            stage1.setScene(scene);
            stage1.showAndWait();

        }
    }

    @FXML
    void textFieldActionTimKiemSanPham(ActionEvent event) {
        FilteredList<Product> filteredData = new FilteredList<>(data, b->true);

        textFieldTimKiem.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(Product->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if(Product.getId().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (Product.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                }else if(Product.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;
            });
        }));
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableSanPham.comparatorProperty());
        tableSanPham.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();
        try {
            readDataFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCellTable(){
        // Set ki???u d??? li???u cho c??c c???t c???a 2 table
        tableColumnMaSanPham2.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnTenSanPham.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnDonGia.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumnSoLuongTon.setCellValueFactory(new PropertyValueFactory<>("count"));

        tableColumnMaSanPham1.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        TableColumnTenSanPham.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        TableColumnDonGia.setCellValueFactory(new PropertyValueFactory<>("priceProduct"));
        TableColumnSoLuong.setCellValueFactory(new PropertyValueFactory<>("count"));
    }
    public void readDataFromDB() throws SQLException {
        // ?????c d??? li???u t??? Database v??o TableView S???n ph???m

        // data.clear();
        data = FXCollections.observableArrayList();
        data.addAll(ProductManager.productList);
        tableSanPham.setItems(data);
    }

    public static boolean isInteger(String s){
        //H??m ki???m tra xem m???t x??u c?? l?? s??? nguy??n kh??ng
        try {
            int iVal = Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}
