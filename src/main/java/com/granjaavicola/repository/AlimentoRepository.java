package com.granjaavicola.repository;

import com.granjaavicola.model.Alimento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class AlimentoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlimentoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Alimento> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        Alimento a = new Alimento();
        a.setId(rs.getInt("id"));
        a.setTipo(rs.getString("tipo"));
        a.setCantidadDisponible(rs.getInt("cantidad_disponible"));
        Date fecha = rs.getDate("fecha_caducidad");
        if (fecha != null) {
            a.setFechaCaducidad(fecha.toLocalDate());
        }
        return a;
    };

    public List<Alimento> findAll() {
        return jdbcTemplate.query("SELECT * FROM alimento ORDER BY id", ROW_MAPPER);
    }

    public Alimento findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM alimento WHERE id = ?", ROW_MAPPER, id);
    }

    public Alimento findByTipo(String tipo) {
        return jdbcTemplate.queryForObject("SELECT * FROM alimento WHERE tipo = ?", ROW_MAPPER, tipo);
    }

    public Alimento save(Alimento alimento) {
        jdbcTemplate.update(
            "INSERT INTO alimento (tipo, cantidad_disponible, fecha_caducidad) VALUES (?, ?, ?)",
            alimento.getTipo(), alimento.getCantidadDisponible(), alimento.getFechaCaducidad()
        );
        return findByTipo(alimento.getTipo());
    }

    public void update(Alimento alimento) {
        jdbcTemplate.update(
            "UPDATE alimento SET tipo = ?, cantidad_disponible = ?, fecha_caducidad = ? WHERE id = ?",
            alimento.getTipo(), alimento.getCantidadDisponible(), alimento.getFechaCaducidad(), alimento.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM alimento WHERE id = ?", id);
    }
}