package com.example.luis.agenda.controlador;

import android.util.*;
import com.example.luis.agenda.modelo.*;
import java.io.*;
import java.util.*;

public class JsonContactoParser{

        public List<ContactoModelo> leerFlujoJson(InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            try {
                return leerArrayContacto(reader);
            } finally {
                reader.close();
            }
        }

        public List<ContactoModelo> leerArrayContacto(JsonReader reader) throws IOException {
            ArrayList<ContactoModelo> contacto = new ArrayList<>();
            reader.beginArray();
            while (reader.hasNext()) {
                contacto.add(leerContacto(reader));
            }
            reader.endArray();
            return contacto;
        }

        public ContactoModelo leerContacto(JsonReader reader) throws IOException {
            String nombre=null;
            String telefono=null;
            String apellido=null;
            String email=null;
            String celular=null;
            String puesto=null;
            String nextel=null;

            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                    case "nombreContacto":
                        nombre = reader.nextString();
                        break;
                    case "telOficinaContacto":
                        telefono = reader.nextString();
                        break;
                    case "apellidoContacto":
                        apellido = reader.nextString();
                        break;
                    case "emailContacto":
                        email = reader.nextString();
                        break;
                    case "celularContacto":
                        celular = reader.nextString();
                        break;
                    case "puestoContacto":
                        puesto = reader.nextString();
                        break;
                    case "nextelContacto":
                        nextel = reader.nextString();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
            return new ContactoModelo(nombre,apellido,celular,telefono,email,puesto,nextel);
        }
}
