package project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Manager.CustomerManager;
import project.model.Customer;

import javax.crypto.Cipher;
import java.net.URL;
import java.net.spi.InetAddressResolver;
import java.util.ResourceBundle;

public class ChooseCustomer implements Initializable {

    Customer customer = new Customer();
    @FXML
    private TableColumn<Customer, String> addressCol;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private TableColumn<Customer, String> emailCol;

    @FXML
    private TableColumn<Customer, String> genderCol;

    @FXML
    private TableColumn<Customer, String> idCol;

    @FXML
    private TableColumn<Customer, String> nameCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TextField searchText;

    @FXML
    private TableColumn<Customer, String> statusCol;

    @FXML
    private TableView<Customer> tableCustomer;

    @FXML
    void actHuy(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void actXacNhan(ActionEvent event) {
        customer = tableCustomer.getSelectionModel().getSelectedItem();
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
    ObservableList<Customer> customerListController= FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerListController.addAll(CustomerManager.customerList);
        idCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("status"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));

        tableCustomer.setItems(customerListController);
        CustomerController2.cloneTable= tableCustomer;

        //Wrap the ObservableList in a FilteredList
        FilteredList<Customer> filteredData = new FilteredList<>(customerListController, b->true);

        //Set the filter Predicate whenever the filter changes
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                if (newValue==null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                //Compare
                String lowerCaseFilter = newValue.toLowerCase();
                if (customer.getName().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;
                } else if (customer.getGender().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;
                } else if (customer.getAddress().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;
                } else if (customer.getPhone().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;
                } else if (customer.getStatus().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;
                } else if (customer.getEmail().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;
                } else if (String.valueOf(customer.getId()).indexOf(lowerCaseFilter) !=-1){
                    return true;
                } else
                    return false; //Does not match
            });
        });
        //Wrap the FilteredList in a SortedList.
        SortedList<Customer> sortedData= new SortedList<>(filteredData);
        //Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect
        sortedData.comparatorProperty().bind(tableCustomer.comparatorProperty());
        //Add sorted (and filtered) data to the table.
        tableCustomer.setItems(sortedData);
    }
    Customer inflateUI(){
        return customer;
    }

}
