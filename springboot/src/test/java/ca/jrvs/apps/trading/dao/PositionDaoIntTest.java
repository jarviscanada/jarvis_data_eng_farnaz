package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.time.LocalDate;
import org.junit.After;
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
public class PositionDaoIntTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private SecurityOrderDao securityOrderDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private QuoteDao quoteDao;

    private Position position = new Position();

    private SecurityOrder securityOrder = new SecurityOrder();

    private Trader trader = new Trader();

    private Account account = new Account();

    private Quote quote = new Quote();


    @Before
    public void insert() {
        trader.setFirstName("John");
        trader.setLastName("Doe");
        trader.setEmail("john.doe@gmail.com");
        trader.setCountry("Canada");
        trader.setDob(Date.valueOf(LocalDate.of(2000, 2, 2)));
        traderDao.save(trader);

        quote.setId("aapl");
        quote.setAskPrice(10d);
        quote.setAskSize(10L);
        quote.setBidPrice(10.2d);
        quote.setBidSize(10L);
        quote.setLastPrice(10.1d);
        quoteDao.save(quote);

        account.setAmount(500.0);
        account.setTraderId(trader.getId());
        account.setTraderId(1);
        accountDao.save(account);

        securityOrder.setTicker("aapl");
        securityOrder.setAccountId(securityOrder.getId());
        securityOrder.setAccountId(1);
        securityOrder.setStatus("Filled");
        securityOrder.setSize(100);
        securityOrder.setPrice(20.0);
        securityOrder.setNotes("Notes");
        securityOrderDao.save(securityOrder);

        position.setTicker("aapl");
        position.setAccountId(account.getId());
        position.setAccountId(1);
        position.setPosition(100);

    }

    @After
    public void remove() {
        securityOrderDao.deleteById(securityOrder.getId());
        accountDao.deleteById(account.getId());
        quoteDao.deleteById(quote.getId());
        traderDao.deleteById(trader.getId());
    }

    @Test
    public void existsByIdTest() {
        assertTrue(positionDao.existsById(1));
    }

    @Test
    public void existsByTickerTest() {
        assertTrue(positionDao.existsByTicker("aapl"));
    }

    @Test
    public void findByIdTest() {
        assertTrue(positionDao.existsById(1));
    }

}




