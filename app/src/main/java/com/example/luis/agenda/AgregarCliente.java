package com.example.luis.agenda;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.*;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AgregarCliente extends AppCompatActivity {

    EditText empresa,rfc,razonSocial,telefono,correo,direccion;
    Button enviar;
    String tel,emp,email,dir,rfcC,razon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        empresa=(EditText)findViewById(R.id.empresaCliente);
        rfc=(EditText)findViewById(R.id.rfcCliente);
        razonSocial=(EditText)findViewById(R.id.razonSocialCliente);
        telefono=(EditText)findViewById(R.id.telefonoCliente);
        correo=(EditText)findViewById(R.id.emailCliente);
        direccion=(EditText)findViewById(R.id.direccionCliente);
        enviar=(Button)findViewById(R.id.agregarClienteBtn);
        enviarCliente();

    }

    public void enviarCliente(){
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    conectarse();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void conectarse() throws MalformedURLException {
        tel= telefono.getText().toString();
        emp=empresa.getText().toString();
        email=correo.getText().toString();
        dir=direccion.getText().toString();
        rfcC=rfc.getText().toString();
        razon=razonSocial.getText().toString();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new PostCommentTask().
                    execute(
                            new URL("http://monstalkers.hostoi.com/data/insert_comments.php"));
            finish();
        }
        else{
            Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
        }

    }


    public class PostCommentTask extends AsyncTask<URL, Void, Void> {

        @Override
        protected Void doInBackground(URL... urls) {
            HttpURLConnection con = null;

            try {
                String data = "body=" + URLEncoder.encode(tel,"UTF-8")+
                        URLEncoder.encode(email,"UTF-8")+URLEncoder.encode(dir,"UTF-8")+
                        URLEncoder.encode(rfcC,"UTF-8")+URLEncoder.encode(razon,"UTF-8");

                con = (HttpURLConnection)urls[0].openConnection();

                con.setDoOutput(true);

                con.setFixedLengthStreamingMode(data.getBytes().length);

                con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                OutputStream out = new BufferedOutputStream(con.getOutputStream());

                out.write(data.getBytes());
                out.flush();
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(con!=null)
                    con.disconnect();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void s) {
            Toast.makeText(getBaseContext(), "Cliente agregado", Toast.LENGTH_LONG).show();
        }
    }
}
