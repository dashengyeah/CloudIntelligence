package life.dashyeah.HelloJEE;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Login extends ActionSupport implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2745288749567779911L;
	
	private User user = new User();

	public String login() {
		
		System.out.println(user.getUsername()+" "+user.getPassword());
		
		return SUCCESS;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
}
