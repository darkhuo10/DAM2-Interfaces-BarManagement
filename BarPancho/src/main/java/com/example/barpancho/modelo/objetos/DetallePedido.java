package com.example.barpancho.modelo.objetos;

public class DetallePedido {
    private int idPedido;
    private int idProducto;
    private int unidadesProducto;

    public DetallePedido(int idProducto, int idPedido, int unidadesProducto) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.unidadesProducto = unidadesProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getUnidadesProducto() {
        return unidadesProducto;
    }

    @Override
    public String toString() {
        return "Pedido: "  + idPedido +
                " Producto: " + idProducto +
                " Unidades producto: " + unidadesProducto;
    }
}
