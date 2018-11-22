package com.tads.jorge.mutantesdb;

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

        List values = new ArrayList();

        list = findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
    }

    public void pesquisarNome(View view){
        EditText text = findViewById(R.id.input);
        if (!text.getText().toString().trim().isEmpty()){
            mutanteDBoperation = new MutanteDAO(getApplicationContext());
            mutanteDBoperation.open();
            ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
            adapter.clear();
            List mutantes = mutanteDBoperation.buscarPorNome(text.getText().toString());
            if (!mutantes.isEmpty())
                for (Object mutante : mutantes) {
                    if (mutante != null)
                        adapter.add(mutante);
                }
            else{
                Toast.makeText(getApplicationContext(),"Nenhum resultado encontrado.", Toast.LENGTH_SHORT).show();
            }
            mutanteDBoperation.close();
        }
    }

    public void pesquisarHabilidade(View view){
        EditText text = findViewById(R.id.input);
        if (!text.getText().toString().trim().isEmpty()){
            mutanteDBoperation = new MutanteDAO(getApplicationContext());
            mutanteDBoperation.open();
            ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
            adapter.clear();
            List mutantes = mutanteDBoperation.buscarPorHabilidade(text.getText().toString());
            if (!mutantes.isEmpty())
                for (Object mutante : mutantes) {
                    if (mutante != null)
                        adapter.add(mutante);
                }
            else{
                Toast.makeText(getApplicationContext(),"Nenhum resultado encontrado.", Toast.LENGTH_SHORT).show();
            }
            mutanteDBoperation.close();
        }
    }

    public void voltarPesquisar(View view){
        Intent tela = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(tela);
        this.finish();
    }

}
