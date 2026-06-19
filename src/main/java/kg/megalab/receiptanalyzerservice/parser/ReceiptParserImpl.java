package kg.megalab.receiptanalyzerservice.parser;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReceiptParserImpl implements ReceiptParser {

    private static final Pattern PRICE_PATTERN =
            Pattern.compile("(\\d+[.,:-]\\d{2})");

    private static final List<String> SKIP_WORDS =
            List.of(
                    "кассир",
                    "дата",
                    "смена",
                    "чек",
                    "безналич",
                    "итог",
                    "округлен",
                    "сайт",
                    "тел",
                    "инн",
                    "ккт"
            );

    @Override
    public List<ParsedProduct> parse(String text) {

        List<ParsedProduct> products =
                new ArrayList<>();

        String[] lines = text.split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (line.isBlank()) {
                continue;
            }

            if (shouldSkip(line)) {
                continue;
            }

            if (isPhoneNumber(line)) {
                continue;
            }

            if (isDate(line)) {
                continue;
            }

            Matcher matcher =
                    PRICE_PATTERN.matcher(line);

            if (matcher.find()) {

                String priceString =
                        matcher.group(1)
                                .replace(":", ".")
                                .replace("-", ".")
                                .replace(",", ".");

                String name =
                        line.substring(
                                0,
                                matcher.start()
                        ).trim();

                name = name.replaceAll(
                        "[^а-яА-Яa-zA-Z ]",
                        ""
                ).trim();

                if (name.length() < 3) {
                    continue;
                }
                if (name.isBlank()) {
                    continue;
                }
                BigDecimal price =
                        new BigDecimal(priceString);

                if (price.compareTo(BigDecimal.TEN) < 0) {
                    continue;
                }

                try {

                    products.add(
                            new ParsedProduct(
                                    name,
                                    new BigDecimal(priceString)
                            )
                    );

                } catch (NumberFormatException ignored) {
                }
            }
        }

        return products;
    }

    private boolean shouldSkip(String line) {

        String lower =
                line.toLowerCase();

        return SKIP_WORDS.stream()
                .anyMatch(lower::contains);
    }

    private boolean isPhoneNumber(String line) {

        return line.matches(
                ".*\\d{3}[- ]?\\d{3}[- ]?\\d{2}[- ]?\\d{2}.*"
        );
    }

    private boolean isDate(String line) {

        return line.matches(
                ".*\\d{2}/\\d{2}/\\d{4}.*"
        );
    }
}
