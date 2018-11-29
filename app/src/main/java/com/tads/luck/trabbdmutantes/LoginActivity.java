package com.tads.luck.trabbdmutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class LoginActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    private ServiceCaller serviceCaller;
    private EditText loginEditText;
    private EditText passwordEditText;
    private ServiceResponse response;
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
        serviceCaller = new ServiceCaller(view.getContext());
        response = serviceCaller.getUser(u,this);
        if(response != null){

        }
        else {
            Toast.makeText(getApplicationContext(),"Erro ao logar, tente novamente.", Toast.LENGTH_SHORT).show();
        }

    }

    public void novoCadastro(View view){
        startActivity(new Intent(getBaseContext(), CadastrarUsuarioActivity.class));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {
        startActivity(new Intent(getBaseContext(), DashboardActivity.class));
        finish();
    }

}
