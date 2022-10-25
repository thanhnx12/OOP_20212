package project.dao;

import project.Manager.CustomerManager;
import project.Manager.EmployeeManager;
import project.Manager.ProductManager;
import project.jdbcutil.JDBCUtil;
import project.model.*;

import java.sql.*;
import java.util.ArrayList;

public class BillManagerDAO {
    //
    public static String productToString(Order order){
        String s= new String();
        for (SoldProduct e : order.getList())
            s=s+e.getProduct().getId()+",";
        return s;
    }
    public static String countToString(Order order){
        String s=new String();
        for (SoldProduct e : order.getList())
            s=s+e.getCount()+",";
        return s;
    }
    public static String priceToString(Order order){
        String s=new String();
        for (SoldProduct e : order.getList())
            s=s+e.getPrice()+",";
        return s;
    }
    public static Order getOrder(String productList,String countList,String priceList){
        Order order= new Order();
        order.setList(new ArrayList<>());
        ArrayList<String> pList = new ArrayList<>();
        ArrayList<Integer> cList = new ArrayList<>();
        ArrayList<Integer> prList = new ArrayList<>();

        for(int x : processString(productList)){
            pList.add(x + "");
        }
        for(int x : processString(countList)){
            cList.add(x);
        }
        for(int x : processString(priceList)){
            prList.add(x);
        }
        for(int x = 0; x < pList.size(); x++){
            order.getList().add(new SoldProduct(ProductManager.get(pList.get(x)), cList.get(x), prList.get(x)));
            //System.out.println(pList.get(x) + " , " + cList.get(x) +" , " + prList.get(x));
        }

        return order;
    }

    public static ArrayList<Integer> processString(String s){
        ArrayList<Integer> res = new ArrayList<>() ;
        int index = 0;
        int tmp = 0;
        for(index = 0; index < s.length() ; index ++){
            while(s.charAt(index) != ','){
                tmp = tmp*10 + (s.charAt(index) - '0');
                index ++;
            }
            res.add(tmp);
            if(s.charAt(index) == ','){
                tmp = 0;
            }
        }
        return res;
    }
    public static int insert(Bill bill) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO bill(id,date,employeeID,customerID,productList,countList,priceList,totalPayout)"
                    +" VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,bill.getId());
            st.setDate(2,bill.getDate());
            st.setString(3,bill.getEmployee().getId());
            st.setString(4,bill.getCustomer().getId());
            st.setString(5,productToString(bill.getOrder()));
            st.setString(6,countToString(bill.getOrder()));
            st.setString(7,priceToString(bill.getOrder()));
            st.setInt(8,bill.getTotalPayout());
            ans=st.executeUpdate();
//            System.out.println("Bạn đã thực thi: "+sql);
//            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;
    }
    public static ArrayList<Bill> importAll(){
        ArrayList<Bill> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM bill order by date asc";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Bill bill=new Bill();
                bill.setId(rs.getString("id"));
                bill.setDate(rs.getDate("date"));
                bill.setEmployee(EmployeeManager.get(rs.getString("employeeID")));
                bill.setCustomer(CustomerManager.get(rs.getString("customerID")));
                Order order= new Order();
                String s1= rs.getString("productList");
                String s2=rs.getString("countList");
                String s3=rs.getString("priceList");
                bill.setOrder(getOrder(s1,s2,s3));
                bill.setTotalPayout(rs.getInt("totalPayout"));
                ans.add(bill);

            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }


}