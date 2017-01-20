package com.example.luis.agenda.modelo;

import android.util.JsonReader;

import java.io.*;
import java.util.*;


public class JsonParserCliente {
    public List<Cliente> leerFlujoJson(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return leerArrayClientes(reader);
        } finally {
            reader.close();
        }

    }



    public List<Cliente> leerArrayClientes(JsonReader reader) throws IOException {
        ArrayList<Cliente> animales = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            animales.add(leerCliente(reader));
        }
        reader.endArray();
        return animales;
    }

    public Cliente leerCliente(JsonReader reader) throws IOException {
        String rfc = null;
        int numero = Integer.parseInt(null);
        String correo = null;
        String empresa = null;
        String direccion = null;
        String razon = null;
        long id= Long.parseLong(null);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "rfc":
                    rfc = reader.nextString();
                    break;
                case "empresa":
                    empresa = reader.nextString();
                    break;
                case "correo":
                    correo = reader.nextString();
                    break;
                case "direccion":
                    direccion = reader.nextString();
                    break;
                case "telefono":
                    numero = Integer.parseInt(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Cliente(id,rfc,correo,empresa,direccion,numero,razon);
    }

}
