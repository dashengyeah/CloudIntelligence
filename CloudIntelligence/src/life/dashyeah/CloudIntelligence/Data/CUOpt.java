package life.dashyeah.CloudIntelligence.Data;

public class CUOpt {
	/**
	 * option
	 */
	private String opt;
	/**
	 * operate chapter or util
	 */
	private String oprand;
	/**
	 * chapter/util ID
	 */
	private String id;
	private String title;
	private String name;
	private String belongId;
	
	private static String[] opts = {"list", "add", "delete"};
	private static String[] oprands = {"chapter", "util"};
	
	public static boolean checkOpt(String opt) {
		for(String o:opts) {
			if(o.equals(opt))
				return true;
		}
		return false;
	}
	public static boolean checkOprand(String opt) {
		for(String o:oprands) {
			if(o.equals(opt))
				return true;
		}
		return false;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBelongId() {
		return belongId;
	}
	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}	
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getOprand() {
		return oprand;
	}

	public void setOprand(String oprand) {
		this.oprand = oprand;
	}
}
