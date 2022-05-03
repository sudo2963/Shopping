package view;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import pojo.Order;
import pojo.Product;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

public class OrderView {
	public static Product product;
	public static User user;
	
	private static  OrderService orderService = new OrderServiceImpl();
	public static Scanner scanner =new Scanner(System.in);
	
	public static void userOrder(Order order) {
		// TODO Auto-generated method stub
		System.out.println("----- 订单信息------");
		System.out.println("订单ID:"+order.getOid());
		System.out.println("客户代码:"+order.getUcode());
		System.out.println("订单明细:"+order.getItems().getpName());
		System.out.println("订单金额:"+order.getPrice());
		System.out.println("订单日期:"+order.getCreateTime());
		System.out.println("支付状态:"+(order.getPayState()==1?"已支付":"未支付"));
		System.out.println("收货地址:"+order.getAddress());
		if(user.getState()==1) {
			orderMenu(order);
		}else {
			userOrderMenu(order);
		}
	}


	private static void orderMenu(Order order) {
		// TODO Auto-generated method stub
		System.out.println("1.取消订单");
		System.out.println("2.修改收货地址");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		int stateChk  =0;
		switch (skipcount) {
		case 1:
			order.setPayState(2);
			 stateChk  =2;
			break;
		case 2:
			System.out.println("请输入新的收货地址");
			order.setAddress(scanner.next());
			break;
		case 0:
			UserView view = new UserView();
			view.back(user);
			break;
		default:
			break;		
		}
		if(orderService.updateOrder(order)) {
			System.out.println("修改成功");
			if(orderService.orderStateChk(stateChk,order)) {
				System.out.println("订单已退款");
			};
		}else {
			System.out.println("修改失败");
		}
		orderMenu();
	}


	private static void userOrderMenu(Order order) {
		// TODO Auto-generated method stub
		System.out.println("1.去支付");
		System.out.println("2.取消订单");
		System.out.println("3.修改收货地址");
		System.out.println("0.返回上一级");
		int stateChk  =0;
		int skipcount =scanner.nextInt();
		switch (skipcount) {
		case 1:
			if(orderService.ChkState(user.getucode())) {
				order.setPayState(1);
				stateChk =1;
			}else {
				System.out.println("没有需要支付的订单");
			}
			break;
		case 2:
			order.setPayState(2);
			stateChk =2;
			break;
		case 3:
			System.out.println("请输入新的收货地址");
			order.setAddress(scanner.next());
			break;
		case 0:
			UserView view = new UserView();
			view.back(user);
			break;
		default:
			break;		
		}
		if(orderService.updateOrder(order)) {
			System.out.println("修改成功");
			if(orderService.orderStateChk(stateChk,order)) {
				System.out.println(stateChk==1?"订单已支付":"订单已退款");
			}
		}else {
			System.out.println("修改失败");
		}
		userOrderMenu(user);
	}


	public static void index() {
		// TODO Auto-generated method stub
		System.out.println("1.查看订单详情");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		switch (skipcount) {
		case 1:
			System.out.println("请输入订单id");
			int oId =scanner.nextInt();
			Order order =orderService.getOrderById(oId);
			userOrder(order);
			break;
		case 2:

			break;
		case 0:
				
			break;
		default:
			break;		
		}
	}


	static void orderMenu() {
		// TODO Auto-generated method stub
		orderShow(orderService.getAllOrder());
		index();
	}
	

	public static void userOrderMenu(User nowuser) {
		// TODO Auto-generated method stub
		user=nowuser;
		orderShow(orderService.getOrderByOne(user.getucode()));
		index();
	}
	private static void orderShow(Order[] orders) {
		// TODO Auto-generated method stub
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		System.out.println("----- 订单列表------");
		System.out.println("________________________________________________________________________________________________________________________________________________________________________________________________________________ ");
		System.out.println("	 	|	订单ID	|	客户ID	|	商品名称			|		价格			|		订单时间			|	支付状态	|	收货地址		|");

		System.out.println("________________|_______________|_______________|_______________________________|_______________________________________|_______________________________________|_______________|______________________________ |");
		
		for (int i = 0; i <orders.length; i++) {
			if(orders[i]!=null) {
			System.out.println(String.format("	%d	|%d		|%s		|%s			|		%f		|	%s		|	%s	|		%s		|",
												i+1,orders[i].getOid(),orders[i].getUcode(),orders[i].getItems().getpName(),
												orders[i].getPrice(),format.format(orders[i].getCreateTime()),orders[i].getPayState()==2?"已取消":orders[i].getPayState()==1?"已支付":"待支付",orders[i].getAddress()));				
			}	
		}
		System.out.println("________________|_______________|_______________|_______________________________|_______________________________________|_______________________________________|_______________|______________________________ |");
	}
}
