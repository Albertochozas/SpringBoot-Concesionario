package com.concesionario.Controller;


import com.concesionario.Domain.Coche;
import com.concesionario.Domain.Exposiciones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ExposicionController {
    private List<Exposiciones> exposiciones = new ArrayList<>();

    @GetMapping("/exposiciones")
    public List<Exposiciones> listarExposiciones() {
        return exposiciones;
    }

    @GetMapping("/exposiciones/{id}")
    public ResponseEntity<Exposiciones> getExposicion(@PathVariable String id) {
        for (Exposiciones exposicion : exposiciones) {
            if (exposicion.getNumeroExposicion().equals(id))
                return ResponseEntity.ok(exposicion);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("exposiciones/{id}/coches")
    public ResponseEntity<List<Coche>> getCochesExposicion(@PathVariable String id) {
        for (Exposiciones exposicion : exposiciones) {
            if (exposicion.getNumeroExposicion().equals(id)) {
                if (exposicion.getCochesEnExpo().isEmpty())
                    return ResponseEntity.noContent().build();
                return ResponseEntity.ok(exposicion.getCochesEnExpo());

            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/exposiciones/{idExpo}/coches/{idCoche}")
    public ResponseEntity<Coche> getCocheDeExpo(@PathVariable String idExpo, @PathVariable String idCoche) {
        for (Exposiciones exposicion : exposiciones) {
            if (exposicion.getNumeroExposicion().equals(idExpo)) {
                for (Coche coche : exposicion.getCochesEnExpo()) {
                    if (coche.getMatricula().equals(idCoche))
                        return ResponseEntity.ok(coche);
                }
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
