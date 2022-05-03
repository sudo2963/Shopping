package service.impl;

import UserUtils.DBUtils;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.Product;
import pojo.User;
import service.UserService;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {

	private  static UserDao userDao =new UserDaoImpl();
	@Override
	public User login(String uCode, String uPwd) {
		// TODO Auto-generated method stub
//		for (int i = 0; i < users.length; i++) {
//			if(users[i]!=null&&users[i].getUid().equals(uID)&&users[i].getPassword().equals(uPwd)) {
//				return users[i];
//			}
//		}
		User user = userDao.select(uCode);
		if (user.getucode().equals(uCode)&&user.getPassword().equals(uPwd)){
			return user;
		}
		return null;
	}
	
	public int getMaxId() {
//		int max =0;
//		for (int i = 0; i < users.length; i++) {
//			if(users[i]!=null&&users[i].getId()>max) {
//				max=users[i].getId();
//			}
//		}
		return 0;
		
	}

	@Override
	public boolean addUser(User newUser) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		DBUtils.begin(conn);
		if(userDao.insert(newUser)){
			DBUtils.commit(conn);
			return true;

		}
		DBUtils.rollback(conn);
		return false;
		
	}

	@Override
	public boolean topup(String ucode, double money) {
		// TODO Auto-generated method stub
//		User user =null;
//		for (int i = 0; i < users.length; i++) {
//			if(users[i]!=null&&users[i].getUid().equals(id)) {
//				user=users[i];
//			}
//		}
//		if(user!=null) {
//			user.setMoney(user.getMoney()+money);
//			return true;
//		}
		return false;
	}


	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		DBUtils.begin(conn);
		if(userDao.update(user)){
			DBUtils.commit(conn);
			return  true;
		}
		DBUtils.rollback(conn);
		return false; 
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		
		return userDao.selectMore();
	}

	@Override
	public boolean addAdmin(String uid) {
		// TODO Auto-generated method stub
//		for (int i = 0; i < users.length; i++) {
//			if(users[i]!=null&&users[i].getId()!=1&&users[i].getUid().equals(uid)) {
//				users[i].setState(1);
//				return true;
//			}
//		}
		return false;
	}

	@Override
	public boolean deleteUser(String uCode) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		DBUtils.begin(conn);
		User user = userDao.select(uCode);
		if(userDao.delete(user.getUid())){
			DBUtils.commit(conn);
			return true;
		}
		DBUtils.rollback(conn);
		return false;
	}


	public boolean userBuy(String uid, Product np) {
		// TODO Auto-generated method stub
//		for(int i=0;i<users.length;i++) {
//			if(users[i]!=null&&users[i].getUid().equals(uid)){
//				if(users[i].getMoney()>np.getPrice()) {
//					users[i].setMoney(users[i].getMoney()-np.getPrice());
//					return true;
//				}else {
//					System.out.println("用户余额不足请充值");
//				}
//			}
//		}
		return false;
	}

	@Override
	public boolean addChk(String uid) {
		// TODO Auto-generated method stub
//
//		for (int i = 0; i < users.length; i++) {
//			if(users[i]!=null&&users[i].getUid().equals(uid)) {
//				return true;
//			}
//		}
		return false;
	}
	
			
}
