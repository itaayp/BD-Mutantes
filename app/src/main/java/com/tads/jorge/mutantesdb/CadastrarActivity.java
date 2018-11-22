package com.tads.jorge.mutantesdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {

    private TextView fieldName;
    private TextView fieldSkills;
    private MutanteDao mutanteDBoperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mutanteDBoperation = new MutanteDao(this);
        mutanteDBoperation.open();

        fieldName = findViewById(R.id.mutanteName);
        fieldSkills = findViewById(R.id.mutanteSkills);

    }

    public void voltarDashboard(View view){
        Intent tela = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(tela);
        this.finish();
    }

    public void cadastrar(View view){
        String mutanteName = fieldName.getText().toString();

        if (mutanteDBoperation.validaNomeMutante(mutanteName)){
            String[] mutanteSkills = retornarSkills(fieldSkills.getText().toString());
            Mutante mutante = new Mutante(mutanteName,mutanteSkills);
            if (mutanteDBoperation.addMutante(mutante) >= 0) {
                Toast.makeText(getApplicationContext(),"Salvo com sucesso", Toast.LENGTH_SHORT).show();
                voltarDashboard(view);
            }
            else {
                Toast.makeText(getApplicationContext(),"Erro ao salvar, tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(getApplicationContext(),"Nome já existente, inserir um nome diferente.", Toast.LENGTH_SHORT).show();

    }

    private String[] retornarSkills(String skills){
        return skills.split(";");
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
