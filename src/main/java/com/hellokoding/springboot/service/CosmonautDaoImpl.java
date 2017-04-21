package com.hellokoding.springboot.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hellokoding.springboot.Cosmonaut;

@Repository
public class CosmonautDaoImpl implements CosmonautDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Cosmonaut findById(Integer id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM cosmonauts WHERE id=:id";

        Cosmonaut result = null;
        try {
            result = namedParameterJdbcTemplate
                    .queryForObject(sql, params, new CosmonautMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

        return result;

    }

    public List<Cosmonaut> findAll() {
        String sql = "SELECT * FROM cosmonauts";
        List<Cosmonaut> result = namedParameterJdbcTemplate.query(sql, new CosmonautMapper());
        return result;
    }

    @Override
    public void save(Cosmonaut cosmonaut) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO COSMONAUTS(NAME, SURNAME, BIRTHDAY, SUPERPOWER) "
                + "VALUES ( :name, :surname, :birthday, :superpower)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(cosmonaut), keyHolder);
        cosmonaut.setId(keyHolder.getKey().intValue());

    }

    @Override
    public void update(Cosmonaut cosmonaut) {

        String sql = "UPDATE cosmonauts SET NAME=:name, SURNAME=:surname, BIRTHDAY=:birthday, "
                + "SUPERPOWER=:superpower WHERE id=:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(cosmonaut));

    }

    public void delete(Integer id) {
        String sql = "DELETE FROM cosmonauts WHERE id= :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private static final class CosmonautMapper implements RowMapper<Cosmonaut> {

        public Cosmonaut mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cosmonaut cosmonaut = new Cosmonaut();
            cosmonaut.setId(rs.getInt("id"));
            cosmonaut.setName(rs.getString("name"));
            cosmonaut.setSurname(rs.getString("surname"));
            cosmonaut.setBirthday(rs.getString("birthday"));
            cosmonaut.setSuperpower(rs.getString("superpower"));
            return cosmonaut;
        }
    }

    private SqlParameterSource getSqlParameterByModel(Cosmonaut cosmonaut) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", cosmonaut.getId());
        paramSource.addValue("name", cosmonaut.getName());
        paramSource.addValue("surname", cosmonaut.getSurname());
        paramSource.addValue("birthday", cosmonaut.getBirthday());
        paramSource.addValue("superpower", cosmonaut.getSuperpower());

        return paramSource;
    }
}


