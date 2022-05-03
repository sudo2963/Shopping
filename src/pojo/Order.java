package pojo;

import java.util.Date;

public class Order {
	
	private int oid;//订单ID
	private String uCode;//客户id
	private Product items; //订单明细
	private double price; //订单金额
	private Date createTime ; //订单日期
	private int payState;//订单状态 1已支付  0 支付中 2已经取消
	private String address;//收货地址
	public String getAddress() {
		return address;
	}
	public int getOid() {
		return oid;
	}
	public Order() {

	}
	public Order(int oid, String uCode, Product items, double price, Date createTime, int payState, String address) {
		super();
		this.oid = oid;
		this.uCode = uCode;
		this.items = items;
		this.price = price;
		this.createTime = createTime;
		this.payState = payState;
		this.address = address;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getUcode() {
		return uCode;
	}
	public void setUserID(String uCode) {
		this.uCode = uCode;
	}
	public Product getItems() {
		return items;
	}
	public void setItems(Product items) {
		this.items = items;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getPayState() {
		return payState;
	}
	public void setPayState(int payState) {
		this.payState = payState;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
