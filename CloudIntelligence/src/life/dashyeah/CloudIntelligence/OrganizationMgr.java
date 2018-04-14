package life.dashyeah.CloudIntelligence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import life.dashyeah.CloudIntelligence.Data.OrgOpt;

public class OrganizationMgr extends ActionSupport implements ModelDriven<OrgOpt> {

	/**
	 * default UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * DB connection
	 */
	private Connection conn = null;
	/**
	 * options
	 */
	private OrgOpt opt;
	/**
	 * return data
	 */
	private Map<String, Object> dataMap;
	
	public OrganizationMgr() {
		conn = DBConn.getConn();
		opt = new OrgOpt();
		dataMap = new HashMap<String, Object>();
	}
	
	public String manage() {
		String sql;
		Map<String, Object> orgs = new HashMap<String, Object>();
		
		dataMap.clear();
		if(!OrgOpt.checkOpt(opt.getOpt())) {
			dataMap.put("status", "ERROR");
			dataMap.put("info", "Bad Option! Please check 'opt' or 'oprand'.");
			return SUCCESS;
		}
		
		switch(opt.getOpt()) {
		case "list":
			sql = "select * from schorcom";
			orgs.clear();
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Map<String, Object> org = new HashMap<String, Object>();
					org.put("id", rs.getString("comId"));
					org.put("name", rs.getString("comName"));
					org.put("type", rs.getString("type"));
					org.put("background", rs.getString("comBackground"));
					org.put("logo", rs.getString("comLogo"));
					org.put("pic", rs.getString("comPic"));
					org.put("introduction", rs.getString("comIntroduce"));
					
					orgs.put(rs.getString("comId"), org);
				}
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("organizations", orgs);
			dataMap.put("status", "OK");
			break;
		case "add":
			orgs.clear();
			sql = "insert into schorcom(comName, type, comBackground, comIntroduce) ";
			sql += "values('"+
			       opt.getName()+"', '"+
				   opt.getType()+"', '"+
			       opt.getBackground()+"', '"+
				   opt.getIntroduction()+"');";
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				orgs.put("name", opt.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("organizations", orgs);
			dataMap.put("status", "OK");
			break;
		case "delete":
			orgs.clear();
			sql = "delete from schorcom where comId="+opt.getId();
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				orgs.put("name", opt.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("organizations", orgs);
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
	public OrgOpt getModel() {
		// TODO Auto-generated method stub
		return opt;
	}
}
