package com.concesionario.Domain;

import java.util.ArrayList;
import java.util.List;

public class Exposiciones {
    private String numeroExposicion;
    private List<Coche> cochesEnExpo;

    public Exposiciones(String numeroExposicion) {
        this.numeroExposicion = numeroExposicion;
        this.cochesEnExpo = new ArrayList<>();
    }

    public String getNumeroExposicion() {
        return numeroExposicion;
    }

    public void setNumeroExposicion(String numeroExposicion) {
        this.numeroExposicion = numeroExposicion;
    }

    public List<Coche> getCochesEnExpo() {
        return cochesEnExpo;
    }

    public void setCochesEnExpo(List<Coche> cochesEnExpo) {
        this.cochesEnExpo = cochesEnExpo;
    }
}
