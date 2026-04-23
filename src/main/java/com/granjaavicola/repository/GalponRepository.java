package com.granjaavicola.repository;

import com.granjaavicola.model.Galpon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GalponRepository {

    private final JdbcTemplate jdbcTemplate;

    public GalponRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Galpon> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        Galpon g = new Galpon();
        g.setId(rs.getInt("id"));
        g.setIdentificador(rs.getString("identificador"));
        g.setCapacidad(rs.getInt("capacidad"));
        g.setGranjaId(rs.getInt("granja_id"));
        return g;
    };

    public List<Galpon> findAll() {
        return jdbcTemplate.query("SELECT * FROM galpon ORDER BY id", ROW_MAPPER);
    }

    public List<Galpon> findByGranjaId(int granjaId) {
        return jdbcTemplate.query("SELECT * FROM galpon WHERE granja_id = ? ORDER BY id", ROW_MAPPER, granjaId);
    }

    public Galpon findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM galpon WHERE id = ?", ROW_MAPPER, id);
    }

    public Galpon findByIdentificador(String identificador) {
        return jdbcTemplate.queryForObject("SELECT * FROM galpon WHERE identificador = ?", ROW_MAPPER, identificador);
    }

    public Galpon save(Galpon galpon, int granjaId) {
        jdbcTemplate.update(
            "INSERT INTO galpon (identificador, capacidad, granja_id) VALUES (?, ?, ?)",
            galpon.getIdentificador(), galpon.getCapacidad(), granjaId
        );
        return findByIdentificador(galpon.getIdentificador());
    }

    public void update(Galpon galpon) {
        jdbcTemplate.update(
            "UPDATE galpon SET identificador = ?, capacidad = ? WHERE id = ?",
            galpon.getIdentificador(), galpon.getCapacidad(), galpon.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM galpon WHERE id = ?", id);
    }
}