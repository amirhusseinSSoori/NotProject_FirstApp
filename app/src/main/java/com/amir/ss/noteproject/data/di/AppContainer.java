package com.amir.ss.noteproject.data.di;

import static com.amir.ss.noteproject.data.di.LocalModule.provideDataBase;

import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSource;
import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSource;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSource;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSource;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSourceImp;
import com.amir.ss.noteproject.data.repository.notes.insert.InsertNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.list.ListNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.remove.DeleteNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.update.UpdateNotesRepository;
import com.amir.ss.noteproject.ui.MainViewModel;

public class AppContainer {

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

    private UpdateNotesSource provideUpdateNoteSource(){
        return DataSourceModule.updateNotesInstance(provideDataBase());
    }


    // for repository
    private InsertNotesRepository provideInsertRepository() {
        return RepositoryModule.insertRepositoryInstance((InsertNotesSourceImp) provideInsertNoteSource());
    }

    private DeleteNotesRepository provideDeleteRepository() {
        return RepositoryModule.deleteRepositoryInstance((DeleteNotesSourceImp) provideDeleteNoteSource());
    }

    private ListNotesRepository provideListNotesRepository() {
        return RepositoryModule.showRepositoryInstance((ListNotesSourceImp) provideListNoteSource());
    }

    private UpdateNotesRepository provideUpdateRepository(){
        return RepositoryModule.updateNotesRepositoryInstance((UpdateNotesSourceImp) provideUpdateNoteSource());
    }


    //for ViewModel
    public MainViewModel ProvideMainVieModel() {
        return new MainViewModel(provideListNotesRepository(), provideInsertRepository(), provideDeleteRepository(),provideUpdateRepository());
    }


}
