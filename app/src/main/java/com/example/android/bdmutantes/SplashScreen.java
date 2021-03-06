package com.example.android.bdmutantes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity implements Runnable{
    private final int DELAY = 3000; //3 segundos

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toast.makeText(this, "Aguarde o carregamento do aplicativo ... ", Toast.LENGTH_SHORT).show();
        Handler h = new Handler();
        h.postDelayed(this, DELAY);

    }

    @Override
    public void run() {
        //Abre menu principal
        startActivity(new Intent(this, DashboardActivity.class));
        finish();

    }
}
