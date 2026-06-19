package kg.megalab.receiptanalyzerservice.repository;

import kg.megalab.receiptanalyzerservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends JpaRepository<Product, Long> {
}
