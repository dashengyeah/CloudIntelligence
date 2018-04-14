package life.dashyeah.CloudIntelligence.Data;

public class OrgOpt {
	/**
	 * option
	 */
	private String opt; 
	
	private String id;
	private String name;
	private String type;
	private String background;
	private String logo;
	private String pic;
	private String introduction;
	
	/**
	 * Available options
	 */
	private static String[] opts= {"list","add","delete"};
	public static boolean checkOpt(String opt) {
		for(String o:opts) {
			if(o.equals(opt)) return true;
		}
		return false;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
}
