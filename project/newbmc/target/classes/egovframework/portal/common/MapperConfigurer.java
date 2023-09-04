package egovframework.portal.common;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * MapperConfigurer
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 12. 28.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 12. 28.
 */
public class MapperConfigurer extends MapperScannerConfigurer {

	private String session = "";

	public MapperConfigurer(String session) {
		super.setAnnotationClass(Mapper.class);
		super.setSqlSessionFactoryBeanName(session);
		this.session = session;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

}