package project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.dao.CustomerManagerDAO;
import project.model.Customer;
import project.Manager.CustomerManager;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController1 implements  Initializable{

    @FXML
    TableView<Customer> tableCustomer;
    @FXML
    TableColumn<Customer, Integer> idCol;
    @FXML
    TableColumn<Customer, String> nameCol;

    @FXML
    TableColumn<Customer, String> genderCol;

    @FXML
    TableColumn<Customer, String> addressCol;

    @FXML
    TableColumn<Customer, String> phoneCol;

    @FXML
    TableColumn<Customer, String> statusCol;

    @FXML
    TableColumn<Customer, String> emailCol;
    @FXML
    TextField searchText;

    static ObservableList<Customer> customerListController= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerListController.clear();
        customerListController.addAll(CustomerManager.customerList);
        idCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
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
        static int noscene;
        // noscene=2 sua
        // noscene=0 chuyen canh sua thong tin
        // noscene=1 them
        public void add(ActionEvent e){
            noscene=1;
            try{
                Stage stage2 = new Stage();
                Parent root2= FXMLLoader.load(getClass().getResource("CustomerController2.fxml"));
                stage2.setScene(new Scene(root2));
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.setTitle("Thêm khách hàng");
                stage2.show();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }

        static Customer currentCustomer;
        public void change(ActionEvent e) {
            noscene=2;
            currentCustomer = tableCustomer.getSelectionModel().getSelectedItem();

            try{
                Stage stage2 = new Stage();
                Parent root2= FXMLLoader.load(getClass().getResource("CustomerController2.fxml"));
                stage2.setScene(new Scene(root2));
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.show();
                stage2.setTitle("Sửa thông tin khách hàng");
            }catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thông báo:");
                alert.setContentText("Chưa chọn khách hàng !");
                alert.showAndWait();
            }
        }

        public void infor(ActionEvent e) {
            noscene=0;
            currentCustomer = tableCustomer.getSelectionModel().getSelectedItem();
            try{
                Stage stage2 = new Stage();
                Parent root2= FXMLLoader.load(getClass().getResource("CustomerController2.fxml"));
                stage2.setScene(new Scene(root2));
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.show();
                stage2.setTitle("Thông tin khách hàng");
            }catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thông báo:");
                alert.setContentText("Chưa chọn khách hàng !");
                alert.showAndWait();
            }
        }

    }