package com.amir.ss.noteproject.data.datasource.local.update;

import static com.amir.ss.noteproject.data.db.Query.Category_UPDATE;

import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class UpdateNotesSourceImp implements UpdateNotesSource {

    DataBaseHelper dataBaseHelper;

    public UpdateNotesSourceImp(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public void UpdateCategory(int CategoryId, String title, String detail) {
        dataBaseHelper.getWritableDatabase().execSQL(Category_UPDATE, new Object[]{title, detail, CategoryId});
    }
}
