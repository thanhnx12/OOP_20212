package project.model;

import project.Manager.BillManager;
import project.Manager.CustomerManager;
import project.Manager.ProductManager;

public class Employee {
    private String id;
    private String name;
    private String password;
    private String role;
    private String address;
    private String gender;
    private String status;
    private int salary;
    private double shift;

    public static BillManager billManager;
    public static CustomerManager customerManager;
    public static ProductManager productManger;



    public Employee(String id, String name, String password, String role, String address, String gender, String status, int salary, double shift) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.shift = shift;
        this.salary = salary;
    }

    public Employee() {
    }
    public double getShift() {
        return shift;
    }

    public void setShift(double shift) {
        this.shift = shift;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {return status;}

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", salary=" + salary +
                ", shift=" + shift +
                '}';
    }


}

