package com.example.android.bdmutantes;

import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

public class HabilidadesAdapter extends ArrayAdapter {

    private String[] skillNames;
    private LayoutInflater inflater;

    public HabilidadesAdapter(Context context, String[] skillNames){
        super(context, skillNames);

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.skillNames = skillNames;
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        View v = inflater.inflate(R.layout.mutante_item, null);
        ((TextView)(v.findViewById(R.id.txtNome))).setText(skillNames[position]);

        return v;
    }

}
