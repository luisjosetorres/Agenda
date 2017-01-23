package com.example.luis.agenda.controlador;

import android.content.*;
import android.view.*;
import android.widget.*;
import com.example.luis.agenda.*;
import com.example.luis.agenda.modelo.*;
import java.util.*;

public class AdaptadorDeContacto extends ArrayAdapter<ContactoModelo> {

    public AdaptadorDeContacto(Context context, List<ContactoModelo> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        if (null == convertView) {
            v = inflater.inflate(R.layout.itemcontacto, parent, false);
        }

        TextView nombre = (TextView) v.findViewById(R.id.nombreContacto);
        TextView apellido = (TextView) v.findViewById(R.id.apellidoContacto);
        TextView puesto = (TextView) v.findViewById(R.id.puestoContacto);
        TextView telefono = (TextView) v.findViewById(R.id.telefonoContacto);
        TextView correo = (TextView) v.findViewById(R.id.emailContacto);

        ContactoModelo item = getItem(position);

        nombre.setText(item.getNombre());
        apellido.setText(item.getApellido());
        puesto.setText(item.getPuesto());
        telefono.setText(item.getTelOficina());
        correo.setText(item.getEmail());
        return v;
    }

}