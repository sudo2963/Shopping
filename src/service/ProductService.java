package service;

import pojo.Order;
import pojo.Product;

public interface ProductService {
	//user
	public Product queryProduct(String pid);
	public Product[] searchProduct(String condition );
	public boolean buyProduct(Order order,String uid);
	//admin
	public boolean addProduct(Product product);
	public boolean deleteProduct(String pid);
	public Product[] getProductInfo();
	public boolean updateProduct(Product product);
	public int getMaxId();
	public boolean addChk(String pid);
}
