package com.amir.ss.noteproject.data.di;

import static com.amir.ss.noteproject.data.di.local.LocalModule.provideDataBase;

import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSource;
import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSource;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSource;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSource;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSourceImp;
import com.amir.ss.noteproject.data.di.local.DataSourceLocalModule;
import com.amir.ss.noteproject.data.di.local.RepositoryLocalModule;
import com.amir.ss.noteproject.data.repository.notes.insert.InsertNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.list.ListNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.remove.DeleteNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.update.UpdateNotesRepository;
import com.amir.ss.noteproject.ui.MainViewModel;

public class AppContainer {

    // for dataSource
    private InsertNotesSource provideInsertNoteSource() {
        return new DataSourceLocalModule().insertNotesInstance(provideDataBase());
    }

    private DeleteNotesSource provideDeleteNoteSource() {
        return new DataSourceLocalModule().deleteNotesInstance(provideDataBase());
    }

    private ListNotesSource provideListNoteSource() {
        return new DataSourceLocalModule().listNotesSourceInstance(provideDataBase());
    }

    private UpdateNotesSource provideUpdateNoteSource(){
        return new DataSourceLocalModule().updateNotesInstance(provideDataBase());
    }


    // for repository
    private InsertNotesRepository provideInsertRepository() {
        return new RepositoryLocalModule().insertRepositoryInstance((InsertNotesSourceImp) provideInsertNoteSource());
    }

    private DeleteNotesRepository provideDeleteRepository() {
        return new RepositoryLocalModule().deleteRepositoryInstance((DeleteNotesSourceImp) provideDeleteNoteSource());
    }

    private ListNotesRepository provideListNotesRepository() {
        return new RepositoryLocalModule().showRepositoryInstance((ListNotesSourceImp) provideListNoteSource());
    }

    private UpdateNotesRepository provideUpdateRepository(){
        return new RepositoryLocalModule().updateNotesRepositoryInstance((UpdateNotesSourceImp) provideUpdateNoteSource());
    }


    //for ViewModel
    public MainViewModel ProvideMainVieModel() {
        return new MainViewModel(provideListNotesRepository(), provideInsertRepository(), provideDeleteRepository(),provideUpdateRepository());
    }


}
