package life.dashyeah.CloudIntelligence.Data;

import java.io.File;

public class OrgOpt {
	/**
	 * option
	 */
	private String opt; 
	
	private String id;
	private String name;
	private String type;
	private String introduction;
	
	private File background;
	private String backgroundFileName;
	private String backgroundContentType;
	
	private File logo;
	private String logoFileName;
	private String logoContentType;
	
	private File pic;
	private String picFileName;
	private String picContentType;
	
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

	public File getBackground() {
		return background;
	}

	public void setBackground(File background) {
		this.background = background;
	}

	public String getBackgroundFileName() {
		return backgroundFileName;
	}

	public void setBackgroundFileName(String backgroundFileName) {
		this.backgroundFileName = backgroundFileName;
	}

	public String getBackgroundContentType() {
		return backgroundContentType;
	}

	public void setBackgroundContentType(String backgroundContentType) {
		this.backgroundContentType = backgroundContentType;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	
}
