package by.epam.fourthtask.controller;

enum StringEnum {

    SLASH_REG_EXP("\\\\"),
    EMPTY_STRING("");

    private String value;

    StringEnum(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }
}
