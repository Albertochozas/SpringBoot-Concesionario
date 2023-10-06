package com.concesionario.Controller;


import Excepciones.CocheNotFoundException;
import Excepciones.InvalidCocheException;
import com.concesionario.Services.CocheService;
import com.concesionario.Domain.Coche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coches")
public class CocheController{
    private CocheService cocheService;
    @Autowired
    public CocheController(CocheService cocheService) {
        this.cocheService = cocheService;

    }

    @GetMapping("/listarcoches")
    public ResponseEntity<List<Coche>> listarCoches() {
        List<Coche> coches = cocheService.listarCoches();
        return ResponseEntity.ok(coches);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<Coche>> listarCochesPorMatricula(@PathVariable String matricula) {
        List<Coche> cochesPorMatricula = cocheService.listarCochesPorMatricula(matricula);
        return ResponseEntity.ok(cochesPorMatricula);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<String> modificarCoche(@PathVariable String matricula, @RequestBody CochesInput cochesInput) {
        try {
            cocheService.modificarCoche(matricula, cochesInput);
            return ResponseEntity.ok("Coche modificado correctamente");
        } catch (CocheNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidCocheException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/addcoche")
    public ResponseEntity<String> agregarCoche(@RequestBody CochesInput cochesInput) {
        try {
            cocheService.agregarCoche(cochesInput);
            return ResponseEntity.ok("Coche añadido correctamente");
        } catch (InvalidCocheException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}


