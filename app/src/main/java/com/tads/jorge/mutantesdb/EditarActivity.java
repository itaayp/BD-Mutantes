package com.tads.jorge.mutantesdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditarActivity extends AppCompatActivity {

    private TextView fieldName;
    private TextView fieldSkills;
    private ServiceCaller serviceCaller;
    private int mutanteId;
    private String mutanteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        serviceCaller = new ServiceCaller();

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
        Mutante m = new Mutante();
        m.setMutanteName(fieldName.getText().toString());
        m.setSkills(StringUtil.retornarSkills(fieldSkills.getText().toString()));
        ServiceResponse response = serviceCaller.editMutante(m);
        if(response != null) {
            if (response.isSucess()) {
                Toast.makeText(getApplicationContext(), "Salvo com sucesso.", Toast.LENGTH_SHORT).show();
                voltarListar(view);
            } else {
                Toast.makeText(getApplicationContext(), response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Erro grave ao editar, tente novamente mais tarde", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarMutante(Intent tela){
        mutanteId = Integer.parseInt(tela.getStringExtra("id"));
        if(mutanteId > 0){
            ServiceResponse mutante = serviceCaller.getMutante(mutanteId);
            //preencherCamposMutante(mutante);
        }else {
            Toast.makeText(getApplicationContext(),"Erro ao buscar dados de Mutante.", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    public void preencherCamposMutante(Mutante mutante){
        mutanteName = mutante.getMutanteName();
        fieldName.setText(mutanteName);
        String skills = "";
        for (Skill s : mutante.getSkills()) {
            skills += s.getSkillName() + ";";
        }
        fieldSkills.setText(skills);
    }

    public void excluir(View view){
//        if (serviceCaller.deleteMutante(mutanteId)) {
//            Toast.makeText(getApplicationContext(),"Excluído com sucesso.", Toast.LENGTH_SHORT).show();
//            voltarListar(view);
//        }
//        else {
//            Toast.makeText(getApplicationContext(),"Erro ao excluir, tente novamente.", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
