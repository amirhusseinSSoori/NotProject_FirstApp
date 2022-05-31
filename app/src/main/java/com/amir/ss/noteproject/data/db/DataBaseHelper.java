package com.amir.ss.noteproject.data.db;

import static com.amir.ss.noteproject.data.db.Query.CATEGORY_CREATE;
import static com.amir.ss.noteproject.data.db.Query.CATEGORY_INSERT;
import static com.amir.ss.noteproject.data.db.Query.CATEGORY_SELECT;
import static com.amir.ss.noteproject.data.db.Query.Category_DELETE;
import static com.amir.ss.noteproject.data.db.Query.Category_UPDATE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.amir.ss.noteproject.Category;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "mydata_base", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CATEGORY_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
