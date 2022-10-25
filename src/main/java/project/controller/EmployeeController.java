package project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.dao.EmployeeManagerDAO;
import project.model.Employee;
import project.Manager.EmployeeManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private ChoiceBox<String> statusChoiceBox;
//    @FXML
//    private DatePicker datePicker;
    @FXML
    private RadioButton manButton, womanButton;

    @FXML
    private TextField salaryText;
    @FXML
    private TextField shiftText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField passText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField searchText;
    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, String> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> passColumn;

    @FXML
    private TableColumn<Employee, String> roleColumn;

    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private TableColumn<Employee, String> genderColumn;

    @FXML
    private TableColumn<Employee, String> statusColumn;
    @FXML
    private TableColumn<Employee, Integer> salaryColumn;
    @FXML
    private TableColumn<Employee, Double> shiftColumn;
    private String[] role = {"Nhân viên", "Quản lý"};
    private String[] status = {"Đang làm việc","Nghỉ việc"};

    private ObservableList<Employee> employeeList;
    public void showEmployee(Employee employee){


        nameText.setText(employee.getName());
        salaryText.setText(""+employee.getSalary());
        shiftText.setText(""+employee.getShift());
        passText.setText(employee.getPassword());
        addressText.setText(employee.getAddress());
        if (employee.getGender()=="Nam") manButton.setSelected(true);
        else womanButton.setSelected(true);
        roleChoiceBox.setValue(employee.getRole());
        statusChoiceBox.setValue(employee.getStatus());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleChoiceBox.getItems().addAll(role);
        statusChoiceBox.getItems().addAll(status);
        employeeList = FXCollections.observableArrayList();
        employeeList.addAll(EmployeeManager.employeeList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        passColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("status"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));
        shiftColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("shift"));
        table.setItems(employeeList);
        FilteredList<Employee> filter = new FilteredList<>(employeeList, e->true);
        searchText.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filter.setPredicate(Employee -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(Employee.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(Employee.getGender().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(Employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(Employee.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(Employee.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(Employee.getStatus().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(Employee.getId().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else
                    return false;
            });
        }));
        SortedList<Employee> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

//    public void getDate(ActionEvent event){
//        LocalDate date = datePicker.getValue();
//    }

    public void add (ActionEvent event) throws SQLException{
        Employee newEmployee = new Employee();
        if(manButton.isSelected()) {
            int id = EmployeeManager.employeeList.size() + 1;
            newEmployee.setId(id + "");
            newEmployee.setName(nameText.getText());
            newEmployee.setPassword(passText.getText());
            newEmployee.setAddress(addressText.getText());
            newEmployee.setRole(roleChoiceBox.getValue());
            newEmployee.setGender(manButton.getText());
            newEmployee.setStatus(statusChoiceBox.getValue());
            newEmployee.setSalary(Integer.parseInt(salaryText.getText()));
            newEmployee.setShift(Double.parseDouble(shiftText.getText()));
        }
        else if(womanButton.isSelected()){
            int id = EmployeeManager.employeeList.size() + 1;
            newEmployee.setId(id + "");
            newEmployee.setName(nameText.getText());
            newEmployee.setPassword(passText.getText());
            newEmployee.setAddress(addressText.getText());
            newEmployee.setRole(roleChoiceBox.getValue());
            newEmployee.setGender(womanButton.getText());
            newEmployee.setStatus(statusChoiceBox.getValue());
            newEmployee.setSalary(Integer.parseInt(salaryText.getText()));
            newEmployee.setShift(Double.parseDouble(shiftText.getText()));
        }
        employeeList.add(newEmployee);
        EmployeeManagerDAO.insert(newEmployee);
        EmployeeManager.add(newEmployee);
    }


    public void edit(ActionEvent event) throws SQLException{
        Employee selected = table.getSelectionModel().getSelectedItem();;
        if(manButton.isSelected()) {

            selected.setName(nameText.getText());
            selected.setPassword(passText.getText());
            selected.setAddress(addressText.getText());
            selected.setRole(roleChoiceBox.getValue());
            selected.setGender(manButton.getText());
            selected.setStatus(statusChoiceBox.getValue());
            selected.setSalary(Integer.parseInt(salaryText.getText()));
            selected.setShift(Double.parseDouble(shiftText.getText()));
        }
        else if(womanButton.isSelected()){

            selected.setName(nameText.getText());
            selected.setPassword(passText.getText());
            selected.setAddress(addressText.getText());
            selected.setRole(roleChoiceBox.getValue());
            selected.setGender(womanButton.getText());
            selected.setStatus(statusChoiceBox.getValue());
            selected.setSalary(Integer.parseInt(salaryText.getText()));
            selected.setShift(Double.parseDouble(shiftText.getText()));
        }
        EmployeeManagerDAO.update(selected);
        table.refresh();
    }
    public void detail(){
        Employee selected = table.getSelectionModel().getSelectedItem();
        showEmployee(selected);
    }
}