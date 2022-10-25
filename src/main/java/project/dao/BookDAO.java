package project.dao;

import project.jdbcutil.JDBCUtil;
import project.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO implements DAO<Book> {
    @Override
    public int insert(Book book) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO book(id,name,price,importPrice,count,description,author,language,translator,numberOfPages,publishingCompany,publishingYear,status)"
                    +" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,book.getId());
            st.setString(2,book.getName());
            st.setString(3,book.getPrice()+"");
            st.setString(4,book.getImportPrice()+"");
            st.setString(5,book.getCount()+"");
            st.setString(6,book.getDescription());
            st.setString(13,book.getStatus());
            st.setString(7,book.getAuthor());
            st.setString(8,book.getLanguage());
            st.setString(9,book.getTranslator());
            st.setString(10,book.getNumberOfPages()+"");
            st.setString(11,book.getPublishingCompany());
            st.setString(12,book.getPublishingYear()+"");
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;
    }

    @Override
    public int update(Book book) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE book "+"SET name=?,price=?,importPrice=?,count=?,description=?"
                    +",author=?,language=?,translator=?,numberOfPages=?,publishingCompany=?,publishingYear=?,status=? "+
                    "WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,book.getName());
            st.setString(2,book.getPrice()+"");
            st.setString(3,book.getImportPrice()+"");
            st.setString(4,book.getCount()+"");
            st.setString(5,book.getDescription());
            st.setString(12,book.getStatus());
            //
            st.setString(6,book.getAuthor());
            st.setString(7,book.getLanguage());
            st.setString(8,book.getTranslator());
            st.setString(9,book.getNumberOfPages()+"");
            st.setString(10,book.getPublishingCompany());
            st.setString(11,book.getPublishingYear()+"");

            st.setString(13,book.getId());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
    @Override
    public int delete(Book book) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from book "+"WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,book.getId());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public ArrayList<Book> selectAll() {
        ArrayList<Book> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM book";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Book book=new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getInt("price"));
                book.setCategory("SÁCH");
                book.setImportPrice(rs.getInt("importPrice"));
                book.setCount(rs.getInt("count"));
                book.setDescription(rs.getString("description"));
                book.setStatus(rs.getString("status"));
                //
                book.setAuthor(rs.getString("author"));
                book.setLanguage(rs.getString("language"));
                book.setTranslator(rs.getString("translator"));
                book.setNumberOfPages(rs.getInt("numberOfPages"));
                book.setPublishingCompany(rs.getString("publishingCompany"));
                book.setPublishingYear(rs.getInt("publishingYear"));
                ans.add(book);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
