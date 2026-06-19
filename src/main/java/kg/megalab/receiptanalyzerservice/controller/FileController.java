package kg.megalab.receiptanalyzerservice.controller;

import kg.megalab.receiptanalyzerservice.dto.FileUploadResponse;
import kg.megalab.receiptanalyzerservice.exception.InvalidFileTypeException;
import kg.megalab.receiptanalyzerservice.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public FileUploadResponse upload(
            @RequestParam("file")
            MultipartFile file) {
        if (!file.getContentType()
                .startsWith("image/")) {
            throw new InvalidFileTypeException(
                    "Only image files are allowed"
            );
        }
        return fileStorageService.upload(file);
    }
}
