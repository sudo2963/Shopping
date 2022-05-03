package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import pojo.Order;
import pojo.Product;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;
import service.ProductService;
import service.impl.ProductServiceImpl;

public class ProductView {
	public static Product product;
	public static Product[] products;
	public static User user;
	private static  OrderService orderService = new OrderServiceImpl();
	private static ProductService productService = new ProductServiceImpl();
	public static Scanner scanner =new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void ProductInfoMenu(User nowUser) {
		// TODO Auto-generated method stub
		user=nowUser;
		products = productService.getProductInfo();
		getProductInfoMenu();

	}
	private static void getProductInfoMenu() {
		// TODO Auto-generated method stub
		productShow(products);
		ProductInfoMenu();
	}

	public static void ProductInfoMenu() {
		// TODO Auto-generated method stub
		System.out.println("1.商品上架");
		System.out.println("2.商品下架");
		System.out.println("3.商品查看");
		System.out.println("4.商品修改");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		String pid="";
		switch (skipcount) {
		case 1:
			addProduct();
			break;
		case 2:
			System.out.println("请输入待删除商品的ID");
			pid  =scanner.next();
			if(productService.deleteProduct(pid)) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
			break;
		case 3:
			System.out.println("请输入要查询商品的ID");
			pid =scanner.next();
			showProduct(pid);
		case 4:
			System.out.println("请输入要修改商品的ID");
			pid =scanner.next();
			showProduct(pid);
		case 0:
			getProductInfoMenu();
			break;
		default:
			break;
		}
		getProductInfoMenu();
	}
	private static void addProduct() {
		// TODO Auto-generated method stub
		Product newProduct =new Product();
		System.out.println("----- 商品上架 -------");
		System.out.println("---请输入商品ID----");
		String pid=scanner.next();
		while(productService.addChk(pid)) {
			System.out.println("---该Id已被占用 请重新输入----");
			pid=scanner.next();
		}
		newProduct.setPid(pid);
		System.out.println("---请输入商品名称----");
		newProduct.setpName(scanner.next());
		System.out.println("---请输入商品价格----");
		newProduct.setPrice(scanner.nextInt());
		System.out.println("---请输入商品介绍----");
		newProduct.setRemark(scanner.next());
		System.out.println("---请输入库存----");
		newProduct.setNums(scanner.nextInt());
		newProduct.setCreateAdmin(user.getucode());
		newProduct.setCreateTime(new Date());
		int  id =productService.getMaxId()+1;
		newProduct.setId(id);
		if(productService.addProduct(newProduct)){		
			System.out.println("上架成功");
		}else {
			System.out.println("上架失败");
		}
		getProductInfoMenu();
	}

	public static void ProductMenu(User nowUser) {
		user=nowUser;
		products = productService.getProductInfo();
		getProductMenu();

	}
	private static void getProductMenu() {
		// TODO Auto-generated method stub
		productShow(products);
		productMenu();
	}

	public static void productMenu() {
		// TODO Auto-generated method stub
		System.out.println("1.查询");
		System.out.println("2.搜索");
		System.out.println("3.购买");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		String pid="";
		switch (skipcount) {
		case 1:
			System.out.println("请输入要查询商品的ID");
			pid =scanner.next();
			showProduct(pid);
			break;
		case 2:
			System.out.println("请输入要搜索条件");
			String chk =scanner.next();
			searchProduct(chk);
			break;
		case 3:
			System.out.println("请输入要购买商品的ID");
			pid =scanner.next();
			buyProduct(pid);
			break;
		case 0:
			UserView view = new UserView();
			view.back(user);
			break;
		default:
			break;
		}
		getProductMenu();
	}

	private static void searchProduct(String chk) {
		// TODO Auto-generated method stub
		productShow(productService.searchProduct(chk));
		productMenu();
	}

