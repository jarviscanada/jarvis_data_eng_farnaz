package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends JdbcCrudDao<Account, Integer> {

    private static final String TABLE_NAME = "account";
    private static final String ID_COLUMN_NAME = "id";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public AccountDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate((dataSource));
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_COLUMN_NAME);
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
    public Class<Account> getEntityClass() {
        return Account.class;
    }


    public int updateOne(Account account) {
        String updatedSql = "UPDATE account SET trader_id=?, amount=? WHERE id=?";
        return jdbcTemplate.update(updatedSql, new Object[]{account.getTraderId(), account.getAmount(), account.getId()});
    }

    @Override
    public <S extends Account> Iterable<S> saveAll(Iterable<S> accounts) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}