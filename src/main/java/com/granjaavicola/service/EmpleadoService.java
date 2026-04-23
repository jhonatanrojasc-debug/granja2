package com.granjaavicola.service;

import com.granjaavicola.model.Empleado;
import com.granjaavicola.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoService {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoService.class);
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> listarEmpleados() {
        log.info("Listando empleados");
        return empleadoRepository.findAll();
    }

    public List<Empleado> listarEmpleadosPorGranja(int granjaId) {
        log.info("Listando empleados de granja: {}", granjaId);
        return empleadoRepository.findByGranjaId(granjaId);
    }

    public Empleado obtenerEmpleado(int id) {
        log.info("Obteniendo empleado con id: {}", id);
        return empleadoRepository.findById(id);
    }

    public Empleado contratarEmpleado(String nombre, String cargo, String turno, int granjaId) {
        log.info("Contratando empleado: {} como {} en granja {}", nombre, cargo, granjaId);
        Empleado empleado = new Empleado(nombre, cargo, turno);
        return empleadoRepository.save(empleado, granjaId);
    }

    public void supervisarGalpon(int empleadoId, int galponId) {
        log.info("Empleado {} supervisando galpon {}", empleadoId, galponId);
        Empleado emp = empleadoRepository.findById(empleadoId);
        log.info("{} supervisa el galpon {}", emp.getNombre(), galponId);
    }

    public void registrarActividad(int empleadoId, String actividad) {
        log.info("Empleado {} registrando actividad: {}", empleadoId, actividad);
    }

    public void actualizarEmpleado(Empleado empleado) {
        log.info("Actualizando empleado: {}", empleado.getNombre());
        empleadoRepository.update(empleado);
    }

    public void desvincularEmpleado(int id) {
        log.info("Desvinculando empleado con id: {}", id);
        empleadoRepository.delete(id);
    }
}