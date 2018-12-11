package by.epam.fourthtask.entity;

public enum DepositType {

    POSTE_RESTANTE("До востребования"),
    URGENT("Срочный"),
    CALCULATED("Расчётный"),
    ACCUMULATIVE("Накопительный"),
    SAVING("Сберегательный"),
    MATELLIC("Мателлический");

    private String value;

    DepositType(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }

}
