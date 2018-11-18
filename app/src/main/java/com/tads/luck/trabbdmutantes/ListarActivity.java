package com.tads.luck.trabbdmutantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListarActivity extends AppCompatActivity {

    private MutanteDAO mutanteDBoperation;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        mutanteDBoperation = new MutanteDAO(this);
        mutanteDBoperation.open();

        List values = mutanteDBoperation.getAllMutantes();

        list = (ListView) findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
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