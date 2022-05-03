package service;

import pojo.Order;
import pojo.Product;

public interface OrderService {


	public Order[] getAllOrder();

	public Order[] getOrderByOne(String uid);

	public Order getOrderById(int oId);


	public boolean ChkState(String uid);

	public boolean updateOrder(Order order);
	public int getMaxId();

	public boolean addOrder(Order order);

	public boolean orderStateChk(int stateChk, Order order);

}
