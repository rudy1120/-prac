package egovframework.portal.unit.bmc.buy.vo;

import egovframework.portal.common.vo.CommonVO;
/*
 * 분양매물 상세정보 VO
 * 
 * */
public class SellVO extends CommonVO{

	/*idx*/
	private int idx;
	
	/*지역*/
	private String step1;
	
	/*용도*/
	private String step2;
	
	/*사업*/
	private String step3;
	
	/*사업명*/
	private String name;
	
	/*위치*/
	private String zone;
	
	/*규모*/
	private String scale;
	
	/*세대수*/
	private String ehouse;
	
	/*면적*/
	private String area;
	
	/*평수*/
	private String space;
	
	/*기타사항*/
	private String etc;
	
	/*위치도*/
	private String map;
	
	/*부가정보*/
	private String mapInfo;
	
	/*예상교통망*/
	private String carsInfo;
	
	/*토지이용계획*/
	private String cad;
	
	/*상세정보*/
	private String contents;
	
	/*메인이미지*/
	private String mainImg;
	
	/*브로슈어1*/
	private String brFile1;
	
	/*브로슈어2*/
	private String brFile2;
	
	/*등록일*/
	private String createDate;
	
	/*수정일*/
	private String updateDate;
	
	/*추천여부*/
	private String good;
	
	/*분양상태*/
	private String state;
	
	/*삭제여부*/
	private String isDel;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getStep1() {
		return step1;
	}

	public void setStep1(String step1) {
		this.step1 = step1;
	}

	public String getStep2() {
		return step2;
	}

	public void setStep2(String step2) {
		this.step2 = step2;
	}

	public String getStep3() {
		return step3;
	}

	public void setStep3(String step3) {
		this.step3 = step3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getEhouse() {
		return ehouse;
	}

	public void setEhouse(String ehouse) {
		this.ehouse = ehouse;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getMapInfo() {
		return mapInfo;
	}

	public void setMapInfo(String mapInfo) {
		this.mapInfo = mapInfo;
	}

	public String getCarsInfo() {
		return carsInfo;
	}

	public void setCarsInfo(String carsInfo) {
		this.carsInfo = carsInfo;
	}

	public String getCad() {
		return cad;
	}

	public void setCad(String cad) {
		this.cad = cad;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getBrFile1() {
		return brFile1;
	}

	public void setBrFile1(String brFile1) {
		this.brFile1 = brFile1;
	}

	public String getBrFile2() {
		return brFile2;
	}

	public void setBrFile2(String brFile2) {
		this.brFile2 = brFile2;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}
