package egovframework.portal.bbs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.portal.bbs.web.BbsController;

/**
 * 게시글 사용자단 controller 테스트
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2019. 6. 17.			J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2019. 6. 17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { //
	"file:src/main/webapp/WEB-INF/config/springmvc/common-servlet.xml", //
	"file:src/main/resources/spring/context-aspect.xml", //
	"file:src/main/resources/spring/context-common.xml", //
	"file:src/test/resources/spring/context-datasource.xml", //
	"file:src/main/resources/spring/context-idgen.xml", //
	"file:src/main/resources/spring/context-mapper.xml", //
	"file:src/main/resources/spring/context-properties.xml", //
	"file:src/main/resources/spring/context-schedule.xml", //
	"file:src/main/resources/spring/context-security.xml", //
	"file:src/main/resources/spring/context-transaction.xml", //
	"file:src/main/resources/spring/context-validator.xml" } //
)
@WebAppConfiguration
public class BbsControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private MockHttpSession session;
	private MockHttpServletRequest request;

	@Autowired
	@InjectMocks
	private BbsController controller;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		session = new MockHttpSession();
		request = new MockHttpServletRequest();
		request.setSession(session);

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void listTest() throws Exception {
		mockMvc.perform( //
			get("/portal/bbs/list.do")
				.header("User-Agent", "junit") //
				.param("ptIdx", "624") //
				.param("mId", "0901000000") //
		)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("result"));
	}

}
