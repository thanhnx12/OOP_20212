package project.dao;

import project.jdbcutil.JDBCUtil;
import project.model.Book;
import project.model.Film;

import java.sql.*;
import java.util.ArrayList;

public class FilmDAO implements DAO<Film> {
    @Override
    public int insert(Film film) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO film(id,name,price,importPrice,count,description"+
                    ",director,mainActor,movieGenre,time,premiere,language,status)"
                    +" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,film.getId());
            st.setString(2,film.getName());
            st.setString(3,film.getPrice()+"");
            st.setString(4,film.getImportPrice()+"");
            st.setString(5,film.getCount()+"");
            st.setString(6,film.getDescription());
            st.setString(13,film.getStatus());
            st.setString(7,film.getDirector());
            st.setString(8,film.getMainActor());
            st.setString(9,film.getMovieGenre());
            st.setString(10,film.getTime());
            st.setString(11,film.getPremiere()+"");
            st.setString(12,film.getLanguage());
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
    public int update(Film film) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE film "+"SET name=?,price=?,importPrice=?,count=?,description=?"
                    +",director=?,mainActor=?,movieGenre=?,time=?,premiere=?,language=?,status=?"+
                    "WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,film.getName());
            st.setString(2,film.getPrice()+"");
            st.setString(3,film.getImportPrice()+"");
            st.setString(4,film.getCount()+"");
            st.setString(5,film.getDescription());
            //
            st.setString(6,film.getDirector());
            st.setString(7,film.getMainActor());
            st.setString(8,film.getMovieGenre());
            st.setString(9,film.getTime());
            st.setString(10,film.getPremiere()+"");
            st.setString(11,film.getLanguage()+"");
            st.setString(13,film.getId());
            st.setString(12,film.getStatus());
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
    public int delete(Film film) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from film "+"WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,film.getId());
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
    public ArrayList<Film> selectAll() {
        ArrayList<Film> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM film";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Film film=new Film();
                film.setId(rs.getString("id"));
                film.setName(rs.getString("name"));
                film.setPrice(rs.getInt("price"));
                film.setCategory("PHIM");
                film.setImportPrice(rs.getInt("importPrice"));
                film.setCount(rs.getInt("count"));
                film.setDescription(rs.getString("description"));
                film.setStatus(rs.getString("status"));
                //
                film.setDirector(rs.getString("director"));
                film.setMainActor(rs.getString("mainActor"));
                film.setMovieGenre(rs.getString("movieGenre"));
                film.setTime(rs.getString("time"));
                film.setPremiere(rs.getInt("premiere"));
                film.setLanguage(rs.getString("language"));
                ans.add(film);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
