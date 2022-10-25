package project.dao;

import project.jdbcutil.JDBCUtil;
import project.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManagerDAO {

    public static int insert(Employee employee) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO employee(id,name,password,role,address,gender,status,salary,shift)"
                    +" VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,employee.getId());
            st.setString(2, employee.getName());
            st.setString(3,employee.getPassword());
            st.setString(4,employee.getRole());
            st.setString(5,employee.getAddress());
            st.setString(6,employee.getGender());
            st.setString(7,employee.getStatus());
            st.setString(8,""+employee.getSalary());
            st.setString(9,""+employee.getShift());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;
    }

    public static int update(Employee employee) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE employee "+"SET name=?,password=?,role=?,address=?,gender=?,status=?,salary=?,shift=? "
                    + "WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(9,employee.getId());
            st.setString(1, employee.getName());
            st.setString(2,employee.getPassword());
            st.setString(3,employee.getRole());
            st.setString(4,employee.getAddress());
            st.setString(5,employee.getGender());
            st.setString(6,employee.getStatus());
            st.setString(7,""+employee.getSalary());
            st.setString(8,""+employee.getShift());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public static int delete(Employee employee) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from employee "+"WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,employee.getId());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public static ArrayList<Employee> importAll() {
        ArrayList<Employee> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM employee";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Employee employee= new Employee();
                employee.setId(rs.getString("id"));
                employee.setName(rs.getString("name"));
                employee.setPassword(rs.getString("password"));
                employee.setRole(rs.getString("role"));
                employee.setAddress(rs.getString("address"));
                employee.setGender(rs.getString("gender"));
                employee.setStatus(rs.getString("status"));
                employee.setSalary(rs.getInt("salary"));
                employee.setShift(rs.getDouble("shift"));
                ans.add(employee);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
