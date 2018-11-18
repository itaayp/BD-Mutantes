package com.tads.luck.trabbdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void telaCadastrar(View view){
        Intent tela = new Intent(getApplicationContext(), CadastrarActivity.class);
        startActivity(tela);
    }

    public void telaListar(View view){
        Intent tela = new Intent(getApplicationContext(), ListarActivity.class);
        startActivity(tela);
    }

    public void telaPesquisar(View view){
        Intent tela = new Intent(getApplicationContext(), PesquisarActivity.class);
        startActivity(tela);
    }
}
