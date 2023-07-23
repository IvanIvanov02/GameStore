package gamestore.gamestore.Entities.Models;

import lombok.*;

import java.util.regex.Pattern;

import static gamestore.gamestore.Constants.ErrorMessages.*;
import static gamestore.gamestore.Constants.Validations.EMAIL_PATTERN;
import static gamestore.gamestore.Constants.Validations.PASSWORD_PATTERN;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterUserDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterUserDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    private void validate() {
        if(!Pattern.matches(EMAIL_PATTERN, this.email)) { throw new IllegalArgumentException(EMAIL_NOT_VALID); }
        if (!Pattern.matches(PASSWORD_PATTERN,this.password )) { throw new IllegalArgumentException(PASSWORD_NOT_VALID); }
        if(!this.password.equals(this.confirmPassword)) { throw new IllegalArgumentException(PASSWORDS_MISMATCH); }
        if ( this.fullName == null || this.fullName.trim().isEmpty()) { throw new IllegalArgumentException(USERNAME_NOT_VALID); }
    }
}
