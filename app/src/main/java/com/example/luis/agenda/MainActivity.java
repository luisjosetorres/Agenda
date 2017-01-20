package com.example.luis.agenda;

import android.content.*;
import android.support.v7.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.luis.agenda.modelo.*;
import org.json.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    Button agregar;
    ListView lista;
    ArrayList<Cliente> arrayList = new ArrayList<Cliente>();
    Cliente datos;

    private RequestQueue queue;
    private static final String URL="";
    private static final String URL2="";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue= Volley.newRequestQueue(this);
        agregar=(Button)findViewById(R.id.agregarClienteBtn);
        lista = (ListView) findViewById(R.id.listaClientes);

        agregarCliente();
        obtenerClientes();
    }

    public void agregarCliente(){
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AgregarCliente.class);
                startActivity(intent);
            }
        });
    }

    public void obtenerClientes(){
        request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.names().get(0).equals("estado")){
                        System.out.println("Obtiene los datos");
                        Toast.makeText(getApplicationContext(),jsonObject.getString("taxista"),Toast.LENGTH_SHORT).show();
                        //Intent intent=new Intent(Lista_Taxista.this,DetalleTaxista.class);
                        //startActivity(intent);
                        //startActivity(new Intent(getApplicationContext(),Welcome.class));
                    }else {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
        };

        queue.add(request);
    }
}
