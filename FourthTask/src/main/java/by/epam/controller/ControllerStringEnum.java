package by.epam.controller;

enum ControllerStringEnum {

    SLASH_REG_EXP("\\\\"),
    EMPTY_STRING("");

    private String value;

    ControllerStringEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
