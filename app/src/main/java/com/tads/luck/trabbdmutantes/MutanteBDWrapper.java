package com.tads.luck.trabbdmutantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MutanteBDWrapper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Mutantes.db";
    private static final int DATABASE_VERSION = 4;
    public static final String MUTANTES = "Mutantes";
    public static final String MUTANTE_ID = "_id";
    public static final String MUTANTE_NAME = "_name";

    private static final String DATABASE_CREATE_MUTANTE = "create table " +
            MUTANTES + "(" + MUTANTE_ID + " integer primary key autoincrement, " +
            MUTANTE_NAME + " text not null);";

    public static final String SKILLS = "Skills";
    public static final String MUTANTE_SKILL_ID = "_id";
    public static final String SKILL_NAME = "_name";

    private static final String DATABASE_CREATE_SKILLS = "create table " +
            SKILLS + "(" + MUTANTE_SKILL_ID + " integer, " +
            SKILL_NAME + " text not null);";

    public MutanteBDWrapper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

    @Override
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        super.setWriteAheadLoggingEnabled(enabled);
    }

    @Override
    public void setLookasideConfig(int slotSize, int slotCount) {
        super.setLookasideConfig(slotSize, slotCount);
    }

    @Override
    public void setOpenParams(SQLiteDatabase.OpenParams openParams) {
        super.setOpenParams(openParams);
    }

    @Override
    public void setIdleConnectionTimeout(long idleConnectionTimeoutMs) {
        super.setIdleConnectionTimeout(idleConnectionTimeoutMs);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_MUTANTE);
        db.execSQL(DATABASE_CREATE_SKILLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MUTANTES);
        db.execSQL("DROP TABLE IF EXISTS " + SKILLS);
        onCreate(db);
    }
}
