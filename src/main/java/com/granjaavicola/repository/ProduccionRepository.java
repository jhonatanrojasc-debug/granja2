package com.granjaavicola.repository;

import com.granjaavicola.model.Produccion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class ProduccionRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProduccionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Produccion> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        Produccion p = new Produccion();
        p.setId(rs.getInt("id"));
        p.setFecha(rs.getDate("fecha").toLocalDate());
        p.setCantidadHuevos(rs.getInt("cantidad_huevos"));
        p.setGalponId(rs.getInt("galpon_id"));
        return p;
    };

    public List<Produccion> findAll() {
        return jdbcTemplate.query("SELECT * FROM produccion ORDER BY fecha DESC", ROW_MAPPER);
    }

    public List<Produccion> findByGalponId(int galponId) {
        return jdbcTemplate.query("SELECT * FROM produccion WHERE galpon_id = ? ORDER BY fecha DESC", ROW_MAPPER, galponId);
    }

    public List<Produccion> findByFechaBetween(Date fechaInicio, Date fechaFin) {
        return jdbcTemplate.query(
            "SELECT * FROM produccion WHERE fecha BETWEEN ? AND ? ORDER BY fecha DESC",
            ROW_MAPPER, fechaInicio, fechaFin
        );
    }

    public Produccion findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM produccion WHERE id = ?", ROW_MAPPER, id);
    }

    public int getTotalHuevosByGalponId(int galponId) {
        Integer total = jdbcTemplate.queryForObject(
            "SELECT COALESCE(SUM(cantidad_huevos), 0) FROM produccion WHERE galpon_id = ?",
            Integer.class, galponId
        );
        return total != null ? total : 0;
    }

    public Produccion save(Produccion produccion) {
        jdbcTemplate.update(
            "INSERT INTO produccion (fecha, cantidad_huevos, galpon_id) VALUES (?, ?, ?)",
            produccion.getFecha(), produccion.getCantidadHuevos(), produccion.getGalponId()
        );
        return findById(jdbcTemplate.queryForObject("SELECT lastval()", Integer.class));
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM produccion WHERE id = ?", id);
    }
}