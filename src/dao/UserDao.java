package dao;

import pojo.User;

import java.util.List;

public interface UserDao {
    public boolean insert(User u);
    public boolean delete(int id);
    public boolean update(User u);
    //public User select(Object...args);
    public User select(String uCode);
    //public List<User> selectMore(Object...args);
    public List<User> selectMore();
}
