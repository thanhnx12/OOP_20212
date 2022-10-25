package project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.dao.CustomerManagerDAO;
import project.model.Customer;
import project.Manager.CustomerManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController2 implements  Initializable {


        @FXML
        private TextField nameText;

        @FXML
        private ComboBox<String> genderBox;

        @FXML
        private TextField addressText;

        @FXML
        private TextField phoneText;
        @FXML
        private TextField emailText;

        @FXML
        private TextField amountPaidText;

        @FXML
        TextArea informationTextArea;
        @FXML
        TextField statusText;
        @FXML
        public Button addButton;
        static TableView<Customer> cloneTable;
        //Lay du lieu tu giao dien
        public Customer setCustomer() {
            Customer newCustomer = new Customer();
            try {
                int id = CustomerManager.customerList.size() + 1;
                newCustomer.setId(id + "");
                newCustomer.setName(nameText.getText());
                if (genderBox.getValue() == null) newCustomer.setGender("");
                else newCustomer.setGender(genderBox.getValue());
                newCustomer.setAddress(addressText.getText());
                newCustomer.setPhone(phoneText.getText());
                newCustomer.setEmail(emailText.getText());
                newCustomer.setInformation(informationTextArea.getText());
                newCustomer.setTotalAmountPaid(Integer.parseInt(amountPaidText.getText()));
                if (newCustomer.getDiscount()==1) newCustomer.setStatus("Đồng");
                if (newCustomer.getDiscount()==2) newCustomer.setStatus("Bạc");
                if (newCustomer.getDiscount()==5) newCustomer.setStatus("Vàng");
                if (newCustomer.getDiscount()==7 ) newCustomer.setStatus("Kim cương");

            } catch (Exception ex) {
                System.out.println(ex);
            }
            return newCustomer;
        }
        @FXML
        HBox hBox;
        static ObservableList<String> listGender = FXCollections.observableArrayList("Nam", "Nữ");

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            genderBox.setItems(listGender);
            hBox.setVisible(false);
            informationTextArea.setWrapText(true);
            if (CustomerController1.noscene !=1) {
                Customer newCustomer= CustomerController1.currentCustomer;
                nameText.setText(String.valueOf(newCustomer.getName()));
                genderBox.setValue(String.valueOf(newCustomer.getGender()));
                addressText.setText(String.valueOf(newCustomer.getAddress()));
                phoneText.setText(String.valueOf(newCustomer.getPhone()));
                statusText.setText(String.valueOf(newCustomer.getStatus()));
                emailText.setText(String.valueOf(newCustomer.getEmail()));
                informationTextArea.setText(String.valueOf(newCustomer.getInformation()));
                amountPaidText.setText(String.valueOf(newCustomer.getTotalAmountPaid()));
                if(CustomerController1.noscene==2) {
                    statusText.setDisable(true);
                    hBox.setVisible(true);
                    addButton.setVisible(false);
                }
            } else
            {
                statusText.setDisable(true);
                hBox.setVisible(true);
            }
        }


        public void add() throws SQLException {
                Customer newCustomer = setCustomer();
                CustomerController1.customerListController.add(newCustomer);
                CustomerManager.add(newCustomer);
                CustomerManagerDAO.insert(newCustomer);
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();

        }

        @FXML
        Button fixButton;
        public void fix() {
            Customer currentCustomer= CustomerController1.currentCustomer;

            currentCustomer.setName(nameText.getText());
            if (genderBox.getValue() == null) currentCustomer.setGender("");
            else currentCustomer.setGender(genderBox.getValue());
            currentCustomer.setAddress(addressText.getText());
            currentCustomer.setPhone(phoneText.getText());
            currentCustomer.setEmail(emailText.getText());
            currentCustomer.setInformation(informationTextArea.getText());
            currentCustomer.setTotalAmountPaid(Integer.parseInt(amountPaidText.getText()));
            if (currentCustomer.getTotalAmountPaid()< 10000000) currentCustomer.setStatus("Đồng");
            if (currentCustomer.getTotalAmountPaid()>= 10000000 && currentCustomer.getTotalAmountPaid()< 20000000 ) currentCustomer.setStatus("Bạc");
            if (currentCustomer.getTotalAmountPaid()>= 20000000 && currentCustomer.getTotalAmountPaid()< 50000000 ) currentCustomer.setStatus("Vàng");
            if (currentCustomer.getTotalAmountPaid()>= 50000000 ) currentCustomer.setStatus("Kim cương");
            int x = CustomerController1.customerListController.indexOf(currentCustomer);
            CustomerController1.customerListController.set(x, currentCustomer);
            CustomerManagerDAO.update(currentCustomer);
            cloneTable.refresh();
            Stage stage = (Stage) fixButton.getScene().getWindow();
            stage.close();
        }
        @FXML Button cancelButton;
        public void cancel(){
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
}