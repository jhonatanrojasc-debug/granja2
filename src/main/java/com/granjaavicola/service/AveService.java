package com.granjaavicola.service;

import com.granjaavicola.model.Ave;
import com.granjaavicola.repository.AveRepository;
import com.granjaavicola.repository.GalponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AveService {

    private static final Logger log = LoggerFactory.getLogger(AveService.class);
    private final AveRepository aveRepository;
    private final GalponRepository galponRepository;

    public AveService(AveRepository aveRepository, GalponRepository galponRepository) {
        this.aveRepository = aveRepository;
        this.galponRepository = galponRepository;
    }

    public List<Ave> listarAves() {
        log.info("Listando todas las aves");
        return aveRepository.findAll();
    }

    public List<Ave> listarAvesPorGalpon(int galponId) {
        log.info("Listando aves del galpon: {}", galponId);
        return aveRepository.findByGalponId(galponId);
    }

    public Ave obtenerAve(int id) {
        log.info("Obteniendo ave con id: {}", id);
        return aveRepository.findById(id);
    }

    public Ave registrarAve(String identificador, String raza, int edad, double peso, int galponId) {
        log.info("Registrando ave {} en galpon {}", identificador, galponId);
        var galpon = galponRepository.findById(galponId);
        if (galpon == null) {
            throw new IllegalArgumentException("Galpon no encontrado");
        }
        int avesActuales = aveRepository.countByGalponId(galponId);
        if (avesActuales >= galpon.getCapacidad()) {
            throw new IllegalStateException("Capacidad maxima del galpon alcanzada");
        }
        Ave ave = new Ave(identificador, raza, edad, peso);
        return aveRepository.save(ave, galponId);
    }

    public void alimentar(int aveId) {
        log.info("Alimentando ave: {}", aveId);
        Ave ave = aveRepository.findById(aveId);
        if (ave == null) {
            throw new IllegalArgumentException("Ave no encontrada");
        }
        log.info("Ave {} ha sido alimentada", ave.getIdentificador());
    }

    public void cambiarEstadoSalud(int aveId, String estado) {
        log.info("Cambiando estado de salud de ave {}: {}", aveId, estado);
        Ave ave = aveRepository.findById(aveId);
        ave.setEstadoSalud(estado);
        aveRepository.update(ave);
    }

    public void eliminarAve(int id) {
        log.info("Eliminando ave con id: {}", id);
        aveRepository.delete(id);
    }
}