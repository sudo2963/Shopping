package pojo;

import java.util.Date;

public class Product {
	private int id ;
	private String pid ;
	private String pName ;
	private double price ;
	private String remark ;
	private int nums ;
	private Date createTime ;
	private String createAdmin;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public Product(int id, String pid, String pName, double price, String remark, int nums, Date createTime,String createAdmin) {
		super();
		this.id = id;
		this.pid = pid;
		this.pName = pName;
		this.price = price;
		this.remark = remark;
		this.nums = nums;
		this.createTime = createTime;
		this.createAdmin=createAdmin;
	}
	public Product() {
		super();
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateAdmin() {
		return createAdmin;
	}
	public void setCreateAdmin(String createAdmin) {
		this.createAdmin = createAdmin;
	}
}
