package project.Manager;

import project.model.Employee;

import java.util.ArrayList;

public class EmployeeManager {
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    public static void add(Employee employee){
        employeeList.add(employee);
    }
    public static void delete(Employee employee){
        employeeList.remove(employee);
    }
    public static Employee get(String id){
        for(Employee employee : employeeList) {
            if (employee.getId().compareTo(id)==0) return employee;
        }
        return null;
    }
    public static boolean containsId(String id){
        for(Employee employee : employeeList){
            if(employee.getId().compareTo(id)==0) return true;
        }
        return false;
    }
}
