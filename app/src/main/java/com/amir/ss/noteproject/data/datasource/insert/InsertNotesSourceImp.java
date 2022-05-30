package com.amir.ss.noteproject.data.datasource.insert;

import static com.amir.ss.noteproject.data.db.Query.CATEGORY_INSERT;

import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class InsertNotesSourceImp implements InsertNotesSource {

    DataBaseHelper dataBaseHelper;

    public InsertNotesSourceImp(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public void insert(String title, String detail, String Date) {
        dataBaseHelper.getWritableDatabase().execSQL(CATEGORY_INSERT, new Object[]{title, detail, Date});
    }
}
