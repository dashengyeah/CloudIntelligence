package life.dashyeah.CloudIntelligence.Data;

/**
 * @author Dash Wong <br />
 * 
 * @Note this should be <b>only</b> a configuration,
 * so, you are not expected to get instance of it.
 */
public final class CfgReader {
	private static String fileRoot = "../webapps/CloudIntelligence/";
	private static String imgFileRoot = "../webapps/CloudIntelligence/img/";
	private static String videoFileRoot = "../webapps/CloudIntelligence/video/";
	
	{
		/**
		 * get configuration from file
		 */
		
	}
	
	private CfgReader() {}

	public static String getFileRoot() {
		return fileRoot;
	}

	public static String getImgFileRoot() {
		return imgFileRoot;
	}

	public static String getVideoFileRoot() {
		return videoFileRoot;
	}
}
