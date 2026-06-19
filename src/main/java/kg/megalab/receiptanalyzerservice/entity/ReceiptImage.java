package kg.megalab.receiptanalyzerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "receipt_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String filePath;

    private Long fileSize;

    private LocalDateTime uploadedAt;
}
