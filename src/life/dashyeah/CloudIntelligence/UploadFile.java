package life.dashyeah.CloudIntelligence;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

public class UploadFile extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();;
	
	public String upload() {
		/* Copy file to a safe location */
		destPath = "D:\\Java\\apache-tomcat-8.5.29\\work";

		try {
			System.out.println("Src File name: " + myFile);
			System.out.println("Dst File name: " + myFileFileName);

			System.out.println("myFileContentType: " + myFileContentType);

			File destFile = new File(destPath, myFileFileName);
			FileUtils.copyFile(myFile, destFile);

		} catch (IOException e) {
			e.printStackTrace();
			dataMap.put("status", "ERROR");
			return ERROR;
		}
		
		dataMap.put("status", "OK");
		
		return SUCCESS;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
}
