package com.example.ale_proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database{

    private static final String DATABASE_NAME = "DB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "rewards";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_REWARDS = "Rewards";
    private static final String COLUMN_USERS = "user";



    private SQLiteDatabase dataBase;

    public Database(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        dataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String user,int rewards) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_REWARDS, rewards);
        cv.put(COLUMN_USERS, user);

        return dataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Rewards md) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_USERS, md.getUsers());
        cv.put(COLUMN_REWARDS, md.getRecord());
        return dataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public void deleteAll() {
        dataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        dataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Rewards select(long id) {
        Cursor cursor = dataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        cursor.moveToFirst();
        String user = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERS));
        int record = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_REWARDS));

        return new Rewards(id, user, record);
    }

    public ArrayList<Rewards> selectAll() {
        Cursor cursor = dataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Rewards> arr = new ArrayList<Rewards>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String user = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERS));
                int rewards =cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_REWARDS));
                arr.add(new Rewards(id, user, rewards));
            } while (cursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERS + " TEXT, " +
                    COLUMN_REWARDS+ " INTEGER)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }

}