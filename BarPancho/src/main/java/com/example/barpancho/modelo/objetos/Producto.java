package com.example.barpancho.modelo.objetos;

public class Producto {
    private Integer idProducto;
    private String nombreProd;
    private double precio;

    public Producto(Integer idProducto, String nombreProd, double precio) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.precio = precio;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return  nombreProd +
                " - " + precio + " €";
    }
}
