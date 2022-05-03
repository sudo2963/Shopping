package service.impl;
import java.util.Date;

import pojo.Order;
import pojo.Product;
import service.*;

public class OrderServiceImpl implements OrderService {
	private static Order[] orders = new Order[10];
	private static ProductService productService = new ProductServiceImpl();
	private UserService userService=new UserServiceImpl();
	static Product[] products = productService.getProductInfo();
	static {
		orders[0] =new Order(1,"sudo",products[0],5555,new Date(),0,"湖北省武汉市江夏区");
		orders[1] =new Order(2,"sudo",products[1],6666,new Date(),1,"湖北省武汉市江夏区");
		orders[2] =new Order(3,"niuqi",products[2],7777,new Date(),0,"湖北省武汉市江夏区");
	}
	@Override
	public Order[] getAllOrder() {
		// TODO Auto-generated method stub
		return orders;
	}
	@Override
	public Order[] getOrderByOne(String uid) {
		// TODO Auto-generated method stub
		int index =0;
		for (int i = 0; i < orders.length; i++) {
			if(orders[i]!=null&&orders[i].getUcode().equals(uid)) {
				index++;
			}
		}
		Order[] userOrders =new Order[index];
		index=0;
		for (int i = 0; i < orders.length; i++) {
			if(orders[i]!=null&&orders[i].getUcode().equals(uid)) {
				userOrders[index]=orders[i];
				index++;
			}
		}
		return userOrders;
		
	}
	@Override
	public Order getOrderById(int oId) {
		// TODO Auto-generated method stub
		return orders[oId-1];
	}
	@Override
	public boolean ChkState(String uid) {
		// TODO Auto-generated method stub
		for (int i = 0; i < orders.length; i++) {
			if(orders[i]!=null&&orders[i].getUcode().equals(uid)&&orders[i].getPayState()==0) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		if(order==null) {
			return false;
		}
		for (int i = 0; i < orders.length; i++) {
			if(orders[i]!=null&&orders[order.getOid()]!=null&&orders[i].getOid()==(order.getOid())) {
				orders[order.getOid()-1]=order;
				return true;
			}
		}

		return false; 
	}
	public int getMaxId() {
		int max =0;
		for (int i = 0; i < orders.length; i++) {
			if(orders[i]!=null&&orders[i].getOid()>max) {
				max=orders[i].getOid();
			}
		}
		return max;
		
	}
	@Override
	public boolean addOrder(Order order) {
		// TODO Auto-generated method stub
		int i =order.getOid()-1;
		if(i>orders.length-1) {
			identity();
		}
		orders[i] =order;
		return true;
	}
	private void identity() {
		// TODO Auto-generated method stub
		Order[] nOrder =new Order[orders.length*2];
		for(int i=0;i<nOrder.length-1;i++) {
			nOrder[i]=orders[i];
		}
		orders=nOrder;
	}
	@Override
	public boolean orderStateChk(int stateChk, Order order) {
		// TODO Auto-generated method stub
		if(stateChk ==2&&order.getPayState()==0) {
			if(userService.topup(order.getUcode(), order.getPrice())) {
				return true;
			}
		}
		if(stateChk ==1&&order.getPayState()==0) {
			if(userService.userBuy(order.getUcode(), order.getItems())){
				return true;
			}
		}
		return true;
	}

}
