package com.example.luis.agenda.modelo;


import org.json.JSONException;
import org.json.JSONObject;

public class ContactoModelo {
    private String nombre;
    private String apellido;
    private String celular;
    private String telOficina;
    private String email;
    private String puesto;
    private String nextel;
    private String clienteId;

    public ContactoModelo(String nombre, String apellido, String celular, String telOficina, String email, String puesto, String nextel) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telOficina = telOficina;
        this.email = email;
        this.puesto = puesto;
        this.nextel = nextel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelOficina() {
        return telOficina;
    }

    public void setTelOficina(String telOficina) {
        this.telOficina = telOficina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNextel() {
        return nextel;
    }

    public void setNextel(String nextel) {
        this.nextel = nextel;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String toJson(){
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("idcontacto",getClienteId());
            jsonObject.put("nombreContacto",getNombre());
            jsonObject.put("apellidoContacto",getApellido());
            jsonObject.put("celularContacto",getCelular());
            jsonObject.put("telOficinaContacto",getTelOficina());
            jsonObject.put("emailContacto",getEmail());
            jsonObject.put("puestoContacto",getPuesto());
            jsonObject.put("nextelContacto",getNextel());
            jsonObject.put("cliente_idcliente",getClienteId());

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
