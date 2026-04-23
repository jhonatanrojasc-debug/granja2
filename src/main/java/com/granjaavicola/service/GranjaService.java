package com.granjaavicola.service;

import com.granjaavicola.model.Galpon;
import com.granjaavicola.model.Granja;
import com.granjaavicola.repository.GalponRepository;
import com.granjaavicola.repository.GranjaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GranjaService {

    private static final Logger log = LoggerFactory.getLogger(GranjaService.class);
    private final GranjaRepository granjaRepository;
    private final GalponRepository galponRepository;

    public GranjaService(GranjaRepository granjaRepository, GalponRepository galponRepository) {
        this.granjaRepository = granjaRepository;
        this.galponRepository = galponRepository;
    }

    public List<Granja> listarGranjas() {
        log.info("Listando todas las granjas");
        return granjaRepository.findAll();
    }

    public Granja obtenerGranja(int id) {
        log.info("Obteniendo granja con id: {}", id);
        return granjaRepository.findById(id);
    }

    public Granja crearGranja(String nombre, String ubicacion) {
        log.info("Creando granja: {} en {}", nombre, ubicacion);
        Granja granja = new Granja(nombre, ubicacion);
        return granjaRepository.save(granja);
    }

    public void actualizarGranja(Granja granja) {
        log.info("Actualizando granja: {}", granja.getNombre());
        granjaRepository.update(granja);
    }

    public void eliminarGranja(int id) {
        log.info("Eliminando granja con id: {}", id);
        granjaRepository.delete(id);
    }

    public List<Galpon> listarGalpones(int granjaId) {
        log.info("Listando galpones de granja: {}", granjaId);
        return galponRepository.findByGranjaId(granjaId);
    }

    public Galpon crearGalpon(String identificador, int capacidad, int granjaId) {
        log.info("Creando galpon {} con capacidad {}", identificador, capacidad);
        Granja granja = granjaRepository.findById(granjaId);
        if (granja == null) {
            throw new IllegalArgumentException("Granja no encontrada");
        }
        Galpon galpon = new Galpon(identificador, capacidad);
        return galponRepository.save(galpon, granjaId);
    }

    public String generarInformeGeneral(int granjaId) {
        log.info("Generando informe general para granja: {}", granjaId);
        Granja granja = granjaRepository.findById(granjaId);
        List<Galpon> galpones = galponRepository.findByGranjaId(granjaId);
        StringBuilder sb = new StringBuilder();
        sb.append("=== INFORME DE GRANJA ===\n");
        sb.append("Nombre: ").append(granja.getNombre()).append("\n");
        sb.append("Ubicacion: ").append(granja.getUbicacion()).append("\n");
        sb.append("Total galpones: ").append(galpones.size()).append("\n");
        for (Galpon g : galpones) {
            sb.append("- Galpon ").append(g.getIdentificador())
              .append(" (Capacidad: ").append(g.getCapacidad()).append(")\n");
        }
        return sb.toString();
    }
}