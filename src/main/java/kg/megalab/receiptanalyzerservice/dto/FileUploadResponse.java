package kg.megalab.receiptanalyzerservice.dto;

public record FileUploadResponse(
        Long id,
        String fileName,
        String filePath,
        long size
) {
}