package index;

import view.ProductView;
import view.UserView;

/**
 * 首页
 * **/
public class Index {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("                       _oo0oo_");
		System.out.println("                      o8888888o");
		System.out.println("                     88\" . \"88");
		System.out.println("                      (| -_- |)");
		System.out.println("                     0\\  =  /0");
		System.out.println("                    ___/`---'\\___");
		System.out.println("                  .' \\|     |// '.");
		System.out.println("                / _||||| -:- |||||- \\");
		System.out.println("               |   | \\\\\\  -  /// |   |");
		System.out.println("               | \\_|  ''\\---/''  |_/ |");
		System.out.println("               \\  .-\\__  '-'  ___/-. /");
		System.out.println("             ___'. .'  /--.--\\  `. .'___");
		System.out.println("          .\"\" '<  `.___\\_<|>_/___.' >' \"\".");
		System.out.println("         | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |");
		System.out.println("         \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /");
		System.out.println("     =====`-.____`.___ \\_____/___.-`___.-'=====");
		System.out.println("                       `=---='");
		System.out.println("                                                ");
		System.out.println("                                                ");
		System.out.println("     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~         ");
		System.out.println("                    佛祖保佑         永无BUG         ");
		//进入首页
		UserView view = new UserView();
		view.login();
		ProductView viewP = new ProductView();
		//y
		//viewP.ProductMenu(view.user);
	}

}
