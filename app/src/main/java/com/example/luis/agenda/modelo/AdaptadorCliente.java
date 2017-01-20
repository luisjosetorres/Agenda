package com.example.luis.agenda.modelo;

import android.content.*;
import android.view.*;
import android.widget.*;
import com.example.luis.agenda.*;
import java.util.*;

public class AdaptadorCliente extends ArrayAdapter<Cliente> {

    public AdaptadorCliente(Context context, List<Cliente> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = convertView;
        if (null == convertView) {
            v = inflater.inflate(R.layout.itemlista, parent, false);
        }

        TextView empresaCliente = (TextView)v.findViewById(R.id.empresaCliente);
        TextView rfcCliente = (TextView)v.findViewById(R.id.telefonoCliente);
        TextView emailCliente = (TextView)v.findViewById(R.id.emailCliente);
        TextView direccion = (TextView)v.findViewById(R.id.direccionCliente);
        TextView numero = (TextView)v.findViewById(R.id.telefonoCliente);

        Cliente item = getItem(position);

        empresaCliente.setText(item.getEmpresa());
        rfcCliente.setText(item.getRfc());
        emailCliente.setText(item.getEmail());
        direccion.setText(item.getDireccion());
        numero.setText(item.getTelefono());
        return v;
    }
}
