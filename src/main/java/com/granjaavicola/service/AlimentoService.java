package com.granjaavicola.service;

import com.granjaavicola.model.Alimento;
import com.granjaavicola.repository.AlimentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlimentoService {

    private static final Logger log = LoggerFactory.getLogger(AlimentoService.class);
    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public List<Alimento> listarAlimentos() {
        log.info("Listando alimentos");
        return alimentoRepository.findAll();
    }

    public Alimento obtenerAlimento(int id) {
        log.info("Obteniendo alimento con id: {}", id);
        return alimentoRepository.findById(id);
    }

    public Alimento crearAlimento(String tipo, int cantidad, String fechaCaducidad) {
        log.info("Creando alimento: {} cantidad: {}", tipo, cantidad);
        Alimento alimento = new Alimento(tipo, cantidad,
            java.time.LocalDate.parse(fechaCaducidad));
        return alimentoRepository.save(alimento);
    }

    public boolean consumir(int id, int cantidad) {
        log.info("Consumiendo {} unidades de alimento id: {}", cantidad, id);
        Alimento alimento = alimentoRepository.findById(id);
        if (alimento.getCantidadDisponible() >= cantidad) {
            alimento.setCantidadDisponible(alimento.getCantidadDisponible() - cantidad);
            alimentoRepository.update(alimento);
            log.info("Consumidas {} unidades. Quedan: {}", cantidad, alimento.getCantidadDisponible());
            return true;
        }
        log.warn("No hay suficiente alimento. Disponible: {}", alimento.getCantidadDisponible());
        return false;
    }

    public void reabastecer(int id, int cantidad) {
        log.info("Reabasteciendo {} unidades a alimento id: {}", cantidad, id);
        Alimento alimento = alimentoRepository.findById(id);
        alimento.setCantidadDisponible(alimento.getCantidadDisponible() + cantidad);
        alimentoRepository.update(alimento);
    }

    public void eliminarAlimento(int id) {
        log.info("Eliminando alimento con id: {}", id);
        alimentoRepository.delete(id);
    }
}