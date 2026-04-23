package com.granjaavicola.repository;

import com.granjaavicola.model.Ave;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AveRepository {

    private final JdbcTemplate jdbcTemplate;

    public AveRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Ave> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        Ave ave = new Ave();
        ave.setId(rs.getInt("id"));
        ave.setIdentificador(rs.getString("identificador"));
        ave.setRaza(rs.getString("raza"));
        ave.setEdad(rs.getInt("edad"));
        ave.setPeso(rs.getDouble("peso"));
        ave.setEstadoSalud(rs.getString("estado_salud"));
        int galponId = rs.getInt("galpon_id");
        ave.setGalponId(rs.wasNull() ? null : galponId);
        return ave;
    };

    public List<Ave> findAll() {
        return jdbcTemplate.query("SELECT * FROM ave ORDER BY id", ROW_MAPPER);
    }

    public List<Ave> findByGalponId(int galponId) {
        return jdbcTemplate.query("SELECT * FROM ave WHERE galpon_id = ? ORDER BY id", ROW_MAPPER, galponId);
    }

    public Ave findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ave WHERE id = ?", ROW_MAPPER, id);
    }

    public Ave findByIdentificador(String identificador) {
        return jdbcTemplate.queryForObject("SELECT * FROM ave WHERE identificador = ?", ROW_MAPPER, identificador);
    }

    public int countByGalponId(int galponId) {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM ave WHERE galpon_id = ?", Integer.class, galponId);
        return count != null ? count : 0;
    }

    public Ave save(Ave ave, Integer galponId) {
        jdbcTemplate.update(
            "INSERT INTO ave (identificador, raza, edad, peso, estado_salud, galpon_id) VALUES (?, ?, ?, ?, ?, ?)",
            ave.getIdentificador(), ave.getRaza(), ave.getEdad(), ave.getPeso(), ave.getEstadoSalud(), galponId
        );
        return findByIdentificador(ave.getIdentificador());
    }

    public void update(Ave ave) {
        jdbcTemplate.update(
            "UPDATE ave SET identificador = ?, raza = ?, edad = ?, peso = ?, estado_salud = ?, galpon_id = ? WHERE id = ?",
            ave.getIdentificador(), ave.getRaza(), ave.getEdad(), ave.getPeso(), ave.getEstadoSalud(), ave.getGalponId(), ave.getId()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM ave WHERE id = ?", id);
    }
}