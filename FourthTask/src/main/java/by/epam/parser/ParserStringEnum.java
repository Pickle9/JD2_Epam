package by.epam.parser;

enum ParserStringEnum {

    BANK_STRING("bank"),
    COUNTRY_STRING("country"),
    EMPTY_STRING(""),
    DATE_FORMAT("yyyy-MM-dd"),
    HYPHEN_SYMBOL("-"),
    UNDERSCORE_SYMBOL("_");

    private String value;

    ParserStringEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
