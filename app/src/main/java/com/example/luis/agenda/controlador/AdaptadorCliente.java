package com.example.luis.agenda.controlador;

import android.content.*;
import android.view.*;
import android.widget.*;

import com.example.luis.agenda.R;

import java.util.*;

public class AdaptadorCliente extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public AdaptadorCliente(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        TextView empresa;
        TextView telefono;
        TextView email;
        TextView rfc;

        inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.itemlista, parent, false);

        resultp = data.get(position);
        empresa = (TextView) itemView.findViewById(R.id.empresaCliente);
        empresa.setText(resultp.get("empresa"));
        telefono = (TextView) itemView.findViewById(R.id.telefonoCliente);
        telefono.setText(resultp.get("telefonoCliente"));
        email = (TextView) itemView.findViewById(R.id.emailCliente);
        email.setText(resultp.get("emailCliente"));
        rfc = (TextView) itemView.findViewById(R.id.rfcCliente);
        rfc.setText(resultp.get("rfcCliente"));
        return itemView;
    }

}