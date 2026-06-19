package kg.megalab.receiptanalyzerservice.controller;

import kg.megalab.receiptanalyzerservice.dto.FileUploadResponse;
import kg.megalab.receiptanalyzerservice.entity.ReceiptImage;
import kg.megalab.receiptanalyzerservice.exception.InvalidFileTypeException;
import kg.megalab.receiptanalyzerservice.parser.ParsedProduct;
import kg.megalab.receiptanalyzerservice.repository.ReceiptImageRepository;
import kg.megalab.receiptanalyzerservice.service.FileStorageService;
import kg.megalab.receiptanalyzerservice.service.OcrService;
import kg.megalab.receiptanalyzerservice.service.ReceiptProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final FileStorageService fileStorageService;
    private final ReceiptImageRepository receiptImageRepository;
    private final OcrService ocrService;
    private final ReceiptProcessingService receiptProcessingService;


    @GetMapping("/{id}/parsed")
    public List<ParsedProduct> parseReceipt(
            @PathVariable Long id) {

        return receiptProcessingService
                .parseReceipt(id);
    }

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

    @GetMapping("/{id}/text")
    public String extractText(
            @PathVariable Long id) {

        ReceiptImage image =
                receiptImageRepository
                        .findById(id)
                        .orElseThrow();

        return ocrService.extractText(
                image.getFilePath()
        );
    }
}
