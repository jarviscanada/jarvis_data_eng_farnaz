package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoIntTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraderDao traderDao;

    private Trader trader = new Trader();
    private Account account = new Account();


    @Before
    public void insert() {
        trader.setFirstName("John");
        trader.setLastName("Doe");
        trader.setCountry("Canada");
        trader.setDob(Date.valueOf(LocalDate.of(2000, 2, 2)));
        trader.setEmail("john.doe@gmail.com");
        traderDao.save(trader);
        account.setTraderId(1);
        account.setAmount(500d);
        accountDao.save(account);
    }

    @After
    public void remove() {
        accountDao.deleteAll();
        traderDao.deleteAll();
    }

    @Test
    public void findByIdTest() {
        assertEquals(Optional.of(account), accountDao.findById(1));
    }

    @Test
    public void existsByIdTest() {
        assertTrue(accountDao.existsById(1));
    }

}