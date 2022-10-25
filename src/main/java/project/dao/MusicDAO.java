package project.dao;

import project.jdbcutil.JDBCUtil;
import project.model.Book;
import project.model.Music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicDAO implements DAO<Music> {
    @Override
    public int insert(Music music) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO music(id,name,price,importPrice,count,description"+
                    ",singers,producer,musicGenre,time,status) "
                    +" VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,music.getId());
            st.setString(2,music.getName());
            st.setString(3,music.getPrice()+"");
            st.setString(4,music.getImportPrice()+"");
            st.setString(5,music.getCount()+"");
            st.setString(6,music.getDescription());
            st.setString(7,music.getSingers());
            st.setString(8,music.getProducer());
            st.setString(9,music.getMusicGenre());
            st.setString(10,music.getTime());
            st.setString(11,music.getStatus());
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
    public int update(Music music) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE music "+"SET name=?,price=?,importPrice=?,count=?,description=?"
                    +",singers=?,producer=?,musicGenre=?,time=?,status=? "+
                    "WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,music.getName());
            st.setString(2,music.getPrice()+"");
            st.setString(3,music.getImportPrice()+"");
            st.setString(4,music.getCount()+"");
            st.setString(5,music.getDescription());
            //
            st.setString(6,music.getSingers());
            st.setString(7,music.getProducer());
            st.setString(8,music.getMusicGenre());
            st.setString(9,music.getTime());
            st.setString(10,music.getStatus());
            st.setString(11,music.getId());
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
    public int delete(Music music) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from music "+"WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,music.getId());
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
    public ArrayList<Music> selectAll() {
        ArrayList<Music> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM music";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Music music=new Music();
                music.setId(rs.getString("id"));
                music.setName(rs.getString("name"));
                music.setPrice(rs.getInt("price"));
                music.setCategory("NHẠC");
                music.setImportPrice(rs.getInt("importPrice"));
                music.setCount(rs.getInt("count"));
                music.setDescription(rs.getString("description"));
                //
                music.setSingers(rs.getString("singers"));
                music.setProducer(rs.getString("producer"));
                music.setMusicGenre(rs.getString("musicGenre"));
                music.setTime(rs.getString("time"));
                music.setStatus(rs.getString("status"));
                ans.add(music);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
