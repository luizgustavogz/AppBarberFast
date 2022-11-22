package com.example.appbarberfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityTelaCadastro extends AppCompatActivity {

    Button btnVoltar, btnCadastrar;
    EditText txtNome, txtUsuario, txtEmail, txtSenha, txtConfSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtNome = findViewById(R.id.txtNome);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        txtConfSenha = findViewById(R.id.txtConfSenha);

        voltarLogin();
        cadastrarUsuario();
    }

    public void voltarLogin() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLogin = new Intent(getApplicationContext(), ActivityTelaLogin.class);
                startActivity(telaLogin);
            }
        });
    }

    public void cadastrarUsuario() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController db = new BancoController(getBaseContext());

                String nome = txtNome.getText().toString();
                String usuario = txtUsuario.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                String confsenha = txtConfSenha.getText().toString();

                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(usuario) || TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(confsenha))
                    Toast.makeText(ActivityTelaCadastro.this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show();
                else {
                    if (senha.equals(confsenha)) {
                        Boolean verificarUsuario = db.verificarUsuario(usuario);
                        if (verificarUsuario == false) {
                            Boolean insert = db.insereDadoCadastro(nome, usuario, email, senha, confsenha);
                            if (insert == true) {
                                Toast.makeText(ActivityTelaCadastro.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), ActivityTelaLogin.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Houve um erro ao cadastrar", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityTelaCadastro.this, "Usuário já cadastrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActivityTelaCadastro.this, "Senhas não são iguais", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}