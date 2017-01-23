package com.example.luis.agenda;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.example.luis.agenda.controlador.*;
import com.example.luis.agenda.modelo.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Contacto extends AppCompatActivity {

    String id;
    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        agregar=(Button)findViewById(R.id.agregarContactoBtn);
        id=getIntent().getStringExtra("id");
        agregarContacto();
        System.out.println("el id es "+id);
        conectar();
    }

    public void agregarContacto(){
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contacto.this,AgregarContacto.class);
                startActivity(intent);
            }
        });

    }

    public void conectar(){
        try{
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().
                        execute(
                                new URL("http://agenda.hol.es/api/obtenerContacto.php?cliente_idcliente="+id));
            } else {
                Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Log.e("Error","No se conecto");
        }
    }


    public class JsonTask extends AsyncTask<URL, Void, List<ContactoModelo>> {

        @Override
        protected List<ContactoModelo> doInBackground(URL... urls) {
            List<ContactoModelo> contacto = null;
            try {
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);
                int statusCode = con.getResponseCode();
                if(statusCode!=200) {
                    contacto = new ArrayList<>();
                    contacto.add(new ContactoModelo("Error",null,null,null,null,null,null));
                } else {
                    InputStream in = new BufferedInputStream(con.getInputStream());
                    JsonContactoParser parser = new JsonContactoParser();
                    contacto = parser.leerFlujoJson(in);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                con.disconnect();
            }
            return contacto;
        }

        @Override
        protected void onPostExecute(List<ContactoModelo> contacto) {
            if(contacto!=null) {
                adaptador = new AdaptadorDeContacto(getBaseContext(), contacto);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        getBaseContext(),
                        "Ocurrió un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
