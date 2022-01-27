package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyAdapter {
    public static final String DATABASE_NAME = "task";

    //Table name
    public static final String TABLE_NAME = "daily_task";

    //DB Version
    public static final int DB_VERSION = 1;

    // Table Columns name
    public static final String COL_ROW = "serialNo";
    public static final String COL_TITLE = "title";
    public static final String COL_SUB = "subject";
    public static final String COL_SUMMARY = "summary";


//    "CAREATE TABLE trainee(col1 dataType, col1 dataType, col1 dataType, col1 dataType, col1 dataType)"

    String createDB = "CREATE TABLE "+TABLE_NAME+" ("+COL_ROW+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_TITLE+" text, "+COL_SUB+" text, "+COL_SUMMARY+" text"+")";

    private SQLiteDatabase sqLiteDatabase;
    private MyDatabase myDatabase;
    public MyAdapter(Context context){
        myDatabase = new MyDatabase(context);
    }

    public MyAdapter openDatabase(){
        sqLiteDatabase = myDatabase.getWritableDatabase();
        return this;
    }

    // TODO : method for insertion records into DB

    public void insertRecord(Context context, String title, String subject, String summary){
        String localMsg;
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_SUB, subject);
        values.put(COL_SUMMARY, summary);


        long insertionValue = sqLiteDatabase.insert(TABLE_NAME, null, values);
        if (insertionValue == -1){
            localMsg = "Insertion failed";
        }else {
            localMsg = "inserted Successfully.";
        }
        Utility.showToast(context, localMsg);
    }

    //TODO : method for retrive records from DB

    public Cursor getAllRecords(String title){
        String[] COLUMNS = {COL_ROW, COL_TITLE, COL_SUB, COL_SUMMARY};
        return sqLiteDatabase.query(TABLE_NAME, COLUMNS, COL_TITLE+" = "+"'"+title+"'", null, null, null, null);
    }

    public Cursor getAllRecords(){
        String[] COLUMNS = {COL_ROW, COL_TITLE, COL_SUB, COL_SUMMARY};
        return sqLiteDatabase.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
    }

    // TODO : delete single record

    public void deleteRecord(String colRow, Context context){
        int id = sqLiteDatabase.delete(TABLE_NAME, COL_ROW+" = "+colRow, null);
        if (id > 0){
            Utility.showToast(context, id+" record deleted.");
        }else {
            Utility.showToast(context, " Something went wrong.");
        }
    }

    // TODO : Delete all records
    public void deleteAllRecords(Context context){
        int id = sqLiteDatabase.delete(TABLE_NAME, null, null);
        if (id > 0){
            Utility.showToast(context, id+" record deleted.");
        }else {
            Utility.showToast(context, " Something went wrong.");
        }
    }

    //TODO : UPDATE records
    public void updateRecord(Context context, String rowId, String title, String subject, String summary){
        String localMsg;
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title );
        values.put(COL_SUB, subject);
        values.put(COL_SUMMARY, summary);

        int updateRecord = sqLiteDatabase.update(TABLE_NAME, values, COL_ROW+" = "+rowId, null);
        if (updateRecord == -1){
            localMsg = "Updation failed";
        }else {
            localMsg = "updated Successfully.";
        }
        Utility.showToast(context, localMsg);
    }


    public class MyDatabase extends SQLiteOpenHelper {

        public MyDatabase(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(createDB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
