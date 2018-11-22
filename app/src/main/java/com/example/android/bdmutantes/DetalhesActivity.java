package com.example.android.bdmutantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DetalhesActivity extends AppCompatActivity {

    ListView skillList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        skillList = (ListView)findViewById(R.id.skillList);
    }
}
