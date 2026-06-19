package kg.megalab.receiptanalyzerservice.service;

import kg.megalab.receiptanalyzerservice.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    FileUploadResponse upload(MultipartFile file);
}
