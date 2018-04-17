package life.dashyeah.CloudIntelligence.Data;

import java.io.File;

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
	private String type;
	
	private File post;
	private String postFileName;
	private String postContentType;
	
	private File video;
	private String videoFileName;
	private String videoContentType;
	
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public File getPost() {
		return post;
	}
	public void setPost(File post) {
		this.post = post;
	}
	public String getPostFileName() {
		return postFileName;
	}
	public void setPostFileName(String postFileName) {
		this.postFileName = postFileName;
	}
	public String getPostContentType() {
		return postContentType;
	}
	public void setPostContentType(String postContentType) {
		this.postContentType = postContentType;
	}
	public File getVideo() {
		return video;
	}
	public void setVideo(File video) {
		this.video = video;
	}
	public String getVideoFileName() {
		return videoFileName;
	}
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}
	public String getVideoContentType() {
		return videoContentType;
	}
	public void setVideoContentType(String videoContentType) {
		this.videoContentType = videoContentType;
	}
}
