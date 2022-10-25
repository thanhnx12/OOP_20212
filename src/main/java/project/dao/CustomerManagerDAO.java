package project.dao;

import project.jdbcutil.JDBCUtil;
import project.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManagerDAO {
    public static void insert(Customer customer) {
        Connection conn= JDBCUtil.getConnection();
        PreparedStatement pst;
        String sql="INSERT INTO customer (id,name,gender,address,phone,status,email,information,totalamountpaid) VALUES(?,?,?,?,?,?,?,?,?)";
        String id= customer.getId();
        String name= customer.getName();
        String gender= customer.getGender();
        String address= customer.getAddress();
        String phone= customer.getPhone();
        String status= customer.getStatus();
        String email= customer.getEmail();
        String information= customer.getInformation();
        int totalamountpaid= customer.getTotalAmountPaid();
        try {
            pst= conn.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,name);
            pst.setString(3,gender);
            pst.setString(4,address);
            pst.setString(5,phone);
            pst.setString(6,status);
            pst.setString(7,email);
            pst.setString(8,information);
            pst.setInt(9,totalamountpaid);
            pst.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void update(Customer customer) {
        try{
            Connection conn= JDBCUtil.getConnection();
            PreparedStatement pst;
            String id= customer.getId();
            String name= customer.getName();
            String gender= customer.getGender();
            String address= customer.getAddress();
            String phone= customer.getPhone();
            String status= customer.getStatus();
            String email= customer.getEmail();
            String information= customer.getInformation();
            int totalamountpaid= customer.getTotalAmountPaid();
            String sql=  "update customer " +
                    "set name= '" + name +
                    "',gender = '" + gender + "',address= '" + address +"', phone= '" + phone + "',status= '" + status + "', email= '" + email +
                    "',information= '"+information+
                    "',totalamountpaid ='"+totalamountpaid+
                    "'where id = " + id;
            pst = conn.prepareStatement(sql);
            pst.execute();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void delete(Customer customer) {
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement pst;
            String id = customer.getId();
            String sql = "DELETE FROM customer where id=" + id;
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static ArrayList<Customer> importALl() {
        ArrayList<Customer> ans= new ArrayList<>();
        try {
            Connection con=JDBCUtil.getConnection();
            String sql= "SELECT * FROM customer";
            PreparedStatement st= con.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getString("id"));
                customer.setName(rs.getString("name"));
                customer.setGender(rs.getString("gender"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setStatus(rs.getString("status"));
                customer.setEmail(rs.getString("email"));
                customer.setInformation(rs.getString("information"));
                customer.setTotalAmountPaid(rs.getInt("totalamountpaid"));
                ans.add(customer);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ans;
    }
}
