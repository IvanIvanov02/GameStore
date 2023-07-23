package gamestore.gamestore.Entities.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static gamestore.gamestore.Constants.ErrorMessages.*;
import static gamestore.gamestore.Constants.Validations.TITLE_PATTERN;

@Getter
@Setter
@NoArgsConstructor
public class AddGameDTO {

    private String title;

    private double price;

    private double size;

    private String trailer;

    private String url;

    private String description;

    private LocalDate localDate;

    public AddGameDTO(String title, double price, double size, String trailer, String url, String description, LocalDate localDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.url = url;
        this.description = description;
        this.localDate = localDate;
        validate();
    }

    private void validate() {
        if (!Pattern.matches(TITLE_PATTERN,title)) { throw new IllegalArgumentException(INVALID_TITLE); };
        if (price <= 0) { throw new IllegalArgumentException(INVALID_PRICE); }
        if (size <= 0) { throw new IllegalArgumentException(INVALID_SIZE); }
        if (description.length() < 20) { throw new IllegalArgumentException(SHORT_DESCRIPTION); }
    }
}
