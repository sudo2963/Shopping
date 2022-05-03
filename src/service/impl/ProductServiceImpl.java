package service.impl;

import java.util.Date;

import pojo.Order;
import pojo.Product;
import service.ProductService;
import service.UserService;

public class ProductServiceImpl implements ProductService {
		private static Product[] products = new Product[10];
	
		static {
			products[0] =new Product(0,"p001","小米2青春派",5555,"小米，年轻人的第一款手机",999,new Date(),"admin");
			products[1] =new Product(1,"p002","小米5纵享丝滑",6666,"小米，年轻人的第一款手机",999,new Date(),"admin");
			products[2] =new Product(2,"p003","小米10至尊选择",7777,"小米，年轻人的第一款手机",99,new Date(),"admin");
			products[3] =new Product(3,"p004","IPhoneXR",6666,"苹果天下第一",999,new Date(),"admin");
			products[4] =new Product(4,"p005","IPhone13proMAX",9999,"苹果天下第一",99,new Date(),"admin");
			products[5] =new Product(5,"p006","MakBook13",13999,"苹果天下第一",99,new Date(),"admin");

		}

	private UserService userService=new UserServiceImpl();
	@Override
	public Product queryProduct(String pid) {
		// TODO Auto-generated method stub
		for (int i = 0; i < products.length; i++) {
			if(products[i]!=null&&products[i].getPid().equals(pid)) {
				return products[i];
			}
		}
		return null;
	}

	@Override
	public Product[] searchProduct(String condition) {
		// TODO Auto-generated method stub
		int index=0;
		for (int i = 0; i < products.length; i++) {
			if(products[i]!=null&&products[i].getpName().toString().contains(condition)) {
				index++;
			}
		}
		Product[] res =new Product[index];
		index =0;
;		for (int i = 0; i < products.length; i++) {
			if(products[i]!=null&&products[i].getpName().toString().contains(condition)) {
				res[index]= products[i];
				index++;
			}
		}
		return res;
	}

	@Override
	public boolean buyProduct(Order order, String uid) {
		// TODO Auto-generated method stub
		boolean productChk =false;
		Product np =new Product();
		for (int i = 0; i < products.length; i++) {
			if(products[i]!=null&&products[i].getPid().equals(order.getItems().getPid())) {
				if(products[i].getNums()>0) {
					products[i].setNums(products[i].getNums()-1);;
					np=products[i];
					productChk = true;
				}else {
					System.out.println("库存不足，请等待管理员入库");
				}
			}
		}
		boolean UserChk =false;
		if(order.getPayState()==1) {
			if(userService.userBuy(uid,np)) {
				UserChk =true;
			}else {
				UserChk =false;
			}
		}else {
			UserChk =true;
		}
		if(UserChk==true&&productChk==true) {
			return true;
		}
		return false;
	}

	
	private void identity() {
		// TODO Auto-generated method stub
		Product[] nProduct =new Product[products.length*2];
		for(int i=0;i<nProduct.length-1;i++) {
			nProduct[i]=products[i];
		}
		products=nProduct;
	}
	public int getMaxId() {
		int max =0;
		for (int i = 0; i < products.length; i++) {
			if(products[i]!=null&&products[i].getId()>max) {
				max=products[i].getId();
			}
		}
		return max;
		
	}
	@Override
	public boolean deleteProduct(String pid) {
		int res= 0;
		int count=getMaxId();
		for(int i=0;i<products.length;i++) {
			if(products[i]!=null&&products[i].getPid().equals(pid)){
				res=i;
				break;
			}else {
				return false;
			}
		}
		
		if(res>=0) {
			for (int i =res ; i <count+1 ; i++) {
				Product temp =products[i];
				products[i]=products[i+1];
				products[i+1]=temp;	
			}
			products[count]=null;
			return true;
		}
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		if(product==null) {
			return false;
		}
		for (int i = 0; i < products.length; i++) {
			if(products[i]!=null&&products[product.getId()]!=null&&!products[i].getPid().equals(product.getPid())&&!products[i].getpName().equals(product.getpName())) {
				products[product.getId()]=product;
				return true;
			}
		}

		return false; 
	}

	@Override
	public boolean addProduct(Product newproduct) {
		// TODO Auto-generated method stub
		int i =newproduct.getId();
		if(i>products.length-1) {
			identity();
		}
		products[i] =newproduct;
		return true;
	}

	@Override
	public Product[] getProductInfo() {
		// TODO Auto-generated method stub
		return products;
	}

	@Override
	public boolean addChk(String pid) {
		// TODO Auto-generated method stub
		for (int i = 0; i < products.length; i++) {
			if(products[i]!=null&&products[i].getPid().equals(pid)) {
				return true;
			}
		}
		return false;
	}

}
