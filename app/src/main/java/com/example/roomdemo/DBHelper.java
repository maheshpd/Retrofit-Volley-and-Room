package com.example.roomdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DBHelper extends SQLiteAssetHelper {

    private static final String DB_NAME = "printing.s3db";
    private static final int DB_VER = 1;
    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context)
    {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    //get all Data
    public List<printModel> getAllData()
    {
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from tbl_examsetsubject;",null);
        List<printModel> printModelList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast())
            {
                printModel printModel = new printModel(cursor.getString(cursor.getColumnIndex("version")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("subjects")),
                        cursor.getString(cursor.getColumnIndex("minposition")),
                        cursor.getString(cursor.getColumnIndex("maxposition")),
                        cursor.getString(cursor.getColumnIndex("examid")));
                printModelList.add(printModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return printModelList;

    }
}
