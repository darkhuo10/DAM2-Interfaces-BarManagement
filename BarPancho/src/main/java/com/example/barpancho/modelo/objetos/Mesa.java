package com.example.barpancho.modelo.objetos;

public class Mesa {
    private int idMesa;
    private int nComensasles;
    private String estado;

    public Mesa(int idMesa, int nComensasles, String estado) {
        this.idMesa = idMesa;
        this.nComensasles = nComensasles;
        this.estado = estado;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getnComensasles() {
        return nComensasles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
