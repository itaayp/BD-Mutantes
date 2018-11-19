package com.tads.luck.trabbdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PesquisarActivity extends AppCompatActivity {

    private MutanteDAO mutanteDBoperation;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        mutanteDBoperation = new MutanteDAO(this);
        mutanteDBoperation.open();

        List values = new ArrayList();

        list = (ListView) findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
    }

    public void pesquisarNome(View view){
        ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
        adapter.clear();
        EditText text = (EditText) findViewById(R.id.input);
        List mutantes = mutanteDBoperation.buscarPorNome(text.getText().toString());
        if (!mutantes.isEmpty())
            for (Object mutante : mutantes) {
                adapter.add(mutante);
            }
        else{
            Toast.makeText(getApplicationContext(),"Nenhum resultado encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void pesquisarHabilidade(View view){
        ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
        adapter.clear();
        EditText text = (EditText) findViewById(R.id.input);
        List mutantes = mutanteDBoperation.buscarPorHabilidade(text.getText().toString());
        if (!mutantes.isEmpty())
            for (Object mutante : mutantes) {
                adapter.add(mutante);
            }
        else{
            Toast.makeText(getApplicationContext(),"Nenhum resultado encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltarPesquisar(View view){
        Intent tela = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(tela);
        this.finish();
    }

    @Override
    protected void onResume() {
        mutanteDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mutanteDBoperation.close();
        super.onPause();
    }
}