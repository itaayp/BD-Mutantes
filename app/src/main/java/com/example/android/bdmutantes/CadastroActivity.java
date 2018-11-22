package com.example.android.bdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtPoderes;
    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bd = new BancoDeDados(this);
        bd.abrir();

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtPoderes = (EditText)findViewById(R.id.edtPoderes);
    }

    public void cadastrar(View view){
        String mutanteName = edtNome.getText().toString();

        if (bd.validaNomeMutante(mutanteName)){
            String[] mutanteSkills = retornarSkills(edtPoderes.getText().toString());
            Mutante mutante = new Mutante(mutanteName,mutanteSkills);
            if (bd.insereMutante(mutante) >= 0) {
                Toast.makeText(getApplicationContext(),"Salvo com sucesso", Toast.LENGTH_SHORT).show();

                Intent tela = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(tela);
                this.finish();
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
        bd.abrir();
        super.onResume();
    }

    @Override
    protected void onPause() {
        bd.fechar();
        super.onPause();
    }
}
