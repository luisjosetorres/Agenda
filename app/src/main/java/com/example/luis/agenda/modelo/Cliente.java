package com.example.luis.agenda.modelo;

public class Cliente {

    protected long id;
    protected String rfc;
    protected String email;
    protected String empresa;
    protected String direccion;
    protected int telefono;
    protected String razon;

    public Cliente(long id, String rfc, String email, String empresa, String direccion, int telefono, String razon) {
        this.id = id;
        this.rfc = rfc;
        this.email = email;
        this.empresa = empresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.razon = razon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}
