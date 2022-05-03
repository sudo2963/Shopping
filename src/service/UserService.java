package service;
import pojo.Product;
/**
 * 接口服务
 * **/
import pojo.User;

import java.util.List;

public  interface UserService {
	public User login(String uCode, String uPwd);

	int getMaxId();

	public boolean addUser(User newUser);


	public boolean topup(String uCode, double money);


	public boolean updateUser(User user);

	public List<User> getAllUser();

	public boolean addAdmin(String uCode);

	public boolean deleteUser(String uCode);

	public  boolean userBuy(String uCode, Product np);

	public boolean addChk(String uCode);


}
