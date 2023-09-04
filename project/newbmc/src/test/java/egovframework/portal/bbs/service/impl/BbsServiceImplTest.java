package egovframework.portal.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import egovframework.portal.bbs.service.BbsService;

/**
 * 게시글 서비스 테스트
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
public class BbsServiceImplTest {

	@Autowired
	private BbsService bbsService;
	private BbsServiceImpl serviceImpl = new BbsServiceImpl();

	private static final String FILE_DIR = "C:/Users/user/Pictures/";

//	@Test
	public void lenthTestShouldBeOK() throws Exception {
		Assert.assertTrue(serviceImpl.isValidLength("테스트 게시글 제목", 100));
	}

	@Test
	public void smallFileTestShouleBeOk() throws IOException {
		String fileName = "The-Marvelous-Crystal-Blue-Lake-Louise-at-Banff-National-Park-in-Alberta-Canada.jpg";
		Assert.assertTrue(serviceImpl.isValidFileSize( //
			new MockMultipartFile( //
				"data", fileName, "image/jpg", Files.readAllBytes(new File(FILE_DIR + fileName).toPath())), //
			1) //
		);
	}

	@Test
	public void bigFileTestShouleBeFailed() throws IOException {
		String fileName = "photo-1503788760144-795d5cdf0f56.jpg";
		Assert.assertTrue(serviceImpl.isValidFileSize( //
			new MockMultipartFile( //
				"data", fileName, "image/jpg", Files.readAllBytes(new File(FILE_DIR + fileName).toPath())), //
			1) //
		);
	}

}
