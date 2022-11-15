package com.example.appbarberfast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logo_barberfast, logo_google, logo_facebook, logo_twitter;
    EditText email, senha;
    Button btnEntrar;
    TextView txtCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo_barberfast = (ImageView) findViewById(R.id.logo_barberfast);
        logo_google = (ImageView) findViewById(R.id.logo_google);
        logo_facebook = (ImageView) findViewById(R.id.logo_facebook);
        logo_twitter = (ImageView) findViewById(R.id.logo_twitter);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txtCadastro = (TextView) findViewById(R.id.txtCadastro);
    }
}