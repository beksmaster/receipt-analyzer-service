package kg.megalab.receiptanalyzerservice.service;

import kg.megalab.receiptanalyzerservice.entity.ReceiptImage;
import kg.megalab.receiptanalyzerservice.parser.ParsedProduct;
import kg.megalab.receiptanalyzerservice.parser.ReceiptParser;
import kg.megalab.receiptanalyzerservice.repository.ReceiptImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptProcessingServiceImpl
        implements ReceiptProcessingService {

    private final ReceiptImageRepository receiptImageRepository;

    private final OcrService ocrService;

    private final ReceiptParser receiptParser;

    @Override
    public List<ParsedProduct> parseReceipt(
            Long imageId) {

        ReceiptImage image =
                receiptImageRepository
                        .findById(imageId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Image not found"
                                )
                        );

        String text =
                ocrService.extractText(
                        image.getFilePath()
                );

        return receiptParser.parse(text);
    }
}
