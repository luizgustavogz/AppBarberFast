package com.example.appbarberfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fevziomurtekin.payview.Payview;

import android.os.Bundle;

public class ActivityTelaPagamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        Payview payview = findViewById(R.id.payview);

        payview.setPayOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pagamento realizado!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ActivityTelaAgendamento.class);
                startActivity(intent);
            }
        });
    }
}
