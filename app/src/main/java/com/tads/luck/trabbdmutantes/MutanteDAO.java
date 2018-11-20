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

    public Long editMutante(Mutante mutante){
        long retorno = 0;

        ContentValues mutanteUpdate = new ContentValues();
        String whereClause  = MutanteBDWrapper.MUTANTE_ID + " = ?";
        String[] whereArgs = {String.valueOf(mutante.getId())};
        mutanteUpdate.put(MutanteBDWrapper.MUTANTE_NAME, mutante.getName());

        long mutanteId = database.update(MutanteBDWrapper.MUTANTES, mutanteUpdate, whereClause, whereArgs);

        if (mutanteId > 0){
            mutante.setId(Integer.parseInt(String.valueOf(mutante.getId())));
            retorno = addSkills(mutante);
        }

        return retorno;
    }

    public Long deleteMutante(int mutanteId){
        long retorno = database.delete(MutanteBDWrapper.MUTANTES, MutanteBDWrapper.MUTANTE_ID + " = " + mutanteId, null);
        if(retorno >= 0)
            retorno = database.delete(MutanteBDWrapper.SKILLS, MutanteBDWrapper.MUTANTE_SKILL_ID + " = " + mutanteId, null);
        return retorno;
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

    public Mutante getMutante(int mutanteId){
        String whereClause  = MutanteBDWrapper.MUTANTE_ID + " = ?";
        String[] whereArgs = {String.valueOf(mutanteId)};
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS,
                whereClause, whereArgs, null,null,null);
        Mutante mutante = new Mutante();
        if(cursor.moveToFirst()){
            mutante = parseMutante(cursor);
        }
        cursor.close();
        mutante.setSkill(getSkills(mutanteId));
        return mutante;
    }

    public boolean validaNomeMutante(String nome){
        String whereClause  = MutanteBDWrapper.MUTANTE_NAME + " = ?";
        String[] whereArgs = {nome};
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS,
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

    public String[] getSkills(int mutanteId){
        String skills = "";
        String whereClause  = MutanteBDWrapper.MUTANTE_SKILL_ID + " = ?";
        String[] whereArgs = {String.valueOf(mutanteId)};
        Cursor cursor = database.query(true,MutanteBDWrapper.SKILLS, SKILL_TABLE_COLUMNS,
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

    public List buscarPorNome(String nome){
        String whereLike = MutanteBDWrapper.MUTANTE_NAME + " LIKE ?";
        String[] whereArgs = {"%"+nome+"%"};
        List mutantes = new ArrayList();
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTES, MUTANTE_TABLE_COLUMNS,
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
        String whereClause  = MutanteBDWrapper.SKILL_NAME + " LIKE ?";
        String[] whereArgs = {"%"+habilidade+"%"};
        Cursor cursor = database.query(true,MutanteBDWrapper.SKILLS, SKILL_TABLE_COLUMNS,
                whereClause, whereArgs, null,null,null,null);
        cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int mutanteId = cursor.getInt(0);
                Mutante mutante = getMutante(mutanteId);
                if (mutantes.isEmpty())
                    mutantes.add(mutante);
                else
                    for (Object item : mutantes) {
                        if (((Mutante) item).getId() != mutanteId)
                            mutantes.add(mutante);
                    }
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
