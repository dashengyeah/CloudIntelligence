package life.dashyeah.CloudIntelligence;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import life.dashyeah.CloudIntelligence.Data.CourseOpt;

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
			courses.clear();
			sql = "insert into course(courseName, courseIntroduce) values";
			sql += "( '"+opt.getName()+
				   "','"+opt.getIntroduction()+
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
