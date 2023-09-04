package egovframework.portal.sys.common.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.portal.sys.common.service.FileUploadHelperService;

/**
 * 파일 업로드 HELPER SERVICE IMPL
 * 엑셀 파일 업로드시 공통적으로 사용하는 로직을 컴포넌트화
 *
 * @author J.Ryeon Lee
 * @since 2015.12.10
 */
@Service
public class FileUploadHelperServiceImpl implements FileUploadHelperService {

	@Override
	public String uploadFile(String tmpUploadPath, MultipartFile file) throws IllegalStateException, IOException {
		String originalFilename = file.getOriginalFilename();
		String convertFilename = String.valueOf(System.currentTimeMillis());

		int index = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(index + 1);

		new File(tmpUploadPath).mkdir();
		File uploadFile = new File(tmpUploadPath + convertFilename + "." + ext);
		file.transferTo(uploadFile);
		return convertFilename + "." + ext;
	}

}
