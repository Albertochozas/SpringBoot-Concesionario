package com.concesionario.Controller;


import com.concesionario.Domain.Coche;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class CochesController {
    private List<Coche> coches = new ArrayList<>();

    public List<Coche> getCoches() {
        return coches;
    }

    @GetMapping("/coches/{id}")
    public ResponseEntity<Coche> obtenerCochePorId(@PathVariable String id) {
        for (Coche coche : coches) {
            if (coche.getMatricula().equals(id)) {
                return ResponseEntity.ok(coche);
            }
        }
        return ResponseEntity.notFound().build();
    }

    //@GetMapping("/coches/{id}/propietario")
  //  public ResponseEntity<String> obtenerPropietarioDeCoche(@PathVariable String id) {
     //   Coche coche = obtenerCochePorId(id).getBody(); //  .getbody = no es bueno llamar metodo controller en la misma clase de controller
     //   if (coche != null) {
      //      return ResponseEntity.ok(coche.getPropietario());
     //   }
     //   return ResponseEntity.notFound().build();
  //  }

    @PostMapping("/addcoche")
    public ResponseEntity<String> anyadirCoche(@RequestBody String matricula,@RequestBody String marca, @RequestBody String modelo,@RequestBody String anyo){
        Coche coche = new Coche(matricula,marca,modelo,anyo);
        coches.add(coche);
        return ResponseEntity.ok("Coche añadido correctamente");
    }


    @GetMapping("/listarcoches")
    public ResponseEntity<List<Coche>> listarCoches() {
        return ResponseEntity.ok(coches);
    }
}
