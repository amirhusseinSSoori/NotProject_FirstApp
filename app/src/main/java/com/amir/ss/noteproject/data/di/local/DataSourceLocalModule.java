package com.amir.ss.noteproject.data.di.local;

import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSource;
import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSource;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSource;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSource;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSourceImp;
import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class DataSourceLocalModule {

    public ListNotesSource listNotesSourceInstance(DataBaseHelper dataBaseHelper) {
        return new ListNotesSourceImp(dataBaseHelper);
    }

    public DeleteNotesSource deleteNotesInstance(DataBaseHelper dataBaseHelper) {
        return new DeleteNotesSourceImp(dataBaseHelper);
    }

    public InsertNotesSource insertNotesInstance(DataBaseHelper dataBaseHelper) {
        return new InsertNotesSourceImp(dataBaseHelper);
    }

    public UpdateNotesSource updateNotesInstance(DataBaseHelper dataBaseHelper) {
        return new UpdateNotesSourceImp(dataBaseHelper);
    }
}
