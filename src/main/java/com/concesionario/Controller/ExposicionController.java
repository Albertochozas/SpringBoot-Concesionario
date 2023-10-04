package com.concesionario.Controller;


import com.concesionario.Domain.Coche;
import com.concesionario.Domain.Exposicion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ExposicionController {
    private List<Exposicion> exposiciones = new ArrayList<>();

    @GetMapping("/exposiciones")
    public List<Exposicion> listarExposiciones() {
        return exposiciones;
    }

    @GetMapping("/exposiciones/{id}")
    public ResponseEntity<Exposicion> getExposicion(@PathVariable String id) {
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(id))
                return ResponseEntity.ok(exposicion);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("exposiciones/{id}/coches")
    public ResponseEntity<List<Coche>> getCochesExposicion(@PathVariable String id) {
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(id)) {
                if (exposicion.getCochesEnExpo().isEmpty())
                    return ResponseEntity.noContent().build();
                return ResponseEntity.ok(exposicion.getCochesEnExpo());

            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/exposiciones/{idExpo}/coches/{idCoche}")
    public ResponseEntity<Coche> getCocheDeExpo(@PathVariable String idExpo, @PathVariable String idCoche) {
        for (Exposicion exposicion : exposiciones) {
            if (exposicion.getCodigoExposicion().equals(idExpo)) {
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
