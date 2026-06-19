package kg.megalab.receiptanalyzerservice.dto;

public record FileUploadResponse(
        String fileName,
        String filePath,
        long size
) {
}
