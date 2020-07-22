package ca.jrvs.apps.trading.model.domain;


import java.util.Objects;

public class Position {

    private Integer accountId;
    private String ticker;
    private Integer position;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position1 = (Position) o;
        return Objects.equals(accountId, position1.accountId) &&
                Objects.equals(ticker, position1.ticker) &&
                Objects.equals(position, position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, ticker, position);
    }

    @Override
    public String toString() {
        return "Position{" +
                "accountId=" + accountId +
                ", ticker='" + ticker + '\'' +
                ", position=" + position +
                '}';
    }
}
