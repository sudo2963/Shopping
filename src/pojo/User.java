package pojo;

import java.util.Date;

/**
 * 模型
 * **/
public class User {
	private int uid;
	private String ucode;
	private String name;
	private String password;
	private double money;
	private Date createDate;
	private int state;
	private String[] address;
	private String defaultAddress;
	public User() {
		
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		String res= (String.format("	%d	|	%s	|	%s	|	%s	|		%f		|		%s		|	%s	|",
				uid,this.ucode,this.name,this.password,
				this.money,this.createDate.toString(),(this.state==0?"普通用户":"管理员")));
		return res;
			
	}



	public User(int uid,String ucode, String name, String password, double money,Date createDate, int state,String[] address,String defaultAddress) {
		super();
		// TODO Auto-generated constructor stub
		this.uid =uid;
		this.ucode=ucode;
		this.name =name;
		this.password =password;
		this.money =money;
		this.createDate=createDate;
		this.state =state;
		this.address =address;
		this.defaultAddress =defaultAddress;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getucode() {
		return ucode;
	}
	public void setucode(String ucode) {
		this.ucode = ucode;
	}



	public String[] getAddress() {
		return address;
	}



	public void setAddress(String[] address) {
		this.address = address;
	}



	public String getDefaultAddress() {
		return defaultAddress;
	}



	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

}
