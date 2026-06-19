package kg.megalab.receiptanalyzerservice.controller;

import jakarta.validation.Valid;
import kg.megalab.receiptanalyzerservice.dto.CreateReceiptRequest;
import kg.megalab.receiptanalyzerservice.dto.ReceiptResponse;
import kg.megalab.receiptanalyzerservice.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping
    public Long createReceipt(
            @Valid @RequestBody CreateReceiptRequest request) {
        return receiptService.createReceipt(request);
    }

    @GetMapping("/{id}")
    public ReceiptResponse getById(
            @PathVariable Long id) {

        return receiptService.getReceiptById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        receiptService.deleteReceipt(id);
    }

    @GetMapping
    public Page<ReceiptResponse> getAll(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size){
        return receiptService.getAllReceipts(page, size);
    }
}
