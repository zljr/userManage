package cn.edu.zuel.dao;

import cn.edu.zuel.entity.User;
import cn.edu.zuel.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User login(User user){
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet resultSet=null;
        try{
            conn= JdbcUtil.getConnection();
            String sql="select *from user where username=? and password=?";
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getUsername());
            stat.setString(2,user.getPassword());
            resultSet=stat.executeQuery();
            if(resultSet.next()){
                String username=resultSet.getString("username");
                String password=resultSet.getString("password");
                User user1=new User(username,password);
                return user1;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.closeConnection(resultSet,stat,conn);
        }
    }
    public int register(User user){
        Connection conn=null;
        PreparedStatement stat=null;
        try{
            conn=JdbcUtil.getConnection();
            JdbcUtil.beginTransaction(conn);
            String sql="insert into user values(?,?)";
            stat=conn.prepareStatement(sql);
            stat.setString(1, user.getUsername());
            stat.setString(2, user.getPassword());
            int i = stat.executeUpdate();
            conn.commit();
            return i;

        }catch (Exception e){
            e.printStackTrace();
            JdbcUtil.rollback(conn);
            return 0;
        }finally {
            JdbcUtil.closeConnection(stat,conn);
        }
    }
    public User findByUsername(String username){
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet resultSet=null;
        try{
            conn= JdbcUtil.getConnection();
            String sql="select *from user where username=?";
            stat=conn.prepareStatement(sql);
            stat.setString(1,username);

            resultSet=stat.executeQuery();
            if(resultSet.next()){

                String password=resultSet.getString("password");
                User user1=new User(username,password);
                return user1;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.closeConnection(resultSet,stat,conn);
        }
    }
}
