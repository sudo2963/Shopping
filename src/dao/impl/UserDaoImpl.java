package dao.impl;

import UserUtils.DBUtils;
import UserUtils.DateUtils;
import dao.UserDao;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean insert(User u) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps =null;
        String sql ="INSERT into user(u_code,u_name,password,money,createdate,user_state,addresss,default_address) VALUES(?,?,?,?,?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,u.getucode());
            ps.setString(2,u.getName());
            ps.setString(3,u.getPassword());
            ps.setDouble(4,u.getMoney());
            ps.setDate(5, DateUtils.utilToSql(u.getCreateDate()));
            ps.setInt(6,u.getState());
            ps.setString(7, Arrays.toString(u.getAddress()));
            ps.setString(8,u.getDefaultAddress());

            return ps.executeUpdate()>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps =null;
        String sql ="delete from user where u_id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);

            return ps.executeUpdate()>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User u) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps =null;
        String sql ="update user set u_code=?,u_name=?,password=?,money=?,createdate=?,user_state=?,addresss=?,default_address=? where u_id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,u.getucode());
            ps.setString(2,u.getName());
            ps.setString(3,u.getPassword());
            ps.setDouble(4,u.getMoney());
            ps.setDate(5,(java.sql.Date)u.getCreateDate());
            ps.setInt(6,u.getState());
            ps.setString(7,u.getAddress().toString());
            ps.setString(8,u.getDefaultAddress());
            ps.setInt(9,u.getUid());

            return ps.executeUpdate()>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User select(String uCode) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql ="select * from user where u_code= ?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,uCode);
            rs= ps.executeQuery();
            if(rs.next()){
                return  new User(
                    rs.getInt("u_id"),
                    rs.getString("u_code"),
                    rs.getString("u_name"),
                    rs.getString("password"),
                    rs.getDouble("money"),
                    rs.getDate("createdate"),
                    rs.getInt("user_state"),
                    rs.getString("addresss").split(","),
                    rs.getString("default_address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> selectMore() {
        List<User> ulist = new ArrayList<User>();
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql ="select * from user";
        try {
            ps=conn.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                ulist.add(new User(
                rs.getInt("u_id"),
                rs.getString("u_code"),
                rs.getString("u_name"),
                rs.getString("password"),
                rs.getDouble("money"),
                rs.getDate("createdate"),
                rs.getInt("user_state"),
                rs.getString("addresss").split(","),
                rs.getString("default_address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ulist;
    }
}
