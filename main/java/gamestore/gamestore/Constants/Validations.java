package gamestore.gamestore.Constants;

public class Validations {
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
    public static final String TITLE_PATTERN = "^[A-Z][A-Za-z]{2,99}$";

}
