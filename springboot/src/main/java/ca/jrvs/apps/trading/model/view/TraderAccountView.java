package ca.jrvs.apps.trading.model.view;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Objects;

public class TraderAccountView {

    private Account account;
    private Trader trader;

    public TraderAccountView(Account account, Trader trader) {
        this.account = account;
        this.trader = trader;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    @Override
    public String toString() {
        return "TraderAccountView{" +
                "account=" + account +
                ", trader=" + trader +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TraderAccountView that = (TraderAccountView) o;
        return Objects.equals(account, that.account) &&
                Objects.equals(trader, that.trader);
    }

}
