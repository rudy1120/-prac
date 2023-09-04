package egovframework.portal.sys.bbs.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * 게시판 XML 제공용 WRAPPER VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 3.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 3.
 */
public class BbsXmlVO {

	private List<BbsMngVO> items = new ArrayList<>();

	public BbsXmlVO() {
		// default
	}

	public BbsXmlVO(List<BbsMngVO> items) {
		setItems(items);
	}

	@XmlElement(name = "item")
	public List<BbsMngVO> getItems() {
		return items;
	}

	public void setItems(List<BbsMngVO> items) {
		this.items = items;
	}

}
