package com.example.appbarberfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityTelaSplash extends AppCompatActivity {

    private static final int TEMPO_TELA_ABERTA = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AppDataBase appDataBase = new AppDataBase(this);

        inicializarTelaLogin();
    }

    private void inicializarTelaLogin(){
        new Timer().schedule(new TimerTask(){
            @Override
            public void run(){
                finish();
                Intent telaLogin = new Intent(getApplicationContext(), ActivityTelaLogin.class);
                startActivity(telaLogin);
            }
        }, TEMPO_TELA_ABERTA);
    }
}