package com.amir.ss.noteproject.data.di;

import com.amir.ss.noteproject.MyApplication;
import com.amir.ss.noteproject.data.db.DataBaseHelper;

public class LocalModule {

    private static DataBaseHelper dataBase;

    public static DataBaseHelper provideDataBase() {
        if (dataBase == null) {
            dataBase = new DataBaseHelper(MyApplication.getAppContext());
        }
        return dataBase;
    }
}
