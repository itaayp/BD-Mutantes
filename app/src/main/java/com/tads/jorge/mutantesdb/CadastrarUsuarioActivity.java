package com.tads.jorge.mutantesdb;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private ServiceCaller serviceCaller;
    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        serviceCaller = new ServiceCaller();

        loginEditText = findViewById(R.id.user);
        passwordEditText = findViewById(R.id.password);
    }

    public void cadastrar(View view){
        User u = new User();
        u.setUsername(loginEditText.getText().toString());
        u.setPassword(passwordEditText.getText().toString());

        ServiceResponse response = serviceCaller.addUser(u);
        if(response != null){
            if(response.isSucess()){
                Toast.makeText(getApplicationContext(),"Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                voltarLogin(view);
            }
            else {
                Toast.makeText(getApplicationContext(),response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Erro ao salvar, tente novamente.", Toast.LENGTH_SHORT).show();
        }


    }


    public void voltarLogin(View view){
        Intent tela = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(tela);
        this.finish();
    }
}
