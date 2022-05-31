package com.amir.ss.noteproject.data.datasource.local.remove;

import static com.amir.ss.noteproject.data.db.Query.Category_DELETE;

import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class DeleteNotesSourceImp  implements  DeleteNotesSource{

    DataBaseHelper dataBaseHelper;

    public DeleteNotesSourceImp(DataBaseHelper dataBaseHelper){
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public void deleteCategory(int CategoryId) {
        dataBaseHelper.getWritableDatabase().execSQL(Category_DELETE, new Object[]{CategoryId});
    }
}
