package com.example.luis.agenda.modelo;

public class Cliente {
    private String telefono;
    private String empresa;
    private String email;
    private String direccion;
    private String rfc;
    private String razonSocial;

    public Cliente(String telefono, String empresa, String email, String direccion, String rfc, String razonSocial) {
        this.telefono = telefono;
        this.empresa = empresa;
        this.email = email;
        this.direccion = direccion;
        this.rfc = rfc;
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}
