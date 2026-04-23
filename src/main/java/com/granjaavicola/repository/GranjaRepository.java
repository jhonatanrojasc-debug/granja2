package com.granjaavicola.repository;

import com.granjaavicola.model.Granja;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GranjaRepository {

    private final JdbcTemplate jdbcTemplate;

    public GranjaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Granja> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        Granja g = new Granja();
        g.setId(rs.getInt("id"));
        g.setNombre(rs.getString("nombre"));
        g.setUbicacion(rs.getString("ubicacion"));
        g.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
        return g;
    };

    public List<Granja> findAll() {
        return jdbcTemplate.query("SELECT * FROM granja ORDER BY id", ROW_MAPPER);
    }

    public Granja findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM granja WHERE id = ?", ROW_MAPPER, id);
    }

    public Granja findByNombre(String nombre) {
        return jdbcTemplate.queryForObject("SELECT * FROM granja WHERE nombre = ?", ROW_MAPPER, nombre);
    }

    public Granja save(Granja granja) {
        jdbcTemplate.update(
            "INSERT INTO granja (nombre, ubicacion) VALUES (?, ?)",
            granja.getNombre(), granja.getUbicacion()
        );
        return findByNombre(granja.getNombre());
    }

    public void update(Granja granja) {
        jdbcTemplate.update(
            "UPDATE granja SET nombre = ?, ubicacion = ? WHERE id = ?",
            granja.getNombre(), granja.getUbicacion(), granja.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM granja WHERE id = ?", id);
    }
}