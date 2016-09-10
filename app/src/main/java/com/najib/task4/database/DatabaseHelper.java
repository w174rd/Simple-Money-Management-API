package com.najib.task4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by w174rd on 9/5/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app_db.db";

    public static String TABLE_NAME = "";

    public static final String TABLE_NAME_EX = "expanses";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_AMOUNT = "amount";

    public static final String TABLE_NAME_IN = "income";
    /*
    public static final String COLUMN_IN_ID = "in_id";
    public static final String COLUMN_IN_DESC = "in_desc";
    public static final String COLUMN_IN_AMOUNT = "in_amount"; */

    private static final String db_create_ex = "create table "
            + TABLE_NAME_EX + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DESC + " varchar(50) not null, "
            + COLUMN_AMOUNT + " varchar(50) not null);";

    private static final String db_create_in = "create table "
            + TABLE_NAME_IN + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DESC + " varchar(50) not null, "
            + COLUMN_AMOUNT + " varchar(50) not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(db_create_ex);
        sqLiteDatabase.execSQL(db_create_in);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_IN);
        onCreate(db);
    }


    /* START CRUD TABLE EXPANSES*/

    public boolean saveData(String table, String desc, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(COLUMN_DESC, desc);
        content_values.put(COLUMN_AMOUNT, amount);
        if (table.equals("ex")) {
            TABLE_NAME = TABLE_NAME_EX;
        } else {
            TABLE_NAME = TABLE_NAME_IN;
        }
        long result = db.insert(TABLE_NAME, null, content_values);
        return result != -1;
    }

    public Cursor listData(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (table.equals("ex")) {
            TABLE_NAME = TABLE_NAME_EX;
        } else {
            TABLE_NAME = TABLE_NAME_IN;
        }
        Cursor datas = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return datas;
    }

    public boolean updateData(String table, String id, String desc, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(COLUMN_ID, id);
        content_values.put(COLUMN_DESC, desc);
        content_values.put(COLUMN_AMOUNT, amount);
        if (table.equals("ex")) {
            TABLE_NAME = TABLE_NAME_EX;
        } else {
            TABLE_NAME = TABLE_NAME_IN;
        }
        db.update(TABLE_NAME, content_values, "_id = ? ", new String[]{id});
        return true;
    }

    public Integer deleteData(String table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (table.equals("ex")) {
            TABLE_NAME = TABLE_NAME_EX;
        } else {
            TABLE_NAME = TABLE_NAME_IN;
        }
        return db.delete(TABLE_NAME, "_id = ?", new String[]{id});
    }
}
