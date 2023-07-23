package gamestore.gamestore.Entities.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Pattern;

import static gamestore.gamestore.Constants.ErrorMessages.EMAIL_NOT_VALID;
import static gamestore.gamestore.Constants.ErrorMessages.PASSWORD_NOT_VALID;
import static gamestore.gamestore.Constants.Validations.EMAIL_PATTERN;
import static gamestore.gamestore.Constants.Validations.PASSWORD_PATTERN;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {

    private String email;

    private String password;

    private String fullName;
    public UserLoginDTO(String email, String password,String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        validate();
    }

    private void validate() {
        if(!Pattern.matches(EMAIL_PATTERN, this.email)) { throw new IllegalArgumentException(EMAIL_NOT_VALID); }
        if (!Pattern.matches(PASSWORD_PATTERN,this.password )) { throw new IllegalArgumentException(PASSWORD_NOT_VALID); }
    }
}
