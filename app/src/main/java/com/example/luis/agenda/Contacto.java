package com.example.luis.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contacto extends AppCompatActivity {

    Button agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        agregar=(Button)findViewById(R.id.agregarContactoBtn);

        agregarContacto();
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
}
