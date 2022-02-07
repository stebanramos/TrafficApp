package com.example.traffic.Reporte;

public class Report {

    private int imagen;
    private String direccion;
    private boolean acidente;
    private boolean cierre;
    private boolean trafico_lento;

    public Report(int imagen, String direccion, boolean acidente, boolean cierre, boolean trafico_lento) {
        this.imagen = imagen;
        this.direccion = direccion;
        this.acidente = acidente;
        this.cierre = cierre;
        this.trafico_lento = trafico_lento;
    }

    public int getImagen() {
        return imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean isAcidente() {
        return acidente;
    }

    public boolean isCierre() {
        return cierre;
    }

    public boolean isTrafico_lento() {
        return trafico_lento;
    }
}
