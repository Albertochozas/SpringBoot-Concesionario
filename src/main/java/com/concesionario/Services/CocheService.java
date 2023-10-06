package com.concesionario.Services;
import Excepciones.CocheNotFoundException;
import com.concesionario.Controller.CochesInput;
import Excepciones.InvalidCocheException;
import com.concesionario.Domain.Coche;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CocheService {
    private Set<String> matriculasExistentes = new HashSet<>();
    private List<Coche> coches = new ArrayList<>();
    public List<Coche> listarCoches(){
        return coches;
    }

    public List<Coche> listarCochesPorMatricula(String matricula) {
        List<Coche> cochesPorMatricula = new ArrayList<>();
        for (Coche coche : coches) {
            if (coche.getMatricula().equals(matricula)) {
                cochesPorMatricula.add(coche);
            }
        }
        return cochesPorMatricula;
    }

    public void agregarCoche(CochesInput cochesInput) throws InvalidCocheException {
        Coche coche = new Coche(cochesInput.getMatricula(), cochesInput.getModelo());
        validarCoche(coche);
        coches.add(coche);
    }

    public void modificarCoche(String matricula, CochesInput cochesInput) throws CocheNotFoundException, InvalidCocheException {
        Optional<Coche> cocheExistente = coches.stream().filter(coche -> coche.getMatricula().equals(matricula)).findFirst();
        if (cocheExistente.isPresent()) {
            Coche coche = cocheExistente.get();

            coche.setMatricula(cochesInput.getMatricula());
            coche.setModelo(cochesInput.getModelo());

            validarCoche(coche);
        } else {
            throw new CocheNotFoundException("No se encuentra el coche");
        }
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



}
