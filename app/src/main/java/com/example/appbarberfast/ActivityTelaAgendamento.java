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
import android.widget.*;

import java.text.BreakIterator;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityTelaAgendamento extends AppCompatActivity {

    private static final String[] tipoCortes = new String[]{"Cabelo", "Barba", "Cabelo e Barba", "Massagem facial"};
    private static final String[] dias = new String[]{"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
    private static final String[] barbeiros = new String[]{"João Pedro", "Luiz Gustavo", "Vitor Brito", "Abner Silva", "Gean Araujo"};

    private static final int TEMPO_TELA_ABERTA = 1500;
    SQLiteDatabase db = null;

    EditText et_nome;
    Button btn_gravar, btn_consultar, btn_fechar;
    Spinner tipoCorte, melhorDia, nomeBarbeiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_agendamento);

        btn_gravar = (Button) findViewById(R.id.btn_gravar);
        btn_consultar = (Button) findViewById(R.id.btn_consultar);
        btn_fechar = (Button) findViewById(R.id.btn_fechar);

        et_nome = (EditText) findViewById(R.id.et_nome);

        tipoCorte = (Spinner) findViewById(R.id.sp_tipoCorte);
        melhorDia = (Spinner) findViewById(R.id.sp_melhorDia);
        nomeBarbeiro = (Spinner) findViewById(R.id.sp_nomeBarbeiro);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoCortes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoCorte.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ActivityTelaAgendamento.this, android.R.layout.simple_spinner_item, dias);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        melhorDia.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(ActivityTelaAgendamento.this, android.R.layout.simple_spinner_item, barbeiros);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nomeBarbeiro.setAdapter(adapter3);

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
        st_TipoCorte = tipoCortes.toString();
        st_MelhorDia = dias.toString();
        st_NomeBarbeiro = barbeiros.toString();

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

        //et_nome.setText(null);
        //tipoCortes.toString().replace("", "");
        //dias.toString().replace("", "");
        //barbeiros.toString().replace("", "");
    }

    public void abrir_tela_consulta(View v) {
        Intent intent = new Intent(this, ActivityTelaConsultaAgendamento.class);
        startActivity(intent);
    }

    public void msg(String txt) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage(txt);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}