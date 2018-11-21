package com.example.android.bdmutantes;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeuOpenHelper extends SQLiteOpenHelper {
    //Dados Banco
    private static final int VERSAO_BANCO = 1;

    //Dados Tabela Mutantes
    public static final String NOME_TABELA = "mutantes";
    public static final String KEY_NOME = "nome";
    private static final String NOME_BANCO = "db_Mutantes";
    private final String SQL_CREATE_TABLE = "create table " + NOME_TABELA +
            "(" + KEY_NOME + " text primary key);";

    //Dados tabela Skills
    public static final String SKILLS = "Skills";
    public static final String MUTANTE_SKILL_ID = "_id";
    public static final String SKILL_NAME = "_name";

    private static final String DATABASE_CREATE_SKILLS = "create table " +
            SKILLS + "(" + MUTANTE_SKILL_ID + " integer, " +
            SKILL_NAME + " text not null);";


    MeuOpenHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    //Chamado quando o bd for criado pela primeira vez
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    //Chamado quando temos uma nova versao do bd ou do app
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mutantes");
        onCreate(db);
    }
}

