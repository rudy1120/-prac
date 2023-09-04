package egovframework.com.cmm.service;

/**
 * 파일 권한 관리 VO
 * 관리 테이블: comtnfile
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 10. 11.	J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 10. 11.
 */
public class FileMasterVO {

	/** 첨부파일 ID */
	private String atchFileId = "";
	/** 파일 권한 검증 여부(Y: 검증, N: 미검증) */
	private String authYn = "";
	/** 권한 관리 주체 테이블명 */
	private String tableName = "";
	/** tableName PK 관리 컬럼명 */
	private String idxKey = "";
	/** atchFileId를 사용 중인 PK값 */
	private String idxValue = "";
	/** di 관리 컬럼명 */
	private String diKey = "";

	public FileMasterVO() {
		// default
	}

	public FileMasterVO(String atchFileId) {
		setAtchFileId(atchFileId);
	}

	public FileMasterVO(String atchFileId, String authYn, String tableName, String idxKey, String idxValue, String diKey) {
		setAtchFileId(atchFileId);
		setAuthYn(authYn);
		setTableName(tableName);
		setIdxKey(idxKey);
		setIdxValue(idxValue);
		setDiKey(diKey);
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getAuthYn() {
		return authYn;
	}

	public void setAuthYn(String authYn) {
		this.authYn = authYn;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdxKey() {
		return idxKey;
	}

	public void setIdxKey(String idxKey) {
		this.idxKey = idxKey;
	}

	public String getIdxValue() {
		return idxValue;
	}

	public void setIdxValue(String idxValue) {
		this.idxValue = idxValue;
	}

	public String getDiKey() {
		return diKey;
	}

	public void setDiKey(String diKey) {
		this.diKey = diKey;
	}

}
