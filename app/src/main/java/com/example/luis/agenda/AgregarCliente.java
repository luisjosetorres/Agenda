package com.example.luis.agenda;

import android.support.v7.app.*;
import android.os.*;
import android.widget.*;

public class AgregarCliente extends AppCompatActivity {

    EditText empresa,rfc,razonSocial,telefono,correo,direccion;
    Button enviar;

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
    }
}
