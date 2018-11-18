package com.example.android.bdmutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados {
    static String KEY_NOME = "nome";

    String NOME_BANCO = "db_Mutantes";
    String NOME_TABELA = "mutantes";
    int VERSAO_BANCO = 1;
    String SQL_CREATE_TABLE = "create table mutantes " +
            "(" + KEY_NOME + " text primary key);";

    final Context context;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDeDados(Context ctx) {
        this.context = ctx;
        openHelper =  new MeuOpenHelper(context);
    }

    public class MeuOpenHelper extends SQLiteOpenHelper {
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

    public BancoDeDados abrir() throws SQLException {
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar() {
        openHelper.close();
    }

    public long insereMutante(String nome) {
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, nome);
        return db.insert(NOME_TABELA, null, campos);
    }

    public boolean apagaMutante(String nome) {
        return db.delete(NOME_TABELA, KEY_NOME + "=" + nome, null) > 0;
    }


    public Cursor retornaTodosMutantes() {
        insereMutante("Itay");

        return db.query(NOME_TABELA, new String[] { KEY_NOME },
                null, null, null, null, null);
    }
}
