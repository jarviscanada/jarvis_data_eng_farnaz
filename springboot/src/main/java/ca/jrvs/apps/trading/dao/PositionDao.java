package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {

    private static final String TABLE_NAME = "position";
    private static final String ID_COLUMN_NAME = "account_id";
    private static final String TICKER_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PositionDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate((dataSource));
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String getIdColumnName() {
        return ID_COLUMN_NAME;
    }

    public String getTickerColumnName() {
        return TICKER_COLUMN_NAME;
    }

    Class<Position> getEntityClass() {
        return Position.class;
    }

    public boolean existsById(Integer accountId) {
        if (accountId == null) {
            return false;
        }
        String countSql =
                "SELECT COUNT(*) FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        long count = getJdbcTemplate().queryForObject(countSql, Long.class, accountId);
        return count != 0L;
    }


    public boolean existsByTicker(String ticker) {
        if (ticker == null) {
            return false;
        }
        String countSql =
                "SELECT COUNT(*) FROM " + getTableName() + " WHERE " + getTickerColumnName() + " = ?";
        long count = getJdbcTemplate().queryForObject(countSql, Long.class, ticker);
        return count != 0L;
    }


    public List<Position> find(Integer accountId, String ticker) {
        return getJdbcTemplate()
                .query("SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ? AND "
                                + getTickerColumnName() + " = ?",
                        BeanPropertyRowMapper.newInstance(getEntityClass()), accountId, ticker);
    }

    public List<Position> findAll() {
        return getJdbcTemplate()
                .query("SELECT * FROM " + getTableName(),
                        BeanPropertyRowMapper.newInstance(getEntityClass()));
    }

    public List<Position> findAllById(List<Integer> accountIds) {
        List<Position> allPositions = new ArrayList<>();
        for (Integer id : accountIds) {
            List<Position> positionsOfCurrentId = getJdbcTemplate()
                    .query("SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?",
                            BeanPropertyRowMapper.newInstance(getEntityClass()), id);
            allPositions.addAll(positionsOfCurrentId);
        }
        return allPositions;
    }

    public List<Position> findByIdAndTicker(Integer accountId, String ticker) {
        return getJdbcTemplate()
                .query("SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ? AND "
                                + getTickerColumnName() + " = ?",
                        BeanPropertyRowMapper.newInstance(getEntityClass()), accountId, ticker);
    }


}