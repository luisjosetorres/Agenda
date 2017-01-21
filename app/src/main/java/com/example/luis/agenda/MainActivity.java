package com.example.luis.agenda;

import android.content.*;
import android.support.v7.app.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.example.luis.agenda.modelo.*;
import org.json.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    Button agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar=(Button)findViewById(R.id.agregarClienteBtn);
        agregarCliente();

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

    }