	private static void buyProduct(String pid) {
		// TODO Auto-generated method stub
		System.out.println("----- 订单信息------");
		Order order =new Order();
		Product product= productService.queryProduct(pid);
		order.setOid(orderService.getMaxId()+1);
		System.out.println("订单ID:"+order.getOid());
		order.setUserID(user.getucode());
		System.out.println("客户代码:"+order.getUcode());
		order.setItems(product);
		System.out.println("订单明细:"+order.getItems().getpName());
		order.setPrice(product.getPrice());
		System.out.println("订单金额:"+order.getPrice());
		order.setCreateTime(new Date());
		System.out.println("订单日期:"+order.getCreateTime());
		order.setAddress(user.getDefaultAddress());
		System.out.println("收货地址:"+order.getAddress());
		System.out.println("请确定订单按y支付n稍后支付");
		String input =scanner.next();
		order.setPayState(input.equals("y")?1:0);
		System.out.println("支付状态:"+(order.getPayState()==1?"已支付":"未支付"));
		
		System.out.println("订单提交中....");
		if(orderService.addOrder(order)) {
			System.out.println("订单创建成功");
			if(productService.buyProduct(order, user.getucode())) {
				
			} 
		}else {
			System.out.println("订单创建失败");
		}
	}

	private static void showProduct(String pid) {
		// TODO Auto-generated method stub
		Product product  = productService.queryProduct(pid);
		System.out.println("----- 商品信息------");
		System.out.println("商品id:"+product.getPid());
		System.out.println("商品名称:"+product.getpName());
		System.out.println("商品价格:"+product.getPrice());
		System.out.println("商品介绍:"+product.getRemark());
		System.out.println("库存:"+product.getNums());
		System.out.println("上架人员:"+product.getCreateAdmin());
		System.out.println("上架时间:"+product.getCreateTime());
		if(user.getState()==1) {
			updateProduct(product);
		}else {
			System.out.println("1.立即下单");
			System.out.println("0.返回上一级");
			int skipcount =scanner.nextInt();
			switch (skipcount) {
			case 1:
				buyProduct(product.getPid());
				break;
			case 0:
				getProductMenu();
			}
		}
		
		
	}

	private static void updateProduct(Product product) {
		// TODO Auto-generated method stub
		System.out.println("1.修改商品id");
		System.out.println("2.修改商品名称");
		System.out.println("3.修改商品价格");
		System.out.println("4.修改商品介绍");
		System.out.println("5.修改库存");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		switch (skipcount) {
		case 1:
			System.out.println("请输入新的商品id");
			product.setPid(scanner.next());
			break;
		case 2:
			System.out.println("请输入新的商品名称");
			product.setpName(scanner.next());
			break;
		case 3:
			System.out.println("请输入新的商品价格");
			product.setPrice(scanner.nextInt());
			break;
		case 4:
			System.out.println("请输入新的商品介绍");
			product.setRemark(scanner.next());
			break;
		case 5:
			System.out.println("请输入新的库存数量");
			product.setNums(scanner.nextInt());
			break;
		case 0:
			UserView view = new UserView();
			view.back(user);
			break;
		default:
			break;		
		}
		if(productService.updateProduct(product)) {
			System.out.println("修改成功！");

		}else {
			System.out.println("修改失败！");
		}
		productMenu();
	}

	private static void productShow(Product[] products) {
		// TODO Auto-generated method stub
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		System.out.println("----- 商品列表------");
		System.out.println("________________________________________________________________________________________________________________________________________________________ ");
		System.out.println("	 	|	商品ID	|	商品名称			|		价格			|	库存	|		上架时间		|");

		System.out.println("________________|_______________|_______________________________|_______________________________________|_______________|______________________________ |");
		
		for (int i = 0; i <products.length; i++) {
			if(products[i]!=null) {
			System.out.println(String.format("	%d	|%s		|%s			|		%f		|	%d	|	%s	|",
												i+1,products[i].getPid(),products[i].getpName(),products[i].getPrice(),
												products[i].getNums(),format.format(products[i].getCreateTime())));				
			}	
		}
		System.out.println("________________|_______________|_______________________________|_______________________________________|_______________|______________________________ |");
	}

}
