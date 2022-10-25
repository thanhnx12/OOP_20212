package project.model;

import java.sql.Date;

public class Bill {
    private String id;
    private Date date;
    private Employee employee;
    private Customer customer;
    private Order order;
    private int totalPayout;
    public Bill() {
    }

    public Bill(String id, Date date, Employee employee, Customer customer, Order order, int totalPayout) {
        this.id = id;
        this.date = date;
        this.employee = employee;
        this.customer = customer;
        this.order = order;
        this.totalPayout = totalPayout;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(int totalPayout) {
        this.totalPayout = totalPayout;
    }
    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", employee=" + employee +
                ", customer=" + customer +
                ", order=" + order +
                ", totalPayout=" + totalPayout +
                '}';
    }
}
