package project.controller;

//import project.model.SoldProduct;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Manager.BillManager;
import project.model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BillController implements Initializable {

    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableView<SoldProduct> soldProductTable;
    @FXML
    private TableColumn<Bill,String> idColumn;

    @FXML
    private TableColumn<Bill, String> customerColumn;

    @FXML
    private TableColumn<Bill,String> employeeColumn;

    @FXML
    private TableColumn<Bill,String> dateColumn;

    @FXML
    private TableColumn<Bill,Integer> totalPayoutColumn;

    private ObservableList<Bill> billObservableList= FXCollections.observableArrayList();
    private ObservableList<SoldProduct> soldProductObservableList = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle resourceBundle) {
        billObservableList.addAll(BillManager.billList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("id"));
        customerColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCustomer().getName()));
        employeeColumn.setCellValueFactory(cd->new SimpleStringProperty(cd.getValue().getEmployee().getName()));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Bill,String>("date"));
        totalPayoutColumn.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("totalPayout"));
        billTable.setItems(billObservableList);
    }
    @FXML
    private TableColumn<SoldProduct,String> idProductColumn;

    @FXML
    private TableColumn<SoldProduct,String> nameProductColumn;

    @FXML
    private TableColumn<SoldProduct,Integer> priceColumn;

    @FXML
    private TableColumn<SoldProduct,Integer> countColumn;

    @FXML
    private TableColumn<SoldProduct,Integer> moneyColumn;
    public void detail(){
        Order selected = billTable.getSelectionModel().getSelectedItem().getOrder();
        soldProductObservableList.clear();
        soldProductObservableList.addAll(selected.getList());
        idProductColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getProduct().getId()));
        nameProductColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getProduct().getName()));
        priceColumn.setCellValueFactory(new PropertyValueFactory<SoldProduct,Integer>("price"));
        countColumn.setCellValueFactory(new PropertyValueFactory<SoldProduct,Integer>("count"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<SoldProduct,Integer>("money"));

        soldProductTable.setItems(soldProductObservableList);
    }
}
