package com.amir.ss.noteproject.data.repository.list;

import androidx.lifecycle.LiveData;

import com.amir.ss.noteproject.Category;
import com.amir.ss.noteproject.data.datasource.list.ListNotesSource;

import java.util.ArrayList;

public class ListNotesRepositoryImp implements ListNotesRepository {

    ListNotesSource listNotesSource;

    public ListNotesRepositoryImp(ListNotesSource listNotesSource) {
        this.listNotesSource = listNotesSource;
    }

    @Override
    public LiveData<ArrayList<Category>> getListNotes() {
        return listNotesSource.getCategory();
    }
}