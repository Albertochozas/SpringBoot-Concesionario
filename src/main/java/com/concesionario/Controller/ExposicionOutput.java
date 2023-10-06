package com.concesionario.Controller;

public class ExposicionOutput {
    private String codigoExposicion;
    private String nombreExposicion;

    public ExposicionOutput(String codigoExposicion) {
        this.codigoExposicion = codigoExposicion;

    }

    public String getCodigoExposicion() {
        return codigoExposicion;
    }

    public void setCodigoExposicion(String codigoExposicion) {
        this.codigoExposicion = codigoExposicion;
    }

    public String getNombreExposicion() {
        return nombreExposicion;
    }

    public void setNombreExposicion(String nombreExposicion) {
        this.nombreExposicion = nombreExposicion;
    }
}
