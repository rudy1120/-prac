package egovframework.portal.util.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.format.Colour;

/**
 * 엑셀 유틸 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 3. 21.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 3. 21.
 */
public class ExcelUtilConfig {

	/** 파일 이름 */
	private String fileName;
	/** 엑셀 상단 타이틀 */
	private String headerTitle;
	/** 데이터 목록 */
	private List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
	/** 데이터 key 목록 */
	private List<String> dataKeyList = new ArrayList<String>();
	/** 헤더 이름 목록 */
	private List<String> headerNameList = new ArrayList<String>();
	/** 각 셀별 너비 */
	private List<Integer> colSizeList = new ArrayList<Integer>();
	/** 번호 사용여부 */
	private boolean useNum = Boolean.TRUE;
	/** 타이틀 색깔 */
	private Colour titleColor = Colour.IVORY;
	/** 데이터 출력 여부 */
	private boolean dataYn = Boolean.TRUE;

	/** 생성자 **/
	public ExcelUtilConfig() {
	}

	/**
	 * Constructor
	 * @param fileName		파일 이름
	 * @param headerTitle	문서 제목
	 * @param dataList		데이터 리스트
	 * @param dataKeyList	데이터 키 리스트
	 */
	public ExcelUtilConfig(String fileName, String headerTitle, List<Map<String, String>> dataList, List<String> dataKeyList) {
		this.fileName = fileName;
		this.headerTitle = headerTitle;
		this.dataList = dataList;
		this.dataKeyList = dataKeyList;
	}

	/**
	 * Constructor
	 * @param fileName		파일 이름
	 * @param headerTitle	문서 제목
	 * @param dataList		데이터 리스트
	 * @param useNum		데이터 키 리스트
	 */
	public ExcelUtilConfig(String fileName, String headerTitle, List<Map<String, String>> dataList, boolean useNum) {
		this.fileName = fileName;
		this.headerTitle = headerTitle;
		this.dataList = dataList;
		this.useNum = useNum;
	}

	/**
	 * Constructor
	 * @param fileName		파일 이름
	 * @param headerTitle	문서 제목
	 * @param dataList		데이터 리스트
	 * @param useNum		번호 출력 여부
	 * @param dataYn		데이터 출력 여부
	 */
	public ExcelUtilConfig(String fileName, String headerTitle, List<Map<String, String>> dataList, boolean useNum, boolean dataYn) {
		this.fileName = fileName;
		this.headerTitle = headerTitle;
		this.dataList = dataList;
		this.useNum = useNum;
		this.dataYn = dataYn;
	}

	/**
	 * Constructor
	 * @param fileName		파일 이름
	 * @param headerTitle	문서 제목
	 * @param dataList		데이터 리스트
	 * @param dataKeyList	데이터 키 리스트
	 * @param useNum		번호 출력 여부
	 */
	public ExcelUtilConfig(String fileName, String headerTitle, List<Map<String, String>> dataList, List<String> dataKeyList, boolean useNum) {
		this.fileName = fileName;
		this.headerTitle = headerTitle;
		this.dataList = dataList;
		this.dataKeyList = dataKeyList;
		this.useNum = useNum;
	}

	/**
	 * Constructor
	 * @param fileName			파일 이름
	 * @param headerTitle		문서 제목
	 * @param dataList			데이터 리스트
	 * @param dataKeyList		데이터 키 리스트
	 * @param headerNameList	헤더 이름 리스트
	 * @param colSizeList		열 크기 리스트
	 */
	public ExcelUtilConfig(String fileName, String headerTitle, List<Map<String, String>> dataList, List<String> dataKeyList, List<String> headerNameList, List<Integer> colSizeList) {
		this.fileName = fileName;
		this.headerTitle = headerTitle;
		this.dataList = dataList;
		this.dataKeyList = dataKeyList;
		this.headerNameList = headerNameList;
		this.colSizeList = colSizeList;
	}

