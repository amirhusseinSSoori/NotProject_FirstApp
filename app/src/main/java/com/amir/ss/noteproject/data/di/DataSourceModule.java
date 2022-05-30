package com.amir.ss.noteproject.data.di;

import com.amir.ss.noteproject.data.datasource.insert.InsertNotesSource;
import com.amir.ss.noteproject.data.datasource.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.list.ListNotesSource;
import com.amir.ss.noteproject.data.datasource.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.remove.DeleteNotesSource;
import com.amir.ss.noteproject.data.datasource.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class DataSourceModule {

    private static ListNotesSourceImp listNotesSourceImp;
    private static DeleteNotesSourceImp deleteNotesSourceImp;
    private static InsertNotesSourceImp insertNotesSourceImp;





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
}
