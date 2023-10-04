package com.concesionario.Services;


import com.concesionario.Controller.CocheController;
import com.concesionario.Controller.CochesInput;
import com.concesionario.Controller.InvalidCocheException;
import com.concesionario.Domain.Coche;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CocheService {
    private Set<String> matriculasExistentes = new HashSet<>();
    private List<Coche> coches = new ArrayList<>();
    public List<Coche> listarCoches(){
        return coches;
    }

    public Coche obtenerCochePorMatricula(String matricula){
        for (Coche coche : coches) {
            if (coche.getMatricula().equals(matricula)){
                return coche;
            }
        }
        return null;
    }

    public void validarCoche(Coche coche) throws InvalidCocheException {
        if (coche == null) {
            throw new InvalidCocheException("El coche no puede ser nulo");
        }
        if (coche.getMatricula() == null || coche.getMatricula().isEmpty()) {
            throw new InvalidCocheException("La matrícula no puede estar vacía");
        }
        if (!coche.getMatricula().matches("\\d{4}[a-zA-Z]{3}")) {
            throw new InvalidCocheException("La matrícula no cumple con el formato requerido (4 números seguidos de 3 letras)");
        }
        if (coche.getModelo() == null || coche.getModelo().isEmpty()) {
            throw new InvalidCocheException("El modelo no puede estar vacío");
        }
        if (matriculasExistentes.contains(coche.getMatricula())) {
            throw new InvalidCocheException("Ya existe un coche con esta matrícula");
        }
        matriculasExistentes.add(coche.getMatricula());

    }

    public void agregarCoche(Coche coche) {
        coches.add(coche);
    }


}
