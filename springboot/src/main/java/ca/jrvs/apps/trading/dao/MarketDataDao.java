package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MarketdataDao is responsible for getting Quotes from IEX
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
                         MarketDataConfig marketDataConfig) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    /**
     * Gets an IexQuote (helper method for findAllById)
     *
     * @param ticker
     * @throws IllegalArgumentException      if a given ticker is invalid
     * @throws DataRetrievalFailureException if HTTP request failed
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() == 1) {
            return Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }
    }

    /**
     * Gets quotes from IEX
     *
     * @param tickers list of tickers
     * @return a list of IexQuote objects
     * @throws IllegalArgumentException      if any ticker is invalid or ticker is empty
     * @throws DataRetrievalFailureException if HTTP request failed
     */
    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {
        List<IexQuote> quotes = new ArrayList<IexQuote>();
        Iterator<String> iterator = tickers.iterator();

        while (iterator.hasNext()) {
            //Create URL
            String ticker = iterator.next();
            String url = String.format(IEX_BATCH_URL, ticker);
            //Execute the request
            Optional<String> response = executeHttpGet(url);
            // Create POJOs
            JSONObject jsonObject = new JSONObject(response.get());
            IexQuote quote;
            Iterator<String> it = jsonObject.keys();
            while (it.hasNext()) {
                String symbol = it.next();

                JSONObject jsonQuote = jsonObject.getJSONObject(symbol);
                String quoteString = jsonQuote.get("quote").toString();
                try {
                    quote = parseJson(quoteString, IexQuote.class);
                    quotes.add(quote);
                } catch (IOException ex) {
                    throw new RuntimeException("Failed to create IexQuote object : " + ex);
                }
            }
        }
        return quotes;

    }

    /**
     * Executes a get request and returns http entity/body as a string
     *
     * @param url resource URL
     * @return http response body or Optional.empty for 404 response
     * @throws DataRetrievalFailureException if HTTP fails or status code is unexpected
     */
    private Optional<String> executeHttpGet(String url) {
        HttpGet request = new HttpGet(url);
        CloseableHttpClient getHttpClient = getHttpClient();
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient.execute(request);
        } catch (IOException ex) {
            logger.error("The HTTP request execution failed", ex);
        }
        HttpEntity httpEntity = response.getEntity();
        try {
            return Optional.ofNullable(EntityUtils.toString(httpEntity));
        } catch (IOException ex) {
            logger.error("Failed to convert to string", ex);
            throw new RuntimeException("Failed in returning http body");
        }
    }


    /**
     * Borrow a HTTP client from the httpClientConnectionManager
     *
     * @return a hTTP client
     */
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                //prevent connectionManager shutdown when calling httpClient.close()
                .setConnectionManagerShared(true).build();
    }


    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> S save(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }


    /**
     * Parses JSON string to an object
     *
     * @param <T>   Type
     * @param json  JSON string
     * @param clazz object class
     * @return Object of specified class
     * @throws IOException
     */
    private <T> T parseJson(String json, Class clazz) throws IOException {
        ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) m.readValue(json, clazz);
    }


}