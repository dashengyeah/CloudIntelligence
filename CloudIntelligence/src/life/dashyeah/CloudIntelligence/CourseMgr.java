package life.dashyeah.CloudIntelligence;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import life.dashyeah.CloudIntelligence.Data.CourseOpt;
import life.dashyeah.CloudIntelligence.Data.CfgReader;

public class CourseMgr extends ActionSupport implements ModelDriven<CourseOpt>{

	/**
	 * default UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * DB connection
	 */
	private Connection conn = null;
	
	/**
	 * opt parameters
	 */
	private CourseOpt opt;
	
	/**
	 * return data
	 */
	private Map<String, Object> dataMap;
	
	public CourseMgr(){
		conn = DBConn.getConn();
		opt = new CourseOpt();
		dataMap = new HashMap<String, Object>();
	}
	
	public String manage() {
		String sql;
		Map<String, Object> courses = new HashMap<String, Object>();
		
		dataMap.clear();
		if(!CourseOpt.checkOpt(opt.getOpt())) {
			dataMap.put("status", "ERROR");
			dataMap.put("info", "Bad Option!");
			return SUCCESS;
		}
		
		switch(opt.getOpt()) {
		case "list":
			courses.clear();
			sql = "SELECT * FROM course;";
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Map<String, Object> course = new HashMap<String, Object>();
					course.put("id", rs.getString("courseId"));
					course.put("name", rs.getString("courseName"));
					course.put("introduction", rs.getString("courseIntroduce"));
					course.put("post", rs.getString("coursePostSrc"));
					course.put("school", rs.getString("belongSchName"));
					course.put("teacher", rs.getString("teacher"));
					course.put("introvideo", rs.getString("introduceVideoSrc"));
					course.put("type", rs.getString("courseType"));
					
					courses.put(rs.getString("courseId"), course);
				}
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("courses", courses);
			dataMap.put("status", "OK");
			break;
		case "add":
			if(!saveFile()) {
				dataMap.put("status", "ERROR");
				dataMap.put("info", "File Upload ERROR.");
				return SUCCESS;
			}
			courses.clear();
			sql = "insert into course(courseName, courseIntroduce, courseType, coursePostSrc, introduceVideoSrc) values";
			sql += "( '"+opt.getName()+
				   "','"+opt.getIntroduction()+
				   "','"+opt.getType()+
				   "','"+opt.getPostFileName()+
				   "','"+opt.getVideoFileName()+
				   "');";
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				courses.put(opt.getId(), opt.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("courses", courses);
			dataMap.put("status", "OK");
			break;
		case "delete":
			courses.clear();
			sql = "delete from course where courseId="+opt.getId();
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				courses.put(opt.getId(), opt.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("courses", courses);
			dataMap.put("status", "OK");
			break;
		default:
			dataMap.put("status", "ERROR");
			dataMap.put("info", "Bad Option!");
		}
		
		return SUCCESS;
	}

	private boolean saveFile() {
		String destPath = CfgReader.getFileRoot();
		try {
			/*
			System.out.println("Src File name: " + opt.getPost());
			System.out.println("Src File name: " + opt.getVideo());
			
			System.out.println("dst File name: " + opt.getPostFileName());
			System.out.println("dst File name: " + opt.getVideoFileName());
			
			System.out.println("myFileContentType: " + opt.getPostContentType());
			System.out.println("myFileContentType: " + opt.getVideoContentType());
			*/
			File destFile = new File(destPath, opt.getPostFileName());
			FileUtils.copyFile(opt.getPost(), destFile);
			File destFile1 = new File(destPath, opt.getVideoFileName());
			FileUtils.copyFile(opt.getVideo(), destFile1);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public CourseOpt getModel() {
		// TODO Auto-generated method stub
		return opt;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
}
