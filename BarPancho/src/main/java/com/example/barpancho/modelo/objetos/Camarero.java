package com.example.barpancho.modelo.objetos;

public class Camarero {
    private int idCamarero;
    private String nomCamarero;
    private String apellido1;
    private String telefono;
    private String email;

    public Camarero(int idCamarero, String nomCamarero, String apellido1, String telefono, String email) {
        this.idCamarero = idCamarero;
        this.nomCamarero = nomCamarero;
        this.apellido1 = apellido1;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdCamarero() {
        return idCamarero;
    }

    public void setIdCamarero(int idCamarero) {
        this.idCamarero = idCamarero;
    }

    public String getNomCamarero() {
        return nomCamarero;
    }

    public void setNomCamarero(String nomCamarero) {
        this.nomCamarero = nomCamarero;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
