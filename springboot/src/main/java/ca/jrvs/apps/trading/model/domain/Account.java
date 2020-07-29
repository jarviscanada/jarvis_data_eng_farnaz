package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Account implements Entity<Integer> {

    private Integer id;
    private Integer traderId;
    private Double amount;

    public Account() {
    }

    public Account(Integer traderId, Double amount) {
        this.traderId = traderId;
        this.amount = amount;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTraderId() {
        return traderId;
    }

    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(traderId, account.traderId) &&
                Objects.equals(amount, account.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, traderId, amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", traderId=" + traderId +
                ", amount=" + amount +
                '}';
    }
}
