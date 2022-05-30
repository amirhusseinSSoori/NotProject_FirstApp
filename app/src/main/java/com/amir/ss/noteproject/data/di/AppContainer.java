package com.amir.ss.noteproject.data.di;

import com.amir.ss.noteproject.MyApplication;
import com.amir.ss.noteproject.data.datasource.insert.InsertNotesSource;
import com.amir.ss.noteproject.data.datasource.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.list.ListNotesSource;
import com.amir.ss.noteproject.data.datasource.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.remove.DeleteNotesSource;
import com.amir.ss.noteproject.data.datasource.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.db.DataBaseHelper;
import com.amir.ss.noteproject.data.repository.insert.InsertRepository;
import com.amir.ss.noteproject.data.repository.list.ListNotesRepository;
import com.amir.ss.noteproject.data.repository.list.ListNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.remove.DeleteRepository;

public class AppContainer {

    private DataBaseHelper provideDataBase(){
        return new DataBaseHelper(MyApplication.getAppContext());
    }

    // for dataSource
    private InsertNotesSource provideInsertNoteSource() {
        return DataSourceModule.insertNotesInstance(provideDataBase());
    }
    private DeleteNotesSource provideDeleteNoteSource() {
        return DataSourceModule.deleteNotesInstance(provideDataBase());
    }
    private ListNotesSource provideListNoteSource() {
        return DataSourceModule.listNotesSourceInstance(provideDataBase());
    }


    // for repository
    private InsertRepository provideInsertRepository(){
        return RepositoryModule.insertRepositoryInstance((InsertNotesSourceImp) provideInsertNoteSource());
    }
    private DeleteRepository provideDeleteRepository(){
        return RepositoryModule.deleteRepositoryInstance((DeleteNotesSourceImp) provideDeleteNoteSource());
    }

    private ListNotesRepository provideListNotesRepository(){
        return RepositoryModule.showRepositoryInstance((ListNotesSourceImp) provideListNoteSource());
    }


    //for VieModel
















}
