package com.example.android.bdmutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {

    private String[] MUTANTE_TABLE_COLUMNS = { MeuOpenHelper.KEY_NOME};
    private String[] SKILL_TABLE_COLUMNS = { MeuOpenHelper.MUTANTE_SKILL_ID, MeuOpenHelper.SKILL_NAME};

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDeDados(Context ctx) {
        this.context = ctx;
        openHelper =  new MeuOpenHelper(context);
    }

    public BancoDeDados abrir() throws SQLException {
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar() {
        openHelper.close();
    }

    public boolean apagaMutante(String nome) {
        return db.delete(openHelper.NOME_TABELA, openHelper.KEY_NOME + "=" + nome, null) > 0;
    }


    public Cursor retornaTodosMutantes() {
        return db.query(openHelper.NOME_TABELA, new String[] { openHelper.KEY_NOME },
                null, null, null, null, null);
    }

    private Mutante parseMutante(Cursor cursor) {
        Mutante mutante = new Mutante();
        mutante.setNome(cursor.getString(0));
        return mutante;
    }

    public String[] getSkills(String mutanteNome){
        String skills = "";
        String whereClause  = openHelper.MUTANTE_SKILL_ID + " = ?";
        String[] whereArgs = {String.valueOf(mutanteNome)};
        Cursor cursor = db.query(true,openHelper.SKILLS, SKILL_TABLE_COLUMNS,
                whereClause, whereArgs, null,null,null,null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                skills += cursor.getString(1) + ";";
                cursor.moveToNext();
            }
        }
        cursor.close();
        return skills.split(";");
    }

    public Mutante getMutante(String mutanteNome){
        String whereClause  = openHelper.KEY_NOME + " = ?";
        String[] whereArgs = {String.valueOf(mutanteNome)};
        Cursor cursor = db.query(openHelper.NOME_TABELA, MUTANTE_TABLE_COLUMNS,
                whereClause, whereArgs, null,null,null);
        Mutante mutante = new Mutante();
        if(cursor.moveToFirst()){
            mutante = parseMutante(cursor);
        }
        cursor.close();
        mutante.setSkill(getSkills(mutanteNome));
        return mutante;
    }

    public List buscarPorNome(String nome){
        String whereLike = openHelper.KEY_NOME + " LIKE ?";
        String[] whereArgs = {"%"+nome+"%"};
        List mutantes = new ArrayList();
        Cursor cursor = db.query(openHelper.NOME_TABELA, MUTANTE_TABLE_COLUMNS,
                whereLike, whereArgs, null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public List buscarPorHabilidade(String habilidade){
        List mutantes = new ArrayList();
        String whereClause  = openHelper.SKILL_NAME + " LIKE ?";
        String[] whereArgs = {"%"+habilidade+"%"};
        Cursor cursor = db.query(true,openHelper.SKILLS, SKILL_TABLE_COLUMNS,
                whereClause, whereArgs, null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mutanteNome = cursor.getString(0);
            Mutante mutante = getMutante(mutanteNome);
            if (mutantes.isEmpty())
                mutantes.add(mutante);
            else
                for (Object item : mutantes) {
                    if (((Mutante) item).getNome() != mutanteNome)
                        mutantes.add(mutante);
                }
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public boolean validaNomeMutante(String nome){
        String whereClause  = openHelper.KEY_NOME + " = ?";
        String[] whereArgs = {nome.toLowerCase()};
        Cursor cursor = db.query(openHelper.NOME_TABELA, MUTANTE_TABLE_COLUMNS,
                whereClause, whereArgs, null,null,null);
        if(cursor.moveToFirst()){
            cursor.close();
            return false;
        }
        else{
            cursor.close();
            return true;
        }
    }

    public Long insereMutante(Mutante mutante){

        long retorno = 0;

        ContentValues mutanteInsert = new ContentValues();

        mutanteInsert.put(openHelper.KEY_NOME, mutante.getNome().toLowerCase());

        long rowId = db.insert(openHelper.NOME_TABELA, null, mutanteInsert);

        if (rowId > 0){
            retorno = insereSkills(mutante);
        }

        return retorno;
    }

    public Long insereSkills(Mutante mutante){
        long retorno = 0;

        for (String skill : mutante.getSkill()) {
            ContentValues skillsInsert = new ContentValues();
            skillsInsert.put(openHelper.MUTANTE_SKILL_ID, mutante.getNome());
            skillsInsert.put(openHelper.SKILL_NAME, skill);
            retorno = db.insert(openHelper.SKILLS, null, skillsInsert);
        }

        return retorno;
    }
}
