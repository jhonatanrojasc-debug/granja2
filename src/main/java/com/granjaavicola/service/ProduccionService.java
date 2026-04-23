package com.granjaavicola.service;

import com.granjaavicola.model.Produccion;
import com.granjaavicola.repository.ProduccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProduccionService {

    private static final Logger log = LoggerFactory.getLogger(ProduccionService.class);
    private final ProduccionRepository produccionRepository;

    public ProduccionService(ProduccionRepository produccionRepository) {
        this.produccionRepository = produccionRepository;
    }

    public List<Produccion> listarProducciones() {
        log.info("Listando producciones");
        return produccionRepository.findAll();
    }

    public List<Produccion> listarProduccionesPorGalpon(int galponId) {
        log.info("Listando producciones del galpon: {}", galponId);
        return produccionRepository.findByGalponId(galponId);
    }

    public Produccion registrarProduccion(LocalDate fecha, int cantidad, int galponId) {
        log.info("Registrando {} huevos del galpon {} en fecha {}", cantidad, galponId, fecha);
        Produccion produccion = new Produccion(fecha, cantidad, galponId);
        return produccionRepository.save(produccion);
    }

    public int obtenerTotalHuevos(int galponId) {
        log.info("Obteniendo total de huevos del galpon: {}", galponId);
        return produccionRepository.getTotalHuevosByGalponId(galponId);
    }

    public List<Produccion> obtenerProduccionesPorRangoFechas(LocalDate inicio, LocalDate fin) {
        log.info("Obteniendo producciones entre {} y {}", inicio, fin);
        return produccionRepository.findByFechaBetween(
            java.sql.Date.valueOf(inicio),
            java.sql.Date.valueOf(fin)
        );
    }

    public String generarReporte(int galponId) {
        log.info("Generando reporte para galpon: {}", galponId);
        List<Produccion> producciones = produccionRepository.findByGalponId(galponId);
        int total = producciones.stream().mapToInt(Produccion::getCantidadHuevos).sum();
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE PRODUCCION ===\n");
        sb.append("Galpon: ").append(galponId).append("\n");
        sb.append("Total huevos: ").append(total).append("\n");
        sb.append("Registros: ").append(producciones.size()).append("\n");
        return sb.toString();
    }

    public void eliminarProduccion(int id) {
        log.info("Eliminando produccion con id: {}", id);
        produccionRepository.delete(id);
    }
}