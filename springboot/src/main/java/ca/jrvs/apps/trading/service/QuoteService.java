package ca.jrvs.apps.trading.service;


import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Helper method. Map an iexQuote to a quote entity
     * Note: 'iexQuote.getLatestPrice()==null' if the stock market is closed.
     */
    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
        Quote quote = new Quote();
        quote.setTicker((iexQuote.getSymbol()));
        quote.setLastPrice(iexQuote.getLatestPrice());
        quote.setBidPrice(iexQuote.getIexBidPrice());
        quote.setBidSize(iexQuote.getIexBidSize());
        quote.setAskPrice(iexQuote.getIexAskPrice());
        quote.setAskSize(iexQuote.getIexAskSize());
        if (quote.getAskPrice() == null) {
            quote.setAskPrice(0.0);
        }
        if (quote.getAskSize() == null) {
            quote.setAskSize(0L);
        }
        if (quote.getBidPrice() == null) {
            quote.setBidPrice(0.0);
        }
        if (quote.getBidSize() == null) {
            quote.setBidSize(0L);
        }

        return quote;
    }

    /**
     * Update quote table against IEX source
     * -get all quotes from the db
     * -for each ticker get iexQuote
     * -convert iexQuote to quote entity
     * -persist quote to db
     *
     * @return
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException        if unable to retrieve data
     * @throws IllegalArgumentException                           for invalid input
     */
    public List<Quote> updateMarketData() {
        List<Quote> quotes = findAllQuotes();
        for (Quote quote : quotes) {
            IexQuote iexQuote = findIexQuoteByTicker(String.valueOf(quote.getTicker()));
            quote = buildQuoteFromIexQuote(iexQuote);
        }
        saveQuotes(quotes);
        return quotes;
    }

    /**
     * Validate (against IEX) and save given ticketrs to quote table
     * <p>
     * -Get iexQuote(s)
     * -convert each iexQuote to Quote entity
     * - persist the quote to db
     *
     * @param quotes a list of quotes
     * @return updated quotes
     */
    public List<Quote> saveQuotes(List<Quote> quotes) {
        quoteDao.saveAll(quotes);
        return quotes;
    }

    /**
     * Helper method
     */
    public Quote saveQuote(String ticker) {
        Quote quote = buildQuoteFromIexQuote(findIexQuoteByTicker(ticker));
        return saveQuote(quote);
    }

    /**
     * Finds an IexQuote
     *
     * @param ticker ticker id
     * @return IexQuote object
     * @throws IllegalArgumentException if ticker is not found in IEX Cloud
     */
    public IexQuote findIexQuoteByTicker(String ticker) {
        return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker + "is invalid"));
    }

    /**
     * Updates/adds provided quote to quote table
     *
     * @param quote provided quote
     * @return updated/added quote
     */
    public Quote saveQuote(Quote quote) {
        return quoteDao.save(quote);
    }


    /**
     * Find all quotes from the quote table
     *
     * @return a list of quotes
     */
    public List<Quote> findAllQuotes() {
        return quoteDao.findAll();
    }

}
