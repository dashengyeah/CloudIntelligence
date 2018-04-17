package life.dashyeah.CloudIntelligence;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//import java.sql.Connection;

import life.dashyeah.CloudIntelligence.Data.User;

public class Login extends ActionSupport implements ModelDriven<User>{

	/**
	 * auto generated ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * receiving data.
	 */
	private User user = new User();
	
	//private Connection conn = null;
	
	/**
	 * return data
	 */
	private Map<String, Object> dataMap;
	
	public String login() {
		
		dataMap.clear();
		if(user.getUsername().equals("admin") && user.getPassword().equals("pass")) {
			System.out.print("[MSG] Accepted.");
			dataMap.put("status", "OK");
		}else {
			System.out.print("[MSG] Denied.");
			dataMap.put("status", "ERROR");
		}
		
		System.out.println(" -- user:"+user.getUsername()+" password:"+user.getPassword());
		
		return SUCCESS;
	}
	
	public Login() {
		dataMap = new HashMap<String, Object>();
		//conn = DBConn.getConn();
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
