package life.dashyeah.CloudIntelligence.Data;

public class TrainingOpt {
	/**
	 * option
	 */
	private String opt;
	
	private String id;
	private String name;
	private String content;
	private String course;
	private String releasedate;
	private String deadline;
	/**
	 * Available options
	 */
	private static String[] opts = {"list","add","delete"};
	public static boolean checkOpt(String opt) {
		for(String o:opts) {
			if(o.equals(opt)) return true;
		}
		return false;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
}
