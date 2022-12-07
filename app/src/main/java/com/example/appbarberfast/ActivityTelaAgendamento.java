package com.example.appbarberfast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.database.sqlite.SQLiteDatabase; //Banco de Dados
import android.database.Cursor; //Navegar entre os registros
import android.widget.*;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityTelaAgendamento extends AppCompatActivity {

    private static final int TEMPO_TELA_ABERTA = 1500;
    EditText et_nome, et_TipoCorte, et_MelhorDia, et_NomeBarbeiro;
    Button btn_gravar, btn_consultar, btn_fechar;

    SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_agendamento);

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_TipoCorte = (EditText) findViewById(R.id.et_TipoCorte);
        et_MelhorDia = (EditText) findViewById(R.id.et_MelhorDia);
        et_NomeBarbeiro = (EditText) findViewById(R.id.et_NomeBarbeiro);
        btn_gravar = (Button) findViewById(R.id.btn_gravar);
        btn_consultar = (Button) findViewById(R.id.btn_consultar);
        btn_fechar = (Button) findViewById(R.id.btn_fechar);

        abrirBanco();
        abrirOuCriarTabela();
        fecharDB();
    }

    public void abrirBanco() {
        try {
            db = openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            msg("Erro ao abrir ou criar o banco de dados");
        }
    }

    public void abrirOuCriarTabela() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos(id INTEGER PRIMARY KEY, nome TEXT, tipoCorte TEXT, MelhorDia TEXT, NomeBarbeiro TEXT);");
        } catch (Exception ex) {
            msg("Erro ao criar Tabela");
        }
    }

    public void fecharDB() {
        db.close();
    }

    public void inserirRegistro(View v) {
        String st_nome, st_TipoCorte, st_MelhorDia, st_NomeBarbeiro;
        st_nome = et_nome.getText().toString();
        st_TipoCorte = et_TipoCorte.getText().toString();
        st_MelhorDia = et_MelhorDia.getText().toString();
        st_NomeBarbeiro = et_NomeBarbeiro.getText().toString();

        if (TextUtils.isEmpty(st_nome) || TextUtils.isEmpty(st_TipoCorte) || TextUtils.isEmpty(st_MelhorDia) || TextUtils.isEmpty(st_NomeBarbeiro)) {
            msg("ERROR: Campos não podem estar vazios");
            return;
        }

        abrirBanco();
        try {
            db.execSQL("INSERT INTO contatos (nome, tipoCorte, MelhorDia, NomeBarbeiro) VALUES ('" + st_nome + "', '" + st_TipoCorte + "', '" + st_MelhorDia + "', '" + st_NomeBarbeiro + "')");
        } catch (Exception ex) {
            msg("Erro no agendamento. Tente novamente.");
        } finally {
            Toast.makeText(getApplicationContext(), "Redirecionando para pagamento.", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), ActivityTelaPagamento.class);
                    startActivity(intent);
                }
            }, TEMPO_TELA_ABERTA);


        }
        fecharDB();

        et_nome.setText(null);
        et_TipoCorte.setText(null);
        et_MelhorDia.setText(null);
        et_NomeBarbeiro.setText(null);
    }

    public void abrir_tela_consulta(View v) {
        Intent intent = new Intent(this, ActivityTelaConsultaAgendamento.class);
        startActivity(intent);
    }

    public void voltarLogin() {
        Intent intent = new Intent(getApplicationContext(), ActivityTelaLogin.class);
        startActivity(intent);
    }


    public void msg(String txt) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage(txt);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}