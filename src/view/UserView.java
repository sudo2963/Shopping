package view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserView {
	
	public static User user;
	
	private UserService userService=new UserServiceImpl();
	
	public static Scanner scanner =new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void login() {
		while(true) {
			System.out.println("----- 登录系统 ------");
			System.out.println("1.登录");
			System.out.println("2.注册");
			System.out.println("0.退出系统");
			int skipcount =scanner.nextInt();
			switch (skipcount) {
			case 1:
				loginMenu();
				break;
			case 2:
				registerMenu();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
				
			}
		}
	}

	public void registerMenu() {
		// TODO Auto-generated method stub
		System.out.println("----- 用户注册 -------");
		System.out.println("---请输入用户代码----");
		String uid=scanner.next();
		while(userService.addChk(uid)) {
			System.out.println("---该代码已被占用 请重新输入----");
			uid=scanner.next();
		}
		System.out.println("---请输入用户名----");
		String uName=scanner.next();
		System.out.println("---请输入密码名----");
		String uPwd=scanner.next();
		System.out.println("---请再次输入密码名----");
		String uPwdChk=scanner.next();
		while(!uPwd.equals(uPwdChk)) {
			System.out.println("---两次密码不同 请重新输入----");
			 uPwdChk=scanner.next();
		}
		System.out.println("---请输入默认收货地址----");
		String udefaultAddress=scanner.next();
		String[] address =new String[5];
		address[0]=udefaultAddress;
		int  id =userService.getMaxId();
		double money =0d;
		int state =0;
		User newUser =new User(id,uid,uName,uPwd,money,new Date(),state,address,udefaultAddress);
		if(userService.addUser(newUser)){		
			System.out.println("注册成功");
			login();
		}else {
			System.out.println("注册失败");
			login();
		}

		
	}
	public void back(User user) {
		this.user= user;
		index();
	}

	public void index() {
		// TODO Auto-generated method stub

		while(true) {
			System.out.println("---------------------- 黑马商城 --------------------------------");
			System.out.println("当前用户："+user.getName()+"	"+(user.getState()==0?"普通用户":"管理员"));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~热销商品~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|a.小米2		|b.小米5			|c.小米10		|");
			System.out.println("|e.IPhoneXR	|f.IPhone13proMAX	|g.MakBook13		|");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("1."+(user.getState()==1?"商品管理":"商品菜单"));
			System.out.println("2."+(user.getState()==1?"订单管理":"我的订单"));
			System.out.println("3.用户信息");		
			System.out.println("4."+(user.getState()==1?"用户管理":"充值"));
			System.out.println("0.退出商城");
				int skipcount =scanner.nextInt();
				switch (skipcount) {
				case 1:
					if(user.getState()==1) {
						ProductView.ProductInfoMenu(user);
						
					}else {
						ProductView.ProductMenu(user);
					}
					break;
				case 2:
					if(user.getState()==1) {
						OrderView.orderMenu();
						
					}else {
						OrderView.userOrderMenu(user);
					}
					break;
				case 3:
					showUser();
					break;
				case 4:
					if(user.getState()==1) {
						userInfoMenu();
						
					}else {
						topupMenu();
					}
					break;
				case 5:
					
					break;
				case 0:
					System.exit(0);
					break;
				default:
					
					break;
				}
			}
	}

	private void userInfoMenu() {
		// TODO Auto-generated method stub
		System.out.println("----- 用户管理------");
		List<User> users =userService.getAllUser();
		System.out.println("__________________________________________________________________________________________________________________________________________________________________");
		System.out.println("	 	|	用户代码	|	用户姓名	|	用户密码	|		用户余额			|		用户默认地址			|	用户属性	|");

		System.out.println("________________|_______________|_______________|_______________|_______________________________________|_______________________________________|_______________|");
		
//		for (int i = 1; i <=userService.getMaxId()+1; i++) {
//			if(users[i]!=null) {
//			System.out.println(String.format("	%d	|	%s	|	%s	|	%s	|		%f		|		%s		|	%s	|",
//												i,users[i].getUid(),users[i].getName(),users[i].getPassword(),
//												users[i].getMoney(),users[i].getDefaultAddress(),(users[i].getState()==0?"普通用户":"管理员")));
//			}
//		}
		for (User user:users) {
			System.out.println(user);
		}
		System.out.println("1.分配管理员");
		System.out.println("2.删除用户");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		String ucode="";
		switch (skipcount) {
		case 1:
			System.out.println("请输入待变更用户的代码");
			ucode =scanner.next();
			if(userService.addAdmin(ucode)) {
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败");
			} 
			break;
		case 2:
			System.out.println("请输入待删除用户的代码");
			ucode  =scanner.next();
			if(userService.deleteUser(ucode)) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
			break;
		case 0:
			index();
			break;
		default:
			break;
		}
		userInfoMenu();
	}

	public void topupMenu() {
		// TODO Auto-generated method stub
		System.out.println("----- 用户充值------");
		System.out.println("当前用户:"+user.getUid());
		System.out.println("当前余额:"+user.getMoney());
		System.out.println("1.50￥");
		System.out.println("2.100￥");
		System.out.println("3.500￥");
		System.out.println("4.1000￥");
		System.out.println("5.自定义金额");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		double money =0;
		switch (skipcount) {
		case 1:
			money=50;
			break;
		case 2:
			money=100;
			break;
		case 3:
			money=500;
			break;
		case 4:
			money=1000;
			break;
		case 5:
			System.out.println("请输入充值金额");
			money =scanner.nextInt();
			break;
		case 0:
			index();
			break;
		default:
			break;
		}
		if(userService.topup(user.getucode(), money)) {
			showUser();
		}
	}

	public void showUser() {
		// TODO Auto-generated method stub
		System.out.println("----- 当前用户信息------");
		System.out.println("id:"+user.getUid());
		System.out.println("用户代码:"+user.getucode());
		System.out.println("姓名:"+user.getName());
		System.out.println("密码:"+user.getPassword());
		System.out.println("余额:"+user.getMoney());
		System.out.println("账户创建时间:"+(Date)user.getCreateDate());
		System.out.println("默认地址:"+user.getDefaultAddress());
		System.out.println("用户状态:"+(user.getState()==0?"普通用户":"管理员"));
		System.out.println("1.修改用户id");
		System.out.println("2.修改用户姓名");
		System.out.println("3.修改密码");
		System.out.println("4.修改默认地址");
		System.out.println("5.添加新地址");
		System.out.println("6.删除新地址");
		System.out.println("0.返回上一级");
		int skipcount =scanner.nextInt();
		String[] address = user.getAddress();
		switch (skipcount) {
		case 1:
			System.out.println("请输入新的用户代码");
			user.setucode(scanner.next());
			break;
		case 2:
			System.out.println("请输入新的用户姓名");
			user.setName(scanner.next());
			break;
		case 3:
			System.out.println("请输入新的密码");
			user.setPassword(scanner.next());
			break;
		case 4:
			for (int i = 0; i < address.length; i++) {
				System.out.println((i+1)+"."+address[i]);
			}
			System.out.println("请选择默认地址");
			int index =scanner.nextInt();
			user.setDefaultAddress(address[index-1]);			
		case 5:
			System.out.println("请输入新的地址");
			if(address==null) {
			String[] newaddress =new String[5];
			newaddress[0] =scanner.next();
			user.setAddress(newaddress);
			}else if(address[address.length-1]==null){
				for (int i = 0; i < address.length; i++) {
					if(address[i]==null) {
						address[i] =scanner.next();
						break;
					}
				}
			}else {
				System.out.println("地址已满，请删除后重新添加");
			}
			
			break;
		case 6:
			for (int i = 0; i < address.length; i++) {
				System.out.println((i+1)+"."+address[i]);
			}
			System.out.println("请选择要删除的地址");
			int deleteindex =scanner.nextInt();
			address[deleteindex-1]= "";
			
			user.setAddress(address);
			break;
		case 0:
			index();
			break;
		default:
			break;		
		}
		if(userService.updateUser(user)) {
			System.out.println("修改成功！");

		}else {
			System.out.println("修改失败！");
		}
		showUser();

	}

	public void loginMenu() {
		// TODO Auto-generated method stub
		System.out.println("第二个测试");
		System.out.println("----- 欢迎登录 ------");
		System.out.println("---请输入用户代码----");
		String uName=scanner.next();
		System.out.println("---请输入用户密码名----");
		String uPwd=scanner.next();
		System.out.println(String.format("您输入的用户代码：%s 密码：****  正在登录中...", uName));
		//login 
		user =userService.login(uName, uPwd);
		if(user!=null) {
			index();
		}else {
			System.out.println("用户代码或密码输入错误");
		}
		

	}

}
