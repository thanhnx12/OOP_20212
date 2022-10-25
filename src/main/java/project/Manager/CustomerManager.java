package project.Manager;

import project.model.Customer;

import java.util.ArrayList;

public class CustomerManager {
    public static ArrayList<Customer> customerList= new ArrayList<>();
    public static void add(Customer customer) {
        customerList.add(customer);
    }
    public static Customer get(String id) {
        try {
            for (Customer customer : customerList) {
                if (customer.getId().compareTo(id)==0) return customer;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static boolean containsID(String id) {
        for (Customer customer: customerList) {
            if (customer.getId().compareTo(id)==0) return true;
        }
        return false;
    }

    public static boolean delete(Customer customer){
        try {
            customerList.remove(customer);
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
