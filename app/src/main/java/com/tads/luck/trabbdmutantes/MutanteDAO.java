package com.tads.luck.trabbdmutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MutanteDAO {

    private MutanteBDWrapper dbHelper;
    private String[] MUTANTE_TABLE_COLUMNS = { MutanteBDWrapper.MUTANTE_ID, MutanteBDWrapper.MUTANTE_NAME };
    private String[] SKILL_TABLE_COLUMNS = { MutanteBDWrapper.MUTANTE_SKILL_ID, MutanteBDWrapper.SKILL_NAME};
    private SQLiteDatabase database;

    public MutanteDAO(Context context){
        dbHelper = new MutanteBDWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Long addMutante(Mutante mutante){

        long retorno = 0;

        ContentValues mutanteInsert = new ContentValues();

        mutanteInsert.put(MutanteBDWrapper.MUTANTE_NAME, mutante.getName());

        long mutanteId = database.insert(MutanteBDWrapper.MUTANTES, null, mutanteInsert);

        if (mutanteId > 0){
            mutante.setId(Integer.parseInt(String.valueOf(mutanteId)));
            retorno = addSkills(mutante);
        }

        return retorno;
    }

    public Long addSkills(Mutante mutante){
        long retorno = 0;

        for (String skill : mutante.getSkill()) {
            ContentValues skillsInsert = new ContentValues();
            skillsInsert.put(MutanteBDWrapper.MUTANTE_SKILL_ID, mutante.getId());
            skillsInsert.put(MutanteBDWrapper.SKILL_NAME, skill);
            retorno = database.insert(MutanteBDWrapper.SKILLS, null, skillsInsert);
        }

        return retorno;
    }

    public void deleteMutante(Mutante comment){
        long id = comment.getId();
        database.delete(MutanteBDWrapper.MUTANTES, MutanteBDWrapper.MUTANTE_ID + " = " + id, null);
        database.delete(MutanteBDWrapper.SKILLS, MutanteBDWrapper.MUTANTE_SKILL_ID + " = " + id, null);
    }

    public List getAllMutantes(){
        List mutantes = new ArrayList();
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS,
                null, null, null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    private Mutante parseMutante(Cursor cursor) {
        Mutante mutante = new Mutante();
        mutante.setId(cursor.getInt(0));
        mutante.setName(cursor.getString(1));
        return mutante;
    }
}
