package kg.megalab.receiptanalyzerservice.repository;

import kg.megalab.receiptanalyzerservice.entity.ReceiptImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptImageRepository extends JpaRepository<ReceiptImage, Long> {
}
