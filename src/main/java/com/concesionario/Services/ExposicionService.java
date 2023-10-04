package com.concesionario.Services;

import com.concesionario.Controller.InvalidExposicionException;
import com.concesionario.Domain.Coche;
import com.concesionario.Domain.Exposicion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExposicionService {
    private Set<String> exposicionesExistentes = new HashSet<>();
    private List<Exposicion> exposiciones = new ArrayList<>();
    public List<Exposicion> listarExposiciones(){
        return exposiciones;
    }

    public Exposicion obtenerExposicionPorCodigo(String codigoExposicion ){
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(codigoExposicion)){
                return exposicion;
            }
        }
        return null;
    }
    public void validarExposicion(Exposicion exposicion) throws InvalidExposicionException {
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
