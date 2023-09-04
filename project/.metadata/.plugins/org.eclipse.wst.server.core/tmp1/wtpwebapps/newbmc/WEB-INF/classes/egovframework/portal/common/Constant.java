package egovframework.portal.common;

public class Constant {

	public final static boolean DEBUG = true;
	public final static String PROJECT_PATH = "/";

	/* File Upload Path */

	public final static String UPLOAD_DIR = "D:\\work\\portal\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\projectWebBasic\\uploadFiles\\"; // 테스트
	public final static String BBS_FILE_DIR_KEY = "Globals.BBSfileStorePath";

	/* PAGING DEFAULT- */

	public final static int BANNER_LIST_CUTPAGE = 16;
	public final static int LIST_CUTPAGE = 10;
	public final static int LIST_CUTRECORD = 10;
	public final static int CLIENT_LIST_CUTRECORD = 20;
	public final static int ADMIN_LIST_CUTRECORD = 20;
	public final static int MAYOR_LIST_CUTPAGE = 5;
	public final static int RESPONSIVE_LIST_CUTPAGE = 4;
	public final static int PUBC_LIST_CUTRECORD = 15;

	/* PAGING THUMBNAIL */
	public final static int THUMB_LIST_CUTPAGE = 10;
	public final static int THUMB_LIST_CUTRECORD = 12;

	/* PAGING - IMAGE게시판 */
	public final static int IMAGE_LIST_CUTPAGE = 10;
	public final static int IMAGE_LIST_CUTRECORD = 12;
	/* - PAGING */

	/* 포토갤러리 THUMNAIL SIZE - */
	public final static int THUMBNAIL_W = 150;
	public final static int THUMBNAIL_H = 100;

	/* 공공데이터 시트 조회시 사이즈 */
	public final static int DATAOPEN_SHEET_LIST_CUTPAGE = 15;

	public final static String SERVER_HOST = "http://localhost";

	/** 관리자 액세스 레벨 */
	public static final int SUPER_ADMIN_ACCESS_LEVEL = 0;

	/**  */
	public static final String[] IGNORE_PROPERTIES = { "searchType", "searchTxt", "page", "searchDept", "searchCategory", "tablePrefix", "scCategory", "scType" };

	/**  */
	public static final String[] IGNORE_FILE_NAMES = { "cMovie", "cBigImg" };

	public static final String AES_KEY = "Globals.aes.key";
	public static final String FULL_DOMAIN = "Globals.full.domain";

	/* ===================================== PROPERTY KEY ===================================== */

	public static final String PROP_FILE_SAVE = "Globals.tmp.file.save.dir";
	public static final String DOMAIN = "Globals.portal.domain";
	public static final String SHORT_DOMAIN = "Globals.portal.short.domain";
	public static final String CITY_CODE = "Globals.portal.city.code";
	public static final String PROP_STAT_DOWNLOAD_PATH = "Globals.stat";
	public static final String DYNAMIC_FILE_SAVE_KEY = "Globals.dynamicFileUpload";
	public static final String PERMIT_FILE_EXT_KEY = "Globals.fileExtWhiteList";

	/* ===================================== USER ===================================== */

	public static final String PW_RANGE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String PW_RANGE_NUMBERS = "0123456789";
	public static final String PW_RANGE_SPECIAL_CHARACTERS = "~!@#$%^*?_+=;:";

	/* ===================================== USER PASSWORD ===================================== */

	public static final int PW_MIN_LENGTH = 9;
	public static final String NUMBER_REG = ".*[0-9]+.*";
	public static final String ALPH_REG = ".*[a-zA-Z]+.*";
	public static final String COLUMN_REG = "^[a-zA-Z0-9_]+$";
	public static final String SPECIAL_REG = ".*[~!@#$%^*?_+=;:]+.*";

	/* ===================================== SOCIAL PROPERTY KEY ===================================== */

	public static final String FACEBOOK_ACCESS_TOKEN = "facebook.accessToken";
	public static final String TWITTER_CONSUMER_KEY = "twitter.consumer.key";
	public static final String TWITTER_CONSUMER_SECRET = "twitter.consumer.secret";
	public static final String TWITTER_ACCESS_TOKEN = "twitter.access.token";
	public static final String TWITTER_ACCESS_TOKEN_SECRET = "twitter.access.token.secret";

	/* ===================================== ETC ===================================== */

	public static final int FAILED_LOGIN_LIMIT = 5;
	public static final String EXCEL_UPLOAD = "Globals.excelUploadDir";
	public static final String HMS_CITY_CODE = "Globals.hms.city.code";
	public static final String LIVE_SERVER_IPS = "Globals.live.server.ips";
	public static final String SYS_MENU_ID_BOARD = "Globals.sys.menu.id.board";
	public static final String PROMOTION_FILE_SAVE_DIR_KEY = "Globals.promotionFileUplode";
	public static final String EDITOR_FILE_SAVE_DIR_KEY = "Globals.editorFileUplode";
	public static final String USER_LOGIN_MID = "Globals.user.loin.mid";
	public static final String USER_MODIFY_MID = "Globals.user.modify.mid";
	public static final String USE_CMS_YN = "Globals.use.cms.yn";

}
