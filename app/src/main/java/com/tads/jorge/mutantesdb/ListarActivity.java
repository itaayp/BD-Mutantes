package com.tads.jorge.mutantesdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

public class ListarActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener{

    private ServiceCaller serviceCaller;
    private List listaMutantes;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        serviceCaller = new ServiceCaller();

        serviceCaller.getAllMutantes(this);

        list = (ListView) findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaMutantes);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent tela = new Intent(getApplicationContext(), EditarActivity.class);
                Mutante mutante = (Mutante) listaMutantes.get(position);
                tela.putExtra("id",String.valueOf(mutante.getId()));
                startActivity(tela);
            }
        });
    }

    public void voltarDashboard(View view){
        Intent tela = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(tela);
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        listaMutantes = JsonTranslator.getListMuttante(response);
    }
}