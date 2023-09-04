package egovframework.portal.bbs.service.impl;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * JAXB ADAPTER
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
public class AdapterCDATA extends XmlAdapter<String, String> {

	@Override
	public String marshal(String arg0) throws Exception {
		return "<![CDATA[" + arg0 + "]]>";
	}

	@Override
	public String unmarshal(String arg0) throws Exception {
		return arg0;
	}
}
