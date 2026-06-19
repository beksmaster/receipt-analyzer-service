package kg.megalab.receiptanalyzerservice.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OcrServiceImpl implements OcrService {

    @Override
    public String extractText(
            String imagePath) {

        try {

            ITesseract tesseract =
                    new Tesseract();

            tesseract.setDatapath(
                    "C:/Program Files/Tesseract-OCR/tessdata"
            );

            tesseract.setLanguage("rus");

            return tesseract.doOCR(
                    new File(imagePath)
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "OCR failed", e
            );
        }
    }
}
