package by.epam.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Bank {

    private String name;
    private String country;
    private DepositType type;
    private String depositor;
    private String accountId;
    private double amount;
    private float profitability;
    private Date timeConstraints;

    public Bank() {
    }

    public Bank(String name, String country, DepositType type, String depositor, String accountId,
                double amount, float profitability, Date timeConstraints) {
        this.name = name;
        this.country = country;
        this.type = type;
        this.depositor = depositor;
        this.accountId = accountId;
        this.amount = amount;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public float getProfitability() {
        return profitability;
    }

    public void setProfitability(float profitability) {
        this.profitability = profitability;
    }

    public Date getTimeConstraints() {
        return timeConstraints;
    }

    public String getStringTimeConstraints() {
        return new SimpleDateFormat("yyyy-MM-dd").format(timeConstraints);
    }

    public void setTimeConstraints(Date timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(bank.amount, amount) == 0 &&
                Float.compare(bank.profitability, profitability) == 0 &&
                Objects.equals(name, bank.name) &&
                Objects.equals(country, bank.country) &&
                type == bank.type &&
                Objects.equals(depositor, bank.depositor) &&
                Objects.equals(accountId, bank.accountId) &&
                Objects.equals(timeConstraints, bank.timeConstraints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, type, depositor, accountId, amount, profitability, timeConstraints);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", type=" + type.getValue() +
                ", depositor='" + depositor + '\'' +
                ", accountId='" + accountId + '\'' +
                ", amount=" + amount + "$" +
                ", profitability=" + profitability + "%" +
                ", timeConstraints=" + new SimpleDateFormat("yyyy-MM-dd").format(timeConstraints) +
                '}';
    }

//    @Override
//    public String toString() {
//        return name + " " +
//                country + " " +
//                type.getValue() + " " +
//                depositor + " " +
//                accountId + " " +
//                amount + "$ " +
//                profitability + "% " +
//                new SimpleDateFormat("yyyy-MM-dd").format(timeConstraints);
//    }
}
