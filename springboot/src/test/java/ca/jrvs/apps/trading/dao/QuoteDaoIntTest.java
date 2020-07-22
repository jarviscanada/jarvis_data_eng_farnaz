package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
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
public class QuoteDaoIntTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private QuoteDao quoteDao;

    private Quote savedQuote = new Quote();


    @Before
    public void insertOne() {
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10L);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10L);
        savedQuote.setId(("aapl"));
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);
    }

    @After
    public void deleteOne() {
        quoteDao.deleteById(String.valueOf(savedQuote.getId()));
    }

    @Test
    public void testFindById() {
        quoteDao.findById("aapl");
    }

    @Test
    public void testExistsById() {
        assertTrue(quoteDao.existsById(("aapl")));
    }

    @Test
    public void testDeletebyId() {
        quoteDao.deleteById("aapl");
    }

}