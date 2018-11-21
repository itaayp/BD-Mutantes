package com.example.android.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    private Button btnNovoMutante;
    private Button btnListarTodos;
    private Button btnPesquisarMutantes;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnNovoMutante = (Button)findViewById(R.id.btnNovoMutante);
        btnListarTodos = (Button)findViewById(R.id.btnListarTodos);
        btnPesquisarMutantes = (Button)findViewById(R.id.btnPesquisarMutantes);
        btnSair = (Button)findViewById(R.id.btnSair);

        btnSair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
    }

    public void openCadastroActivity(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void openListarActivity(View view) {
        Intent intent = new Intent(this, ListarTodosActivity.class);
        startActivity(intent);
    }

    public void openBuscarActivity(View view){
        Intent tela = new Intent(getApplicationContext(), BuscarActivity.class);
        startActivity(tela);
    }
}
