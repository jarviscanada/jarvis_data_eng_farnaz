package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao extends JdbcCrudDao<Quote, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate((dataSource));
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return ID_COLUMN_NAME;
    }

    @Override
    public Class<Quote> getEntityClass() {
        return Quote.class;
    }

    /**
     * @throws org.springframework.dao.DataAccessException for unexpected SQL result or SQL execution failure
     */
    @Override
    public <S extends Quote> S save(S quote) {
        if (existsById(String.valueOf(quote.getId()))) {
            int updatedRowNo = updateOne(quote);
            if (updatedRowNo != 1) {
                throw new DataRetrievalFailureException("Unable to update quote");
            }
        } else {
            addOne(quote);
        }
        return quote;
    }


    /**
     * helper method that saves one quote
     */
    private void addOne(Quote quote) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
        }
    }

    /**
     * Helper method that updates existing quote
     */
    public int updateOne(Quote quote) {
        String updateSql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, "
                + "ask_size=? WHERE ticker=?";
        return jdbcTemplate.update(updateSql, makeUpdateValues(quote));
    }

    /**
     * helper method that makes sql update values objects
     *
     * @param quote quote to generate values from
     * @return UPDATE_SQL
     */
    private Object[] makeUpdateValues(Quote quote) {
        Object[] updatedValues = new Object[6];
        updatedValues[0] = quote.getLastPrice();
        updatedValues[1] = quote.getBidPrice();
        updatedValues[2] = quote.getBidSize();
        updatedValues[3] = quote.getAskPrice();
        updatedValues[4] = quote.getAskSize();
        updatedValues[5] = quote.getTicker();
        return updatedValues;
    }

    /**
     * Updates all the provided quotes in the quote table
     *
     * @param <S>
     * @param quotes list of quotes
     * @return quotes list if succeeds in updating
     * @throws ResourceNotFoundException if quote ticker not found in quote table
     */
    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> quotes) {
        /*String updateSql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, "
                + "ask_size=? WHERE ticker=?";
        List<Object[]> batch = new ArrayList<>();
        quotes.forEach(quote -> {
            if (!existsById(String.valueOf(quote.getTicker()))) {
                throw new ResourceNotFoundException("Ticker not found:" + quote.getTicker());
            }
            Object[] values = makeUpdateValues(quote);
            batch.add(values);
        });
        int[] rows = jdbcTemplate.batchUpdate(updateSql, batch);
        int totalRow = Arrays.stream(rows).sum();
        if (totalRow != batch.size()) {
            throw new IncorrectResultSizeDataAccessException("Number of rows ", batch.size(), totalRow);
        }
        return (Iterable<S>) findAll();*/
        quotes.forEach(this::save);
        return quotes;
    }

    @Override
    public Optional<Quote> findById(String ticker) {
        Quote quote = null;
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
        try {
            quote = jdbcTemplate.queryForObject
                    (selectSql, BeanPropertyRowMapper.newInstance(Quote.class), ticker);
        } catch (EmptyResultDataAccessException ex) {
            logger.debug("Can't find quote id:" + ticker, ex);
        }

        return Optional.of(quote);
    }

    @Override
    public boolean existsById(String ticker) {
        String selectSql = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";

        boolean result = jdbcTemplate.queryForObject(selectSql, Integer.class, ticker) > 0;
        return result;
    }

    @Override
    public List<Quote> findAll() {
        String selectSql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class));
    }


    @Override
    public List<Quote> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    //DeleteById
    @Override
    public void deleteById(String id) {
        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
        jdbcTemplate.update(deleteSql, id);
    }

    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }
}