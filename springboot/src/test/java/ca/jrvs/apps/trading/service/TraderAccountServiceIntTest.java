package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import java.sql.Date;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceIntTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Autowired
    private TraderAccountService traderAccountService;
    @Autowired
    private TraderDao traderDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private SecurityOrderDao securityOrderDao;

    private Integer traderId;

    private Trader trader = new Trader();
    private Account account = new Account();
    private Quote quote = new Quote();
    private TraderAccountView savedView;

    @Before
    public void insert() {
        trader.setFirstName("Jane");
        trader.setLastName("Doe");
        trader.setEmail("doe@gmail.com");
        trader.setDob(Date.valueOf(LocalDate.of(2020, 12, 12)));
        trader.setCountry("Canada");

        savedView = traderAccountService.createTraderAndAccount(trader);
        traderId = savedView.getTrader().getId();
        account.setId(1);
        account.setTraderId(1);
        account.setAmount(0.0);
        TraderAccountView expectedView = new TraderAccountView(account, trader);

        assertEquals(savedView, expectedView);
    }

    @Test
    public void deleteTest() {
        traderAccountService.deleteTraderById(traderId);
    }

    @Test
    public void depositTest() {
        double testAmount = savedView.getAccount().getAmount() + 100d;
        assertTrue(testAmount == traderAccountService.deposit(traderId, 100d).getAmount());

    }

}