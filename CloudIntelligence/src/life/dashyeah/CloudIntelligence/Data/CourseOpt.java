package life.dashyeah.CloudIntelligence.Data;

public class CourseOpt {
	/**
	 * list, add, delete
	 */
	private String opt;
	/**
	 * operation target id
	 */
	private String id;
	
	private String name;
	private String introduction;

	private static String[] opts = {"list","add","delete"};
	
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public static boolean checkOpt(String opt) {
		for(String o:opts) {
			if(o.equals(opt)) return true;
		}
		return false;
	}
}
