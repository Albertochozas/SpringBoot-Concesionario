package com.concesionario.Services;

import Excepciones.ExposicionNotFoundException;
import Excepciones.InvalidCodigoExposicionException;
import Excepciones.InvalidExposicionException;
import com.concesionario.Controller.*;
import com.concesionario.Domain.Coche;
import com.concesionario.Domain.Exposicion;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExposicionService {
    private Set<String> exposicionesExistentes = new HashSet<>();
    private List<Exposicion> exposiciones = new ArrayList<>();
    public List<Exposicion> listarExposiciones(){
        return exposiciones;
    }

    public ExposicionOutput obtenerExposicionPorCodigo(String codigoExposicion) throws ExposicionNotFoundException {
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(codigoExposicion)) {
                return new ExposicionOutput(exposicion.getCodigoExposicion());
            }
        }
        throw new ExposicionNotFoundException("No se encuentra la exposición");
    }
    public ExposicionOutput modificarExposicion(String codigoExposicion, ExposicionInput exposicionInput)
            throws ExposicionNotFoundException, InvalidExposicionException {
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(codigoExposicion)) {
                validarExposicion(exposicionInput);
                exposicion.actualizar(exposicionInput);
                return new ExposicionOutput(exposicion.getCodigoExposicion());
            }
        }
        throw new ExposicionNotFoundException("No se encuentra la exposición");
    }
    public ExposicionOutput agregarExposicion(ExposicionInput exposicionInput) throws InvalidExposicionException {
        validarExposicion(exposicionInput);
        Exposicion exposicion = new Exposicion(exposicionInput.getCodigoExposicion(), exposicionInput.getNombreExposicion());
        exposiciones.add(exposicion);
        return new ExposicionOutput(exposicion.getCodigoExposicion());
    }
    public List<Coche> obtenerCochesDeExposicion(String codigoExposicion) throws ExposicionNotFoundException {
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(codigoExposicion)) {
                return exposicion.getCochesEnExpo();
            }
        }
        throw new ExposicionNotFoundException("No se encuentra la exposición");
    }
    public void agregarCocheAExposicion(String codigoExposicion, Coche coche) throws ExposicionNotFoundException {
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(codigoExposicion)) {
                exposicion.getCochesEnExpo().add(coche);
                return;
            }
        }
        throw new ExposicionNotFoundException("No se encuentra la exposición");
    }
    public void validarCodigoExposicion(String codigoExposicion) throws InvalidCodigoExposicionException {
        if (codigoExposicion == null || codigoExposicion.isEmpty()) {
            throw new InvalidCodigoExposicionException("El código de exposición no puede estar vacío");
        }
    }
    public void validarExposicion(ExposicionInput exposicion) throws InvalidExposicionException {
        if (exposicion == null) {
            throw new InvalidExposicionException("El nombre de la exposicion no puede ser nulo");
        }
        if (exposicion.getCodigoExposicion() == null || exposicion.getCodigoExposicion().isEmpty()) {
            throw new InvalidExposicionException("El codigo de exposición no puede estar vacío");
        }
        if (exposicion.getNombreExposicion() == null || exposicion.getNombreExposicion().isEmpty()) {
            throw new InvalidExposicionException("El nombre de la exposicion no puede estar vacío");
        }
        if (exposicionesExistentes.contains(exposicion.getCodigoExposicion())) {
            throw new InvalidExposicionException("Ya existe una exposición con ese códigp");
        }
        exposicionesExistentes.add(exposicion.getCodigoExposicion());

    }

}
