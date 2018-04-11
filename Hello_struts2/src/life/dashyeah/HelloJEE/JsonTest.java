package life.dashyeah.HelloJEE;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.HashMap;
import java.util.Map;

public class JsonTest extends ActionSupport implements ModelDriven<MyData>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> dataMap;
	private MyData data = new MyData();
	
	public JsonTest() {
		dataMap = new HashMap<String, Object>();
	}
	
	public String react(){
		System.out.println("MSG: react() called!");
		System.out.println("MSG: "+data.getT()+" "+data.getQuestion());
		
		dataMap.clear();
		User user = new User();
		user.setUsername("Admin");
		user.setPassword("123456");
		user.setState("OK");
		
		dataMap.put("user", user);
		dataMap.put("success", true);
		
		return SUCCESS;
	}
	
	public Map<String, Object> getDataMap(){
		return dataMap;
	}

	@Override
	public MyData getModel() {
		// TODO Auto-generated method stub
		return data;
	}
}

class MyData{
	private String t;
	private String question;
	
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
