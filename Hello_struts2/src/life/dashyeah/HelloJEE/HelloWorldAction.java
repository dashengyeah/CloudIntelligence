package life.dashyeah.HelloJEE;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String add() {
		return SUCCESS;
	}
	
	public String update() {
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("Action excuted. -- HelloWorldAction.");
		
		return SUCCESS;
	}

}
