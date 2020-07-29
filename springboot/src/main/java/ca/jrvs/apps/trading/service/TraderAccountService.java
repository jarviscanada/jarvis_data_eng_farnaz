package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {

    private TraderDao traderDao;
    private AccountDao accountDao;
    private PositionDao positionDao;
    private SecurityOrderDao securityOrderDao;

    @Autowired
    public TraderAccountService(TraderDao traderDao, AccountDao accountDao,
                                PositionDao positionDao, SecurityOrderDao securityOrderDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    /**
     * Create a new trader and initialize a new account with 0 amount
     * - validate user input (all fields must be non-empty)
     * - create a trader
     * - create an account
     * - create, setup, and return a new traderAccountView
     * <p>
     * Assumption: to simplify the logic, each trader has only one account where traderId=accountId
     *
     * @param trader cannot be null. All fields except for ID(auto-generated) should be non-null
     * @return traderAccountView
     * @throws IllegalArgumentException if a trader has null fields or id is not null.
     */
    public TraderAccountView createTraderAndAccount(Trader trader) {
        if ((trader.getId() != null)) {
            throw new IllegalArgumentException("Every fields other than ID should not be null.");
        }
        if (trader.getFirstName() == null || trader.getLastName() == null || trader.getDob() == null
                || trader.getEmail() == null || trader.getCountry() == null) {
            throw new IllegalArgumentException("Every field other than id must not be null");
        }
        traderDao.save(trader);
        Account traderAccount = accountDao.save(new Account(trader.getId(), 0.0));
        TraderAccountView traderAccountView = new TraderAccountView(traderAccount, trader);
        return traderAccountView;
    }

    /**
     * A trader can be deleted iff it has no open position and 0 cash balance
     * - validate traderID
     * - get trader account by traderId and check account balance
     * - get position by accountId and check positions
     * - delete all securityOrders, account, trader (in this order)
     *
     * @param traderId must not be null
     * @throws IllegalArgumentException if traderId is null or not found or unable to delete
     */
    public void deleteTraderById(Integer traderId) {
        if (!traderDao.existsById(traderId)) {
            throw new IllegalArgumentException("TraderID could not be found : " + traderId);
        }

        Account traderAccount = accountDao.findById(traderId).get();
        List<Position> traderPositions = positionDao.findAllById(Arrays.asList(traderId));
        if (traderAccount.getAmount() != 0.0) {
            throw new IllegalArgumentException("Trader Account cannot be deleted : " + traderId);
        } else {
            securityOrderDao.deleteAll(traderAccount.getId());
            accountDao.deleteById(traderAccount.getId());
            traderDao.deleteById(traderId);
        }
    }


    /**
     * Deposit a fund to an account by traderId
     * - validate user input
     * - account = accountDao.findByTraderId
     * - accountDao.updateAmountId
     *
     * @param traderId must not be null
     * @param fund     must be greater than 0
     * @return updated Account
     * @throws IllegalArgumentException if traderId is null or not found, and fund is less or equal to
     *                                  0.0
     */
    public Account deposit(Integer traderId, Double fund) {
        if (traderId == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        if (fund <= 0d) {
            throw new IllegalArgumentException("Fund cannot be less or equal than zero");
        }
        Account account = accountDao.findById(traderId).orElseThrow(() ->
                new IllegalArgumentException("The trader account could not be found"));
        Double amount = account.getAmount() + fund;
        account.setAmount(amount);
        return accountDao.save(account);
    }

    /**
     * Withdraw a fund to an account by traderId
     * - validate user input
     * - account = accountDao.findByTraderId
     * - accountDao.updateAmountById
     *
     * @param traderId trader ID
     * @param fund     amount can't be 0.0
     * @return updated Account
     * @throws IllegalArgumentException if traderId is null or not found, fund is less or equal to 0,
     *                                  and insufficient fund
     */
    public Account withdraw(Integer traderId, Double fund) {
        if (traderId == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        if (fund <= 0d) {
            throw new IllegalArgumentException("Fund cannot be less or equal than zero");
        }
        Account account = accountDao.findById(traderId).orElseThrow(() ->
                new IllegalArgumentException("The trader account specified could not be found"));
        Double balance = account.getAmount();
        if (balance - fund < 0.0) {
            throw new IllegalArgumentException(
                    "Not Enough Balance");
        } else {
            account.setAmount(balance - fund);
            return accountDao.save(account);
        }
    }
}
