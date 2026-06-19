package kg.megalab.receiptanalyzerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;

    private LocalDateTime purchaseDate;

    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "receipt",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
}
