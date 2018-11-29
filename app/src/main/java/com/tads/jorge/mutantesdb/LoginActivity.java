package com.tads.jorge.mutantesdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private ServiceCaller serviceCaller;
    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        serviceCaller = new ServiceCaller();

        loginEditText = findViewById(R.id.user);
        passwordEditText = findViewById(R.id.password);

    }

    public void onLogin(View view){
        User u = new User();
        u.setUsername(loginEditText.getText().toString());
        u.setPassword(passwordEditText.getText().toString());

        ServiceResponse response = serviceCaller.getUser(u);
        if(response != null){
            if(response.isSucess()){
                startActivity(new Intent(getBaseContext(), DashboardActivity.class));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Erro ao logar, tente novamente.", Toast.LENGTH_SHORT).show();
        }

    }

    public void novoCadastro(View view){
        startActivity(new Intent(getBaseContext(), CadastrarUsuarioActivity.class));
    }
}
