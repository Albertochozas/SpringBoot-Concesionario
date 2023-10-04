package com.concesionario.Controller;


import com.concesionario.Services.CocheService;
import com.concesionario.Domain.Coche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coches")
public class CocheController{
    private final CocheService cocheService;
    @Autowired
    public CocheController(CocheService cocheService) {
        this.cocheService = cocheService;

    }


    @GetMapping("/{id}")
    public ResponseEntity<Coche> obtenerCochePorMatricula(@PathVariable String matricula) {
        Coche coche = cocheService.obtenerCochePorMatricula(matricula);
        if (coche != null) {
            return ResponseEntity.ok(coche);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listarcoches")
    public ResponseEntity<List<Coche>> listarCoches() {
        List<Coche> coches = cocheService.listarCoches();
        return ResponseEntity.ok(coches);
    }
    @PostMapping("/addcoche")
    public ResponseEntity<String> agregarCoche(@RequestBody CochesInput cochesInput) {
        try {
            Coche coche = new Coche(cochesInput.getMatricula(),cochesInput.getModelo());
            cocheService.validarCoche(coche);
            cocheService.agregarCoche(coche);
            return ResponseEntity.ok("Coche añadido correctamente");
        } catch (InvalidCocheException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}




//@GetMapping("/coches/{id}/propietario")
//  public ResponseEntity<String> obtenerPropietarioDeCoche(@PathVariable String id) {
//   Coche coche = obtenerCochePorId(id).getBody(); //
//   if (coche != null) {
//      return ResponseEntity.ok(coche.getPropietario());
//   }
//   return ResponseEntity.notFound().build();
//  }


