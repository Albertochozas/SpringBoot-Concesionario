package com.concesionario.Controller;
import Excepciones.ExposicionNotFoundException;
import Excepciones.InvalidExposicionException;
import com.concesionario.Domain.Coche;
import com.concesionario.Domain.Exposicion;
import com.concesionario.Services.ExposicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/exposiciones")
public class ExposicionController {
    private final ExposicionService exposicionService;

    @Autowired
    public ExposicionController(ExposicionService exposicionService) {
        this.exposicionService = exposicionService;
    }

    @GetMapping("/listarexposiciones")
    public ResponseEntity<List<Exposicion>> listarExposiciones() {
        List<Exposicion> exposiciones = exposicionService.listarExposiciones();
        return ResponseEntity.ok(exposiciones);
    }
    @GetMapping("/{codigoExposicion}")
    public ResponseEntity<ExposicionOutput> obtenerExposicionPorCodigo(@PathVariable String codigoExposicion) {
        try {
            ExposicionOutput exposicion = exposicionService.obtenerExposicionPorCodigo(codigoExposicion);
            return ResponseEntity.ok(exposicion);
        } catch (ExposicionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{codigoExposicion}/coches")
    public ResponseEntity<List<Coche>> obtenerCochesDeExposicion(@PathVariable String codigoExposicion) {
        try {
            List<Coche> coches = exposicionService.obtenerCochesDeExposicion(codigoExposicion);
            return ResponseEntity.ok(coches);
        } catch (ExposicionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addexposicion")
    public ResponseEntity<ExposicionOutput> agregarExposicion(@RequestBody ExposicionInput exposicionInput) {
        try {
            ExposicionOutput exposicion = exposicionService.agregarExposicion(exposicionInput);
            return ResponseEntity.ok(exposicion);
        } catch (InvalidExposicionException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{codigoExposicion}/agregarcoche")
    public ResponseEntity<String> agregarCocheAExposicion(@PathVariable String codigoExposicion, @RequestBody Coche coche) {
        try {
            exposicionService.agregarCocheAExposicion(codigoExposicion, coche);
            return ResponseEntity.ok("Coche añadido a la exposición correctamente");
        } catch (ExposicionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigoExposicion}/modificar")
    public ResponseEntity<ExposicionOutput> modificarExposicion(@PathVariable String codigoExposicion, @RequestBody ExposicionInput exposicionInput) {
        try {
            ExposicionOutput exposicion = exposicionService.modificarExposicion(codigoExposicion, exposicionInput);
            return ResponseEntity.ok(exposicion);
        } catch (ExposicionNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidExposicionException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}




