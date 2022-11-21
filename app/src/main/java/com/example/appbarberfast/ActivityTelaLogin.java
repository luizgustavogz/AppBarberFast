package com.example.appbarberfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityTelaLogin extends AppCompatActivity {

    Button btnEntrar;
    TextView txtCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txtCadastro = (TextView) findViewById(R.id.txtCadastro);

        //abrirTelaCadastro();
        //abrirTelaMenu();
    }

    /*
    public void abrirTelaCadastro(){
        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastro = new Intent(getApplicationContext(), ActivityTelaCadastro.class);
                startActivity(telaCadastro);
            }
        });
    }
    */

    /*
    public void abrirTelaMenu(){
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaMenu = new Intent(getApplicationContext(), ActivityTelaMenu.class);
                startActivity(telaMenu);
            }
        });
    }
    */
}