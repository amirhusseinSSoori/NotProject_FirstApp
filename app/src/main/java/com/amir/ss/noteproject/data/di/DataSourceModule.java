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

    private static ListNotesSourceImp listNotesSourceImp;
    private static DeleteNotesSourceImp deleteNotesSourceImp;
    private static InsertNotesSourceImp insertNotesSourceImp;
    private static UpdateNotesSourceImp updateNotesSourceImp;


    public static ListNotesSource listNotesSourceInstance(DataBaseHelper dataBaseHelper) {
        if (listNotesSourceImp == null) {
            listNotesSourceImp = new ListNotesSourceImp(dataBaseHelper);
        }
        return listNotesSourceImp;
    }


    public static DeleteNotesSource deleteNotesInstance(DataBaseHelper dataBaseHelper) {
        if (deleteNotesSourceImp == null) {
            deleteNotesSourceImp = new DeleteNotesSourceImp(dataBaseHelper);
        }
        return deleteNotesSourceImp;
    }


    public static InsertNotesSource insertNotesInstance(DataBaseHelper dataBaseHelper) {
        if (insertNotesSourceImp == null) {
            insertNotesSourceImp = new InsertNotesSourceImp(dataBaseHelper);
        }
        return insertNotesSourceImp;
    }

    public static UpdateNotesSource updateNotesInstance(DataBaseHelper dataBaseHelper) {
        if (updateNotesSourceImp == null) {
            updateNotesSourceImp = new UpdateNotesSourceImp(dataBaseHelper);
        }
        return updateNotesSourceImp;
    }
}
