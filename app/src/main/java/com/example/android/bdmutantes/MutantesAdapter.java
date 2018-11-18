package com.example.android.bdmutantes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public abstract class MutantesAdapter extends BaseAdapter {
    private List<Mutante> mutantes;
    private LayoutInflater inflater;

    public MutantesAdapter(Context context, List<Mutante> mutantes) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mutantes = mutantes;
    }

    public void novosDados(List<Mutante> mutantes) {
        this.mutantes = mutantes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mutantes.size();
    }

    @Override
    public Object getItem(int position) {
        return mutantes.get(position);
    }

    @Override
    //retorna um id especifico para cada elemento da lista
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        View v = inflater.inflate(R.layout.mutante_item, null);
        ((TextView)(v.findViewById(R.id.txtNome))).setText(mutantes.get(position).nome);
        ((ImageButton)
                (v.findViewById(R.id.btnEditar))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleta(mutantes.get(position));
            }
        });
        return v;
    }

    public abstract void edita(Mutante artigo);
    public abstract void deleta(Mutante artigo);
}
