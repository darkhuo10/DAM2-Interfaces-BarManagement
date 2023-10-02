package com.example.barpancho.modelo.objetos;

public class Pedido {
    private int idPedido;
    private int idMesa;
    private String nombreUsuario;
    private int idCamarero;
    private String fecha;

    public Pedido(int idMesa, String nombreUsuario, int idCamarero, String fecha) {
        this.idMesa = idMesa;
        this.nombreUsuario = nombreUsuario;
        this.idCamarero = idCamarero;
        this.fecha = fecha;
    }

    public Pedido(int idPedido, int idMesa, String nombreUsuario, int idCamarero, String fecha) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.nombreUsuario = nombreUsuario;
        this.idCamarero = idCamarero;
        this.fecha = fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getIdCamarero() {
        return idCamarero;
    }

    public String getFecha() {
        return fecha;
    }

}
