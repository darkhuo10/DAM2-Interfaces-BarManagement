package com.example.barpancho.modelo.objetos;

public class Usuario {
    private String nombreUsuario;
    private int idCamarero;
    private String password;

    public Usuario(String nombreUsuario, int idCamarero, String password) {
        this.nombreUsuario = nombreUsuario;
        this.idCamarero = idCamarero;
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdCamarero() {
        return idCamarero;
    }

    public void setIdCamarero(int idCamarero) {
        this.idCamarero = idCamarero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
