package life.dashyeah.CloudIntelligence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import life.dashyeah.CloudIntelligence.Data.CUOpt;

public class ChapterAndUtilMgr extends ActionSupport implements ModelDriven<CUOpt> {

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
	private CUOpt opt;
	/**
	 * return data
	 */
	private Map<String, Object> dataMap;
	
	public ChapterAndUtilMgr() {
		conn = DBConn.getConn();
		opt = new CUOpt();
		dataMap = new HashMap<String, Object>();
	}
	
	public String manage() {
		String sql;
		Map<String, Object> chaps = new HashMap<String, Object>();
		
		dataMap.clear();
		if(!CUOpt.checkOpt(opt.getOpt()) || !CUOpt.checkOprand(opt.getOprand())) {
			dataMap.put("status", "ERROR");
			dataMap.put("info", "Bad Option! Please check 'opt' or 'oprand'.");
			return SUCCESS;
		}
		
		switch(opt.getOpt()) {
		case "list":
			try {
				opt.getBelongId().equals("");
			}catch(Exception e) {
				dataMap.put("status", "ERROR");
				dataMap.put("info", "belong ID not given!");
				return SUCCESS;
			}
			chaps.clear();
			if(opt.getOprand().equals("chapter"))
				sql =  "select chapterId,chapterTitle,chapterName "+
					   "from chapter where belongCourseId="+opt.getBelongId();
			else
				sql =  "select utilId,utilTitle,utilName "+
						   "from util where belongChapterId="+opt.getBelongId();
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Map<String, Object> chap = new HashMap<String, Object>();
					if(opt.getOprand().equals("chapter")) {
						chap.put("id", rs.getString("chapterId"));
						chap.put("title", rs.getString("chapterTitle"));
						chap.put("name", rs.getString("chapterName"));
						chaps.put(rs.getString("chapterTitle"), chap);
					}else {
						chap.put("id", rs.getString("utilId"));
						chap.put("title", rs.getString("utilTitle"));
						chap.put("name", rs.getString("utilName"));
						chaps.put(rs.getString("utilTitle"), chap);
					}
				}
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("chaputils", chaps);
			dataMap.put("status", "OK");
			break;
		case "add":
			try {
				//opt.getId().equals("");
				opt.getName().equals("");
				opt.getTitle().equals("");
				opt.getBelongId().equals("");
			}catch(Exception e) {
				dataMap.put("status", "ERROR");
				dataMap.put("info", "attribute not given!");
				return SUCCESS;
			}
			chaps.clear();
			if(opt.getOprand().equals("chapter"))
				sql =  "insert into chapter(chapterName, chapterTitle, belongCourseId)";
			else
				sql =  "insert into util(utilId, utilName, utilTitle, belongChapetrId)";
			sql += " values('"+
					opt.getName()+"', '"+
		            opt.getTitle()+"', "+
					opt.getBelongId()+")";
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				chaps.put("chaputils", opt.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("chaputils", chaps);
			dataMap.put("status", "OK");
			break;
		case "delete":
			if(opt.getId().equals(null) || opt.getId() == "") {
				dataMap.put("status", "ERROR");
				dataMap.put("info", "ID not given!");
				return SUCCESS;
			}
			chaps.clear();
			if(opt.getOprand().equals("chapter"))
				sql =  "delete from chapter where chapterid="+opt.getId();
			else
				sql =  "delete from util where utilId="+opt.getId();
			System.out.println("[MSG] sql: "+sql);
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				chaps.put("chaputils", opt.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dataMap.put("status", "ERROR");
				dataMap.put("info", "SQL ERROR");
				return SUCCESS;
			}
			dataMap.put("chaputils", chaps);
			dataMap.put("status", "OK");
			break;
		default:
			dataMap.put("status", "ERROR");
			dataMap.put("info", "Bad Option!");
		}
		
		return SUCCESS;
	}
	
	@Override
	public CUOpt getModel() {
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
