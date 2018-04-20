package life.dashyeah.CloudIntelligence;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import life.dashyeah.CloudIntelligence.Data.CfgReader;
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
			if(!saveFile()) {
				dataMap.put("status", "ERROR");
				dataMap.put("info", "File Upload ERROR.");
				return SUCCESS;
			}
			
			orgs.clear();
			sql = "insert into schorcom(comName, type, comBackground, comLogo, comPic, comIntroduce) ";
			sql += "values('"+
			       opt.getName()+"', '"+
				   opt.getType()+"', '"+
				   opt.getBackgroundFileName()+"', '"+
				   opt.getLogoFileName()+"', '"+
				   opt.getPicFileName()+"', '"+
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
				orgs.put("id", opt.getId());
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
	
	private boolean saveFile() {
		/* Copy file to a safe location */
		String destPath = CfgReader.getFileRoot();
		
		try {
			/*
			System.out.println("Src File name: " + opt.getLogo());
			System.out.println("Src File name: " + opt.getBackground());
			System.out.println("Src File name: " + opt.getPic());
			
			System.out.println("dst File name: " + opt.getLogoFileName());
			System.out.println("dst File name: " + opt.getBackgroundFileName());
			System.out.println("dst File name: " + opt.getPicFileName());

			System.out.println("myFileContentType: " + opt.getLogoContentType());
			System.out.println("myFileContentType: " + opt.getBackgroundContentType());
			System.out.println("myFileContentType: " + opt.getPicContentType());
			*/
			File destFile = new File(destPath, opt.getLogoFileName());
			FileUtils.copyFile(opt.getLogo(), destFile);
			File destFile1 = new File(destPath, opt.getBackgroundFileName());
			FileUtils.copyFile(opt.getBackground(), destFile1);
			File destFile11 = new File(destPath, opt.getPicFileName());
			FileUtils.copyFile(opt.getPic(), destFile11);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
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
