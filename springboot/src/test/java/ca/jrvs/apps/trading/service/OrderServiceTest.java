package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private AccountDao accountDao;
    @Mock
    private SecurityOrderDao securityOrderDao;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private PositionDao positionDao;

    //injecting mocked dependencies to the testing class via constructor
    @InjectMocks
    private OrderService orderService;

    @Test
    public void testAll() {
        MarketOrderDto marketOrderDto = new MarketOrderDto();
        marketOrderDto.setAccountId(5);
        marketOrderDto.setTicker("AAAA");
        marketOrderDto.setSize(50);

        Quote quote = new Quote();
        quote.setTicker("AAAA");
        quote.setLastPrice(150.0);
        quote.setAskPrice(50.0);
        quote.setBidPrice(50.0);
        quote.setAskSize(5L);
        quote.setBidSize(5L);

        Account account = new Account();
        account.setId(5);
        account.setTraderId(5);
        account.setAmount(150.0);
        when(accountDao.findById(any())).thenReturn(java.util.Optional.of(account));

        Position position = new Position();
        position.setAccountId(5);
        position.setTicker("AAAA");
        position.setPosition(5);
        List<Position> positions = new ArrayList<>();
        positions.add(position);
        when(positionDao.findByIdAndTicker(any(), any())).thenReturn(positions);

        when(quoteDao.existsById(any())).thenReturn(true);
        when(quoteDao.findById(any())).thenReturn(java.util.Optional.of(quote));


        SecurityOrder expected = new SecurityOrder();
        expected.setId(5);
        expected.setAccountId(5);
        expected.setStatus("Rejected");
        expected.setPrice(quote.getAskPrice());
        expected.setSize(50);
        expected.setTicker("AAAA");
        when(securityOrderDao.save(any()))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        SecurityOrder actual = orderService.executeMarketOrder(marketOrderDto);
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getSize(), actual.getSize());

        expected.setPrice(quote.getBidPrice());
        expected.setSize(50);
        actual = orderService.executeMarketOrder(marketOrderDto);
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getSize(), actual.getSize());
    }
}