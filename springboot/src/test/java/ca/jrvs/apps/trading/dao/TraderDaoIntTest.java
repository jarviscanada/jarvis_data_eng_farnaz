package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.util.Lists;
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
@SpringBootTest(classes = TestConfig.class)
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Autowired
    private TraderDao traderDao;
    private Trader savedTrader;


    @Before
    public void insertOne() {
        savedTrader = new Trader();
        savedTrader.setFirstName("John");
        savedTrader.setLastName("Doe");
        savedTrader.setDob(Date.valueOf(LocalDate.parse("2000-02-02")));
        savedTrader.setCountry("Canada");
        savedTrader.setEmail("Johndoe@gmail.com");
        traderDao.save(savedTrader);
    }

    @After
    public void deleteOne() {
        traderDao.deleteById(savedTrader.getId());
    }


    @Test
    public void findAllById() {
        List<Trader> traders = Lists.newArrayList(traderDao.findAllById(Arrays.asList(savedTrader.getId(), -1)));
        assertEquals(1, traders.size());
        assertEquals(savedTrader.getCountry(), traders.get(0).getCountry());

    }

}