package by.yemelyanenko.MoneyMap.enums;

public class ValidationConstants {

    public static final String NOT_BLANC_MESSAGE = "должно быть заполнено";

    public static final String SIZE_MESSAGE = "должно быть не менее 5 и не более 20 символов";

    public static final String MATCHER_MESSAGE = "может содержать только буквы латинского алфавита, цифры и нижнее подчеркивание";

    public static final String VALIDATION_USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9_]{3,19}$";

    public static final String VALIDATION_PASSWORD_REGEX = "^[a-zA-Z0-9_]+$";
}
