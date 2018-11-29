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
    private ServiceCaller serviceCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        serviceCaller = new ServiceCaller();

        fieldName = findViewById(R.id.mutanteName);
        fieldSkills = findViewById(R.id.mutanteSkills);

    }

    public void voltarDashboard(View view){
        Intent tela = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(tela);
        this.finish();
    }

    public void cadastrar(View view){
        Mutante m = new Mutante();
        m.setMutanteName(fieldName.getText().toString());
        m.setSkills(StringUtil.retornarSkills(fieldSkills.getText().toString()));
        ServiceResponse response = serviceCaller.addMutante(m);
        if(response != null){
            if ( response.isSucess()) {
                Toast.makeText(getApplicationContext(),"Salvo com sucesso", Toast.LENGTH_SHORT).show();
                voltarDashboard(view);
            }
            else {
                Toast.makeText(getApplicationContext(),response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Erro ao salvar, tente novamente.", Toast.LENGTH_SHORT).show();
        }
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
