package com.concesionario.Domain;

import com.concesionario.Controller.ExposicionInput;

import java.util.ArrayList;
import java.util.List;

public class Exposicion {
    private String codigoExposicion;
    private String nombreExposicion;
    private List<Coche> cochesEnExpo;


    public Exposicion(String codigoExposicion, String nombreExposicion) {
        this.codigoExposicion = codigoExposicion;
        this.nombreExposicion = nombreExposicion;
    }


    public String getNombreExposicion() {
        return nombreExposicion;
    }

    public void setNombreExposicion(String nombreExposicion) {
        this.nombreExposicion = nombreExposicion;
    }

    public String getCodigoExposicion() {
        return codigoExposicion;
    }

    public void setCodigoExposicion(String codigoExposicion) {
        this.codigoExposicion = codigoExposicion;
    }

    public List<Coche> getCochesEnExpo() {
        return cochesEnExpo;
    }

    public void setCochesEnExpo(List<Coche> cochesEnExpo) {
        this.cochesEnExpo = cochesEnExpo;
    }

    public void actualizar(ExposicionInput exposicionInput) {

        this.nombreExposicion = exposicionInput.getNombreExposicion();

    }
}
