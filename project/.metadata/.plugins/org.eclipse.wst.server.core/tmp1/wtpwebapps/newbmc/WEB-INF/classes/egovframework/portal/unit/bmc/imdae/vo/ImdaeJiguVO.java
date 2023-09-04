package egovframework.portal.unit.bmc.imdae.vo;

public class ImdaeJiguVO {
	
	/* 기본키 */
	private int sn;
	
	/* jigu 번호 */
	private int jigu;
	
	/* jigu 이름 */
	private String jiguname;
	
	/* jigu 주소 */
	private String jiguso;

	/* 평 */
	private String pyung;
	
	/* 총 세대수 */
	private String sedaetotal;
	
	/* 공가세대 */
	private String sedae;
	
	/* ? */
	private String dae;
	
	/* 번호 */
	private int num;
	
	/* 구분 */
	private int gb;
	
	/* 대기자 수 */
	private int wating;
	
	/* 공급대상 */
	private String supply;
	
	/* 행복주택 세부 구분값 */
	private String hgbn;
	
	private String cnt;
	
	public int getWating() {
		return wating;
	}

	public void setWating(int wating) {
		this.wating = wating;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public int getJigu() {
		return jigu;
	}

	public void setJigu(int jigu) {
		this.jigu = jigu;
	}

	public String getJiguname() {
		return jiguname;
	}

	public void setJiguname(String jiguname) {
		this.jiguname = jiguname;
	}

	public String getJiguso() {
		return jiguso;
	}

	public void setJiguso(String jiguso) {
		this.jiguso = jiguso;
	}

	public String getPyung() {
		return pyung;
	}

	public void setPyung(String pyung) {
		this.pyung = pyung;
	}

	public String getSedaetotal() {
		return sedaetotal;
	}

	public void setSedaetotal(String sedaetotal) {
		this.sedaetotal = sedaetotal;
	}

	public String getSedae() {
		return sedae;
	}

	public void setSedae(String sedae) {
		this.sedae = sedae;
	}

	public String getDae() {
		return dae;
	}

	public void setDae(String dae) {
		this.dae = dae;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getGb() {
		return gb;
	}

	public void setGb(int gb) {
		this.gb = gb;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	public String getHgbn() {
		return hgbn;
	}

	public void setHgbn(String hgbn) {
		this.hgbn = hgbn;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "ImdaeJiguVO [sn=" + sn + ", jigu=" + jigu + ", jiguname=" + jiguname + ", jiguso=" + jiguso + ", pyung="
				+ pyung + ", sedaetotal=" + sedaetotal + ", sedae=" + sedae + ", dae=" + dae + ", num=" + num + ", gb="
				+ gb + ", wating=" + wating + ", supply=" + supply + ", hgbn=" + hgbn + ", cnt=" + cnt + "]";
	}
}
