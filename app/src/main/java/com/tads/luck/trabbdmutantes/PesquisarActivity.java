package com.tads.luck.trabbdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class PesquisarActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    private ServiceCaller serviceCaller;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        List values = new ArrayList();

        serviceCaller = new ServiceCaller();

        list = (ListView) findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
    }

    public void pesquisarNome(View view){
        EditText text = (EditText) findViewById(R.id.input);
        if (!text.getText().toString().trim().isEmpty()){
            serviceCaller.pesquisarNome(text.getText().toString(),this);
        }
    }

    public void pesquisarHabilidade(View view){
        EditText text = (EditText) findViewById(R.id.input);
        if (!text.getText().toString().trim().isEmpty()){
            serviceCaller.buscarPorHabilidade(text.getText().toString(),this);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {
        ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
        adapter.clear();
        List mutantes = JsonTranslator.getListMuttante(response);
        if (!mutantes.isEmpty()) {
            for (Object mutante : mutantes) {
                if (mutante != null)
                    adapter.add(mutante);
            }
        }
    }

    public void voltarPesquisar(View view){
        Intent tela = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(tela);
        this.finish();
    }

}