	/**
	 * Constructor
	 * @param fileName			파일 이름
	 * @param headerTitle		문서 제목
	 * @param dataList			데이터 리스트
	 * @param dataKeyList		데이터 키 리스트
	 * @param headerNameList	헤더 이름 리스트
	 * @param colSizeList		열 크기 리스트
	 * @param useNum			번호 출력 여부
	 */
	public ExcelUtilConfig(String fileName, String headerTitle, List<Map<String, String>> dataList, List<String> dataKeyList, List<String> headerNameList, List<Integer> colSizeList, boolean useNum) {
		this.fileName = fileName;
		this.headerTitle = headerTitle;
		this.dataList = dataList;
		this.dataKeyList = dataKeyList;
		this.headerNameList = headerNameList;
		this.colSizeList = colSizeList;
		this.useNum = useNum;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Map<String, String>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, String>> dataList) {
		this.dataList = dataList;
	}

	public List<String> getHeaderNameList() {
		return headerNameList;
	}

	public void setHeaderNameList(List<String> headerNameList) {
		this.headerNameList = headerNameList;
	}

	public List<Integer> getColSizeList() {
		return colSizeList;
	}

	public void setColSizeList(List<Integer> colSizeList) {
		this.colSizeList = colSizeList;
	}

	public boolean isUseNum() {
		return useNum;
	}

	public void setUseNum(boolean useNum) {
		this.useNum = useNum;
	}

	public String getHeaderTitle() {
		return headerTitle;
	}

	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}

	public List<String> getDataKeyList() {
		return dataKeyList;
	}

	public void setDataKeyList(List<String> dataKeyList) {
		this.dataKeyList = dataKeyList;
	}

	public Colour getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Colour titleColor) {
		this.titleColor = titleColor;
	}

	public boolean getDataYn() {
		return dataYn;
	}

	public void setDataYn(boolean dataYn) {
		this.dataYn = dataYn;
	}

	/**
	 * 엑셀 상단 필드 이름을 추가하는 메소드
	 * @param name
	 */
	public void addHeaderName(String name) {
		this.headerNameList.add(name);
	}

	public void setHeaderNameValues(String values) {
		String[] array = values.trim().split(",");
		for (String value : array) {
			this.headerNameList.add(value);
		}
	}

	/**
	 * 엑셀 열 너비 설정
	 * @param size
	 */
	public void addColSize(int size) {
		this.colSizeList.add(size);
	}

	/**
	 * 헤더 갯수 만큼 기본 너비를 설정
	 */
	public void setDefaultColSize() {
		for (int i = 0; i < this.headerNameList.size(); i++) {
			this.colSizeList.add(20);
		}
	}

	public void setColSizeListValues(String values) {
		String[] array = values.trim().split(",");
		for (String value : array) {
			this.colSizeList.add(Integer.parseInt(value));
		}
	}

	/**
	 * 입력한 값으로 모든 너비를 설정
	 * @param colSize
	 */
	public void setAllColSize(int colSize) {
		for (int i = 0; i < this.headerNameList.size(); i++) {
			this.colSizeList.add(colSize);
		}
	}

	/**
	 * DataKeyList에 key 추가
	 * @param key
	 */
	public void addDataKey(String key) {
		this.dataKeyList.add(key);
	}

	/**
	 * 문자열 , 구분자로 넘어온 값을 DataKeyList에 추가
	 * @param values
	 */
	public void setDataKeyListValues(String values) {
		String[] array = values.trim().split(",");
		for (String value : array) {
			
			this.dataKeyList.add(value);
		}
	}

	/**
	 * 필수 사용값 체크
	 * @return
	 */
	public boolean essentialCheck() {
		if (this.dataList == null) {
			return false;
		}
//		if (this.dataList != null && this.dataList.size() <= 0) {
//			return false;
//		}
//		if (this.headerNameList.size() != this.dataList.size()) {
//			return false;
//		}
//		if (this.colSizeList.size() != this.dataList.size()) {
//			return false;
//		}
		if (this.colSizeList.size() != this.headerNameList.size()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExcelUtilConfig [fileName=" + fileName + ", headerTitle=" + headerTitle + ", dataList=" + dataList + ", dataKeyList=" + dataKeyList + ", headerNameList=" + headerNameList + ", colSizeList=" + colSizeList + ", useNum=" + useNum + ", titleColor=" + titleColor + ", dataYn=" + dataYn + "]";
	}


}