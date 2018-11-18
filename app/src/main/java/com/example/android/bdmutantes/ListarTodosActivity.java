package com.example.android.bdmutantes;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;



import java.util.ArrayList;
import java.util.List;

public class ListarTodosActivity extends ListActivity {
    private BancoDeDados db;
    private List<Mutante> mutantes = new ArrayList<Mutante>();
    private MutantesAdapter mutantesAdapter;
    public static final int REQUEST_EDICAO = 0;
    public static final int REQUEST_SALVOU = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_todos);
        db = new BancoDeDados(this);
        lerDados();
    }

    public void lerDados() {
        db.abrir();
        mutantes.clear();
        Cursor cursor = db.retornaTodosMutantes();
        if (cursor.moveToFirst()) {
            do {
                Mutante m = new Mutante();
                m.nome = cursor.getString(cursor.getColumnIndex(BancoDeDados.KEY_NOME));
                mutantes.add(m);
            } while (cursor.moveToNext());
        }

        if (mutantes.size() > 0) {
            if (mutantesAdapter == null) {
                mutantesAdapter = new MutantesAdapter(this, mutantes) {
                    @Override
                    public void edita(Mutante mutante) {
                        Intent intent = new Intent(getApplicationContext(), NovoEdicaoActivity.class);
                        intent.putExtra("mutante", mutante);
                        //descobrir se o user pressionou salvar ou cancelou a ação
                        startActivityForResult(intent, REQUEST_EDICAO);
                    }

                    @Override
                    public void deleta(Mutante artigo) {
                        db.abrir();
                        db.apagaMutante(artigo.nome);
                        db.fechar();
                        lerDados();
                    }
                };
                setListAdapter(mutantesAdapter);
            } else {
                mutantesAdapter.novosDados(mutantes);
            }
        }
        db.fechar();
    }
}
