package egovframework.portal.sys.common.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadHelperService {

	public String uploadFile(String tmpUploadPath, MultipartFile file) throws IllegalStateException, IOException;
}
