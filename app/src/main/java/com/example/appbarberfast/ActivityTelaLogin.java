package com.example.appbarberfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTelaLogin extends AppCompatActivity {

    TextView txtCadastro, usuarioLogin, senhaLogin;
    Button btnEntrar;
    BancoController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = findViewById(R.id.btnEntrar);
        txtCadastro = findViewById(R.id.txtCadastro);
        usuarioLogin = findViewById(R.id.usuarioLogin);
        senhaLogin = findViewById(R.id.senhaLogin);
        db = new BancoController(this);

        abrirTelaCadastro();
        abrirTelaMenu();
    }

    public void abrirTelaCadastro() {
        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastro = new Intent(getApplicationContext(), ActivityTelaCadastro.class);
                startActivity(telaCadastro);
            }
        });
    }

    public void abrirTelaMenu() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = usuarioLogin.getText().toString();
                String senha = senhaLogin.getText().toString();

                if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senha))
                    Toast.makeText(ActivityTelaLogin.this, "Todos os campos são necessários", Toast.LENGTH_SHORT).show();
                else {
                    Boolean verificarSenha = db.verificarUsuarioSenha(usuario, senha);
                    if (verificarSenha == true) {
                        Toast.makeText(ActivityTelaLogin.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ActivityTelaAgendamento.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ActivityTelaLogin.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}