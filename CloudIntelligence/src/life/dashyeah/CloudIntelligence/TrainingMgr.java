package life.dashyeah.CloudIntelligence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import life.dashyeah.CloudIntelligence.Data.TrainingOpt;

public class TrainingMgr extends ActionSupport implements ModelDriven<TrainingOpt> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * DB connection
	 */
	private Connection conn = null;
	private TrainingOpt opt;
	/**
	 * return data
	 */
	private Map<String, Object> dataMap;
	
	public TrainingMgr() {
		conn = DBConn.getConn();
		opt = new TrainingOpt();
		dataMap = new HashMap<String, Object>();
	}
	
	public String manage() {
		String sql;
		Map<String, Object> trainings = new HashMap<String, Object>();
		
		dataMap.clear();
		if(!TrainingOpt.checkOpt(opt.getOpt())) {
			dataMap.put("status", "ERROR");
			dataMap.put("info", "Bad Option! Please check 'opt' or 'oprand'.");
			return SUCCESS;
		}
		switch(opt.getOpt()) {
		case "list":
			sql = "select * from training";
			trainings.clear();
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Map<String, Object> training = new HashMap<String, Object>();
					training.put("id", rs.getString("id"));
					training.put("name", rs.getString("name"));
					training.put("content", rs.getString("content"));
					training.put("course", rs.getString("course"));
					training.put("releasedate", rs.getString("releasedate"));
					training.put("deadline", rs.getString("deadline"));
					
					trainings.put(rs.getString("id"), training);
				}
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("trainings", trainings);
			dataMap.put("status", "OK");
			break;
		case "add":
			trainings.clear();
			sql = "insert into training(course, name, content, releasedate, deadline)";
			sql += "values("+
			       opt.getCourse()+", '"+
				   opt.getName()+"', '"+
			       opt.getContent()+"', '"+
				   opt.getReleasedate()+"', '"+
			       opt.getDeadline()+"')";
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				trainings.put("name", opt.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("trainings", trainings);
			dataMap.put("status", "OK");
			break;
		case "delete":
			trainings.clear();
			sql = "delete from trainings where id="+opt.getId();
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				trainings.put("name", opt.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("trainings", trainings);
			dataMap.put("status", "OK");
			break;
		default:
			dataMap.put("status", "ERROR");
			dataMap.put("info", "Bad Option!");
		}
		
		return SUCCESS;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	@Override
	public TrainingOpt getModel() {
		// TODO Auto-generated method stub
		return opt;
	}
}
