package com.granjaavicola.repository;

import com.granjaavicola.model.Empleado;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class EmpleadoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmpleadoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Empleado> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        Empleado e = new Empleado();
        e.setId(rs.getInt("id"));
        e.setNombre(rs.getString("nombre"));
        e.setCargo(rs.getString("cargo"));
        e.setTurno(rs.getString("turno"));
        e.setGranjaId(rs.getInt("granja_id"));
        return e;
    };

    public List<Empleado> findAll() {
        return jdbcTemplate.query("SELECT * FROM empleado ORDER BY id", ROW_MAPPER);
    }

    public List<Empleado> findByGranjaId(int granjaId) {
        return jdbcTemplate.query("SELECT * FROM empleado WHERE granja_id = ? ORDER BY id", ROW_MAPPER, granjaId);
    }

    public Empleado findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM empleado WHERE id = ?", ROW_MAPPER, id);
    }

    public Empleado save(Empleado empleado, int granjaId) {
        jdbcTemplate.update(
            "INSERT INTO empleado (nombre, cargo, turno, granja_id) VALUES (?, ?, ?, ?)",
            empleado.getNombre(), empleado.getCargo(), empleado.getTurno(), granjaId
        );
        return findById(jdbcTemplate.queryForObject("SELECT lastval()", Integer.class));
    }

    public void update(Empleado empleado) {
        jdbcTemplate.update(
            "UPDATE empleado SET nombre = ?, cargo = ?, turno = ? WHERE id = ?",
            empleado.getNombre(), empleado.getCargo(), empleado.getTurno(), empleado.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM empleado WHERE id = ?", id);
    }
}