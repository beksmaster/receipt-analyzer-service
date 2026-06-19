package kg.megalab.receiptanalyzerservice.service;

import kg.megalab.receiptanalyzerservice.dto.CreateReceiptRequest;
import kg.megalab.receiptanalyzerservice.dto.ProductResponse;
import kg.megalab.receiptanalyzerservice.dto.ReceiptResponse;
import kg.megalab.receiptanalyzerservice.entity.Product;
import kg.megalab.receiptanalyzerservice.entity.Receipt;
import kg.megalab.receiptanalyzerservice.exception.ReceiptNotFoundException;
import kg.megalab.receiptanalyzerservice.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Override
    @Transactional
    public Long createReceipt(CreateReceiptRequest request) {

        Receipt receipt = new Receipt();

        receipt.setStoreName(request.storeName());
        receipt.setPurchaseDate(LocalDateTime.now());
        receipt.setTotalAmount(request.totalAmount());

        List<Product> products = request.products()
                .stream()
                .map(dto ->{
                            Product product = new Product();

                            product.setName(dto.name());
                            product.setPrice(dto.price());
                            product.setQuantity(dto.quantity());

                            product.setReceipt(receipt);

                            return product;
                        })
                .toList();
        receipt.setProducts(products);

        Receipt saved = receiptRepository.save(receipt);

        return saved.getId();
    }

    private ReceiptResponse toResponse(Receipt receipt) {

        List<ProductResponse> products =
                receipt.getProducts()
                        .stream()
                        .map(product -> new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getPrice(),
                                product.getQuantity()
                        ))
                        .toList();

        return new ReceiptResponse(
                receipt.getId(),
                receipt.getStoreName(),
                receipt.getPurchaseDate(),
                receipt.getTotalAmount(),
                products
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceiptResponse> getAllReceipts() {

        return receiptRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ReceiptResponse getReceiptById(Long id) {

        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() ->
                        new ReceiptNotFoundException(id));

        return toResponse(receipt);
    }

    @Override
    @Transactional
    public void deleteReceipt(long id) {

        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() ->
                        new ReceiptNotFoundException(id));

        receiptRepository.delete(receipt);
    }
}
