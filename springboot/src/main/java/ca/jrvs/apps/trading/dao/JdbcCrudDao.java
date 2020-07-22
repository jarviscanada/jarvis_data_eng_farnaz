package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<E>, E> implements CrudRepository<T, E> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

    abstract public JdbcTemplate getJdbcTemplate();

    abstract public SimpleJdbcInsert getSimpleInsert();

    abstract public String getTableName();

    abstract public String getIdColumnName();

    protected abstract Class<T> getEntityClass();

    /**
     * Saves an entity and update auto-generated integer ID
     *
     * @param entity to be saved
     * @param <S>    type of entity
     * @return saved entity
     */
    @Override
    public <S extends T> S save(S entity) {
        if (existsById((entity.getId()))) {
            if (updateOne(entity) != 1) {
                throw new DataRetrievalFailureException("Unable to update quote");
            }
        } else {
            addOne(entity);
        }
        return entity;
    }

    /**
     * Helper method to add new entity into entity table
     *
     * @param entity to be added
     * @param <S>    type of entity
     */
    private <S extends T> void addOne(S entity) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
        E newId = (E) getSimpleInsert().executeAndReturnKey(parameterSource);
        entity.setId(newId);
    }

    /**
     * Helper method to update entity fields in entity table
     *
     * @param entity to be updated
     * @param <S>    type of entity
     * @return row number of entity
     */
    public <S extends T> int updateOne(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Updates all the provided entities in the entity table
     *
     * @param iterable list of entities
     * @param <S>      type of entity
     * @return updated list of entities
     */
    @Override
    public abstract <S extends T> Iterable<S> saveAll(Iterable<S> iterable);

    /**
     * Finds an enity given its id
     *
     * @param id of entity to be found
     * @return found entity or Optional.empty()
     */
    @Override
    public Optional<T> findById(E id) {
        Optional<T> entity = Optional.empty();
        String findSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        try {
            entity = Optional.ofNullable(getJdbcTemplate().queryForObject(findSql,
                    BeanPropertyRowMapper.newInstance(getEntityClass()), id));
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.debug("Can't find trader id: " + id, e);
        }
        return entity;
    }

    /**
     * Checks if entity exists in entity table
     *
     * @param id to be searched
     * @return true if found, false otherwise
     */
    @Override
    public boolean existsById(E id) {
        if (id == null) {
            return false;
        }
        String countSql =
                "SELECT COUNT(*) FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        long count = getJdbcTemplate().queryForObject(countSql, Long.class, id);
        return count != 0L;
    }

    /**
     * Finds all entities in entity table
     *
     * @return list of all entities in entity table
     */
    @Override
    public List<T> findAll() {
        return getJdbcTemplate()
                .query("SELECT * FROM " + getTableName(),
                        BeanPropertyRowMapper.newInstance(getEntityClass()));
    }

    /**
     * Finds all entities of provided ids in entity table
     *
     * @param ids list of ids to be found
     * @return list of found entities
     */
    @Override
    public Iterable<T> findAllById(Iterable<E> ids) {
        List<T> allEntities = new ArrayList<>();
        for (E id : ids) {
            List<T> entityOfCurrentId = getJdbcTemplate()
                    .query("SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?",
                            BeanPropertyRowMapper.newInstance(getEntityClass()), id);
            allEntities.addAll(entityOfCurrentId);
        }
        return allEntities;
    }

    /**
     * Counts total number of entities in entity table
     *
     * @return count of entities in entity table
     */
    @Override
    public long count() {
        String countSql = "SELECT COUNT(*) FROM " + getTableName();
        return getJdbcTemplate().queryForObject(countSql, Long.class);
    }

    /**
     * Deletes an entity when its id is provided
     *
     * @param id of entity to be deleted
     */
    @Override
    public void deleteById(E id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ? ";
        getJdbcTemplate().update(deleteSql, id);
    }

    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Deletes all entities in the entity table
     */
    @Override
    public void deleteAll() {
        String deleteSql = "DELETE FROM " + getTableName();
        getJdbcTemplate().update(deleteSql);
    }
}