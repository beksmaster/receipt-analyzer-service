package kg.megalab.receiptanalyzerservice.service;

import kg.megalab.receiptanalyzerservice.entity.Product;
import kg.megalab.receiptanalyzerservice.entity.Receipt;
import kg.megalab.receiptanalyzerservice.entity.ReceiptImage;
import kg.megalab.receiptanalyzerservice.parser.ParsedProduct;
import kg.megalab.receiptanalyzerservice.parser.ReceiptParser;
import kg.megalab.receiptanalyzerservice.repository.ReceiptImageRepository;
import kg.megalab.receiptanalyzerservice.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReceiptImportServiceImpl implements ReceiptImportService{private final ReceiptImageRepository receiptImageRepository;
    private final ReceiptRepository receiptRepository;

    private final OcrService ocrService;
    private final ReceiptParser receiptParser;

    @Override
    public Long importReceipt(Long imageId) {

        ReceiptImage image =
                receiptImageRepository
                        .findById(imageId)
                        .orElseThrow();

        String text =
                ocrService.extractText(
                        image.getFilePath()
                );

        List<ParsedProduct> parsedProducts =
                receiptParser.parse(text);

        Receipt receipt = new Receipt();

        receipt.setStoreName("Unknown");
        receipt.setPurchaseDate(LocalDateTime.now());

        BigDecimal total =
                parsedProducts.stream()
                        .map(ParsedProduct::price)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        receipt.setTotalAmount(total);

        List<Product> products =
                parsedProducts.stream()
                        .map(parsed -> {

                            Product product =
                                    new Product();

                            product.setName(
                                    parsed.name()
                            );

                            product.setPrice(
                                    parsed.price()
                            );

                            product.setQuantity(
                                    parsed.quantity()
                            );

                            product.setReceipt(
                                    receipt
                            );

                            return product;

                        })
                        .toList();

        receipt.setProducts(products);

        Receipt saved =
                receiptRepository.save(receipt);

        return saved.getId();
    }
}
