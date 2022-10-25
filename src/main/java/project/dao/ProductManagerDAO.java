package project.dao;

import project.jdbcutil.JDBCUtil;
import project.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductManagerDAO {
    static public BookDAO bookDAO= new BookDAO();
    static public FilmDAO filmDAO=new FilmDAO();
    static public MusicDAO musicDAO=new MusicDAO();

    public static ArrayList<Product> importAll(){
        ArrayList<Product> list= new ArrayList<>();
        list.addAll(bookDAO.selectAll());
        list.addAll(filmDAO.selectAll());
        list.addAll(musicDAO.selectAll());
        return list;
    }

    public static boolean insert(Product product) {
        try{
            if (product.getCategory()=="SÁCH") bookDAO.insert((Book) product);
            else
            if (product.getCategory()=="PHIM") filmDAO.insert((Film) product);
            else
            if (product.getCategory()=="NHẠC") musicDAO.insert((Music) product);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    public static boolean update(Product product) {
        try{
            if (product.getCategory()=="SÁCH") bookDAO.update((Book) product);
            else
            if (product.getCategory()=="PHIM") filmDAO.update((Film) product);
            else
            if (product.getCategory()=="NHẠC") musicDAO.update((Music) product);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean delete(Product product) {
        try{
            if (product.getCategory()=="SÁCH") bookDAO.delete((Book) product);
            else
            if (product.getCategory()=="PHIM") filmDAO.delete((Film) product);
            else
            if (product.getCategory()=="NHẠC") musicDAO.delete((Music) product);
        } catch (Exception e){
            return false;
        }
        return true;
    }

}
