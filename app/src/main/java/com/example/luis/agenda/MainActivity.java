package com.example.luis.agenda;

import android.app.*;
import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.example.luis.agenda.controlador.*;
import org.json.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    Button agregar;
    ArrayList<HashMap<String, String>> arraylistclientes;
    JSONArray jsonarray;
    JSONObject jsonobject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new clientes().execute();
        agregar=(Button)findViewById(R.id.agregarClienteBtn);
        agregarCliente();
        buscarInternet();

    }

    public void buscarInternet(){
        if (!redDisponible()){
            Toast.makeText(getBaseContext(),"No hay Conexión a Internet...", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    public boolean redDisponible() {
        boolean red = false;
        Context context = getApplicationContext();
        ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectMgr != null) {
            NetworkInfo[] netInfo = connectMgr.getAllNetworkInfo();
            if (netInfo != null) {
                for (NetworkInfo net : netInfo) {
                    if (net.getState() == NetworkInfo.State.CONNECTED) {
                        red = true;
                    }
                }
            }
        }
        return red;
    }

    public void agregarCliente(){
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Intento");
                Intent intent=new Intent(MainActivity.this,AgregarCliente.class);
                startActivity(intent);
            }
        });
    }

    class clientes extends AsyncTask<Void, Void, Void>{
        ProgressDialog pDialog;
        HashMap<String, String> resultp = new HashMap<String, String>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando Clientes...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            arraylistclientes= new ArrayList<HashMap<String, String>>();
            jsonobject = JSONfunctions.getJSONfromURL("http://agenda.hol.es/api/conectToDB.php");
            try {
                try {
                    jsonarray = jsonobject.getJSONArray("Datos");
                    System.out.println(jsonarray);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        jsonobject = jsonarray.getJSONObject(i);
                        // Retrive JSON Objects
                        map.put("empresa",jsonobject.getString("empresa"));
                        map.put("rfc", jsonobject.getString("idCliente"));

                        // Set the JSON Objects into the array
                        arraylistclientes.add(map);

                    }
                }catch(Exception ignored) {
                    System.out.println("sin poner en el registro");
                }
            } catch (Exception er) {
                er.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            ListView listview;
            AdaptadorCliente adapter;
            listview = (ListView) findViewById(R.id.listaClientes);
            if(arraylistclientes.size() ==0) {
                Toast.makeText(MainActivity.this,"No hay informacion",Toast.LENGTH_LONG).show();
            }
            else {
                adapter = new AdaptadorCliente (MainActivity.this,arraylistclientes);
                listview.setAdapter(adapter);
            }
        }
    }
}



