package com.tads.jorge.mutantesdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    private TextView fieldName;
    private TextView fieldSkills;
    private MutanteDAO mutanteDBoperation;
    private int mutanteId;
    private String mutanteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        mutanteDBoperation = new MutanteDAO(this);
        mutanteDBoperation.open();

        fieldName = findViewById(R.id.mutanteName);
        fieldSkills = findViewById(R.id.mutanteSkills);

        Intent tela = getIntent();
        if (tela != null)
            buscarMutante(tela);
    }

    public void voltarListar(View view){
        Intent tela = new Intent(getApplicationContext(), ListarActivity.class);
        startActivity(tela);
        this.finish();
    }

    public void editar(View view){
        String nome = fieldName.getText().toString();
        boolean mesmoNome = false;
        if (mutanteName.equals(nome))
            mesmoNome = true;
        if (mutanteDBoperation.validaNomeMutante(nome) || mesmoNome){
            String[] mutanteSkills = retornarSkills(fieldSkills.getText().toString());
            Mutante mutante = new Mutante(mutanteId,nome,mutanteSkills);
            if (mutanteDBoperation.editMutante(mutante) > 0) {
                Toast.makeText(getApplicationContext(),"Salvo com sucesso.", Toast.LENGTH_SHORT).show();
                voltarListar(view);
            }else {
                Toast.makeText(getApplicationContext(),"Erro ao salvar, tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(getApplicationContext(),"Nome já existente, inserir um nome diferente.", Toast.LENGTH_SHORT).show();

    }

    private String[] retornarSkills(String skills){
        return skills.split(";");
    }

    public void buscarMutante(Intent tela){
        mutanteId = Integer.parseInt(tela.getStringExtra("id"));
        if(mutanteId > 0){
            Mutante mutante = mutanteDBoperation.getMutante(mutanteId);
            preencherCamposMutante(mutante);
        }else {
            Toast.makeText(getApplicationContext(),"Erro ao buscar dados de Mutante.", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    public void preencherCamposMutante(Mutante mutante){
        mutanteName = mutante.getName();
        fieldName.setText(mutanteName);
        String skills = "";
        for (String skill : mutante.getSkill()) {
            skills += skill + ";";
        }
        fieldSkills.setText(skills);
    }

    public void excluir(View view){
        if (mutanteDBoperation.deleteMutante(mutanteId) > 0) {
            Toast.makeText(getApplicationContext(),"Excluído com sucesso.", Toast.LENGTH_SHORT).show();
            voltarListar(view);
        }
        else {
            Toast.makeText(getApplicationContext(),"Erro ao excluir, tente novamente.", Toast.LENGTH_SHORT).show();
        }
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
