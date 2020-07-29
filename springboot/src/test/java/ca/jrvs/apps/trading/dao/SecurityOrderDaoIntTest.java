package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
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
public class SecurityOrderDaoIntTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private SecurityOrderDao securityOrderDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private QuoteDao quoteDao;

    private SecurityOrder securityOrder = new SecurityOrder();
    private Trader trader = new Trader();

    private Account account = new Account();

    private Quote quote = new Quote();


    @Before
    public void insert() {
        trader.setFirstName("John");
        trader.setLastName("Doe");
        trader.setCountry("Canada");
        trader.setEmail("john.doe@gmail.com");
        trader.setDob(Date.valueOf(LocalDate.of(2000, 2, 2)));
        traderDao.save(trader);
        account.setTraderId(1);
        account.setAmount(500.0);
        accountDao.save(account);

        quote.setAskPrice(10d);
        quote.setAskSize(10L);
        quote.setBidPrice(10.2d);
        quote.setBidSize(10L);
        quote.setId(("aapl"));
        quote.setLastPrice(10.1d);
        quoteDao.save(quote);

        securityOrder.setAccountId(1);
        securityOrder.setTicker("aapl");
        securityOrder.setStatus("Filled");
        securityOrder.setSize(4);
        securityOrder.setPrice(550d);
        securityOrder.setNotes("Notes");
        securityOrderDao.save(securityOrder);

    }

    @After
    public void remove() {
        securityOrderDao.deleteById(securityOrder.getId());

    }

    @Test
    public void findByIdTest() {
        assertEquals(Optional.of(securityOrder), securityOrderDao.findById(1));
    }

    @Test
    public void existsByIdTest() {
        assertTrue(securityOrderDao.existsById(1));
    }


    @Test
    public void countTest() {
        assertEquals(1, securityOrderDao.count());
    }


    @Test
    public void deleteAllTest() {
        securityOrderDao.deleteAll();
        assertEquals(0, securityOrderDao.count());
    }

}