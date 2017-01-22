package com.example.luis.agenda;

import android.app.Activity;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.luis.agenda.controlador.*;
import com.example.luis.agenda.modelo.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class MainActivity extends Activity {

    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;
    Button agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.listaClientes);
        agregar=(Button)findViewById(R.id.agregarClienteBtn);
        agregarClientes();
        try{
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().
                        execute(
                                new URL("http://agenda.hol.es/api/obtenerClientes.php"));
            } else {
                Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Log.e("Error","No se conecto");
        }
    }

    public void agregarClientes(){
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AgregarCliente.class);
                startActivity(intent);
            }
        });
    }

    public class JsonTask extends AsyncTask<URL, Void, List<Cliente>> {

        @Override
        protected List<Cliente> doInBackground(URL... urls) {
            List<Cliente> clientes = null;
            try {
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);
                int statusCode = con.getResponseCode();
                if(statusCode!=200) {
                    clientes = new ArrayList<>();
                    clientes.add(new Cliente("Error",null,null,null,null,null));
                } else {
                    InputStream in = new BufferedInputStream(con.getInputStream());
                    JsonClienteParser parser = new JsonClienteParser();
                    clientes = parser.leerFlujoJson(in);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                con.disconnect();
            }
            return clientes;
        }

        @Override
        protected void onPostExecute(List<Cliente> clientes) {
            if(clientes!=null) {
                adaptador = new AdaptadorDeClientes(getBaseContext(), clientes);
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
