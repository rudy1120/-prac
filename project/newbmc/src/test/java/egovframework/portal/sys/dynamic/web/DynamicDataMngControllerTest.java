package egovframework.portal.sys.dynamic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import egovframework.portal.common.SessionKey;
import egovframework.portal.sys.sysAuth.AdminType;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.sys.sysAuth.web.SysMemberController;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/springmvc/common-servlet.xml",
//	"file:src/main/resources/spring/context-aspect.xml",
//	"file:src/main/resources/spring/context-common.xml",
//	"file:src/test/resources/spring/context-datasource.xml",
//	"file:src/main/resources/spring/context-idgen.xml",
//	"file:src/main/resources/spring/context-mapper.xml",
//	"file:src/main/resources/spring/context-properties.xml",
//	"file:src/main/resources/spring/context-schedule.xml",
//	"file:src/main/resources/spring/context-security.xml",
//	"file:src/main/resources/spring/context-transaction.xml",
//	"file:src/main/resources/spring/context-validator.xml"})
public class DynamicDataMngControllerTest {
//
//	@InjectMocks
//	private DynamicDataMngController mainController;
//	@InjectMocks
//	private SysMemberController sysmemberController;
//	@Autowired
//	private WebApplicationContext wac;
//
//	private MockMvc mockMvc;
//	private MockMvc mockMvc2;
//
//	@Before
//	public void setUp() {
////		MockitoAnnotations.initMocks(this);
////		mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
////		mockMvc2 = MockMvcBuilders.standaloneSetup(sysmemberController).build();
////		this.mockMvc = webAppContextSetup(this.wac).build();
//	}
//
//	@Test
//	public void testMainController() throws Exception {
////		when(commonService.commonDataCreater(request, response, model)).thenReturn(10);
//
////		AdminLoginVO adminLoginVO = new AdminLoginVO();
////		adminLoginVO.setId("test");
////		adminLoginVO.setAdminAccessLevelCode(AuthType.SUPER.getCode());
////		adminLoginVO.setDeptId("00000000");
////		adminLoginVO.setDeptName("test");
////		adminLoginVO.setAdminType(AdminType.PUBLIC_OFFICIAL);
////		adminLoginVO.setAdminTypeCode(AdminType.PUBLIC_OFFICIAL.getCode());
////
////		MockHttpSession session = new MockHttpSession();
////		session.setAttribute(SessionKey.ADMIN_LOGIN_INFO.getKey(), adminLoginVO);
//
////		mockMvc2.perform(get("/sys/superLoginProc.do?adminId=yhdev&passwd=yhdb6667")).andExpect(status().isOk());
////		mockMvc.perform(get("/sys/dataMng/test1/batchDeleteProc.do?idxs=1")).andExpect(status().isOk());
//	}

}
