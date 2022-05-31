package com.amir.ss.noteproject.data.di;

import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSource;
import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSource;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSource;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSource;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSourceImp;
import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class DataSourceModule {

    public static ListNotesSource listNotesSourceInstance(DataBaseHelper dataBaseHelper) {
        return new ListNotesSourceImp(dataBaseHelper);
    }

    public static DeleteNotesSource deleteNotesInstance(DataBaseHelper dataBaseHelper) {
        return new DeleteNotesSourceImp(dataBaseHelper);
    }

    public static InsertNotesSource insertNotesInstance(DataBaseHelper dataBaseHelper) {
        return new InsertNotesSourceImp(dataBaseHelper);
    }

    public static UpdateNotesSource updateNotesInstance(DataBaseHelper dataBaseHelper) {
        return new UpdateNotesSourceImp(dataBaseHelper);
    }
}
