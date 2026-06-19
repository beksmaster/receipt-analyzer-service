package kg.megalab.receiptanalyzerservice.service;

import kg.megalab.receiptanalyzerservice.dto.FileUploadResponse;

import kg.megalab.receiptanalyzerservice.entity.ReceiptImage;
import kg.megalab.receiptanalyzerservice.repository.ReceiptImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    ReceiptImageRepository receiptImageRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public FileUploadResponse upload(MultipartFile file) {


        if (!file.getContentType()
                .startsWith("image/")) {
            throw new RuntimeException(
                    "Only images allowed"
            );
        }
        try {

            Path uploadPath =
                    Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName =
                    UUID.randomUUID() +
                            "_" +
                            file.getOriginalFilename();

            Path filePath =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            ReceiptImage image = new ReceiptImage();

            image.setFileName(fileName);
            image.setFilePath(filePath.toString());
            image.setFileSize(file.getSize());
            image.setUploadedAt(LocalDateTime.now());

            receiptImageRepository.save(image);

            return new FileUploadResponse(
                    fileName,
                    filePath.toString(),
                    file.getSize()
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "File upload failed"
            );
        }
    }
}
