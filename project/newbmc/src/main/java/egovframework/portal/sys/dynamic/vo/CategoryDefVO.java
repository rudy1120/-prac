package egovframework.portal.sys.dynamic.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import egovframework.portal.validation.ByteSize;

/**
 * 카테고리 설정 VO
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 4. 10.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 4. 10.
 */
public class CategoryDefVO {

	/** 카테고리 항목 이름 */
	@ByteSize(max = 1000)
	private String name = "";
	/** 카테고리 폭 */
	@ByteSize(max = 3)
	@RegExp("^[0-9]+$")
	private String width = "";
	/** 카테고리 전체 사용여부 */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String showYn = "";
	/** 카테고리 목록 출력여부 */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String showListYn = "";
	/** 카테고리 검색 사용여부 */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String showSearchYn = "";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getShowYn() {
		return showYn;
	}
	public void setShowYn(String showYn) {
		this.showYn = showYn;
	}
	public String getShowListYn() {
		return showListYn;
	}
	public void setShowListYn(String showListYn) {
		this.showListYn = showListYn;
	}
	public String getShowSearchYn() {
		return showSearchYn;
	}
	public void setShowSearchYn(String showSearchYn) {
		this.showSearchYn = showSearchYn;
	}

}