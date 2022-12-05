package com.example.appbarberfast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTelaConsultaAgendamento extends AppCompatActivity {

    EditText et_nome_consulta, et_tipo_corte_consulta, et_melhor_dia, et_nome_barbeiro;
    Button btn_anterior,btn_proximo, btn_voltar;

    SQLiteDatabase db = null;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta_agendamento);

        et_nome_consulta = (EditText) findViewById(R.id.et_nome_consulta);
        et_tipo_corte_consulta = (EditText) findViewById(R.id.et_tipo_corte_consulta);
        et_melhor_dia = (EditText) findViewById(R.id.et_melhor_dia);
        et_nome_barbeiro = (EditText) findViewById(R.id.et_nome_barbeiro);
        btn_anterior = (Button) findViewById(R.id.btn_anterior);
        btn_proximo = (Button) findViewById(R.id.btn_proximo);
        btn_voltar = (Button) findViewById(R.id.btn_voltar_consulta);
    }

    public void voltar_tela_agendamento(View v){
        Intent intent= new Intent(this,ActivityTelaAgendamento.class);
        startActivity(intent);
    }

    public void abrirBanco(){
        try{
            db=openOrCreateDatabase("bancoAgenda",MODE_PRIVATE,null);
        }catch (Exception ex){
            msg("Erro ao abrir ou criar o banco de dados");
        }
    }

    public void fecharDB(){
        db.close();
    }

    public boolean buscarDados(){
        abrirBanco();
        cursor=db.query("contatos",
                new String[]{"nome", "TipoCorte", "MelhorDia", "NomeBarbeiro"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            return true;
        }else{
            msg("Nenhum registro encontrado");
            return false;
        }
    }

    public void mostrarDados(){
        et_nome_consulta.setText(cursor.getString(1));
        et_tipo_corte_consulta.setText(cursor.getString(2));
    }

    public void msg(String txt){
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setMessage(txt);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}