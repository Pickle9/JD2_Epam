package by.epam.fourthtask.parser;

enum StringEnum {

    BANK_STRING("bank"),
    COUNTRY_STRING("country"),
    EMPTY_STRING(""),
    DATE_FORMAT("yyyy-MM-dd"),
    HYPHEN_SYMBOL("-"),
    UNDERSCORE_SYMBOL("_");

    private String value;

    StringEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
