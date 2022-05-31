package com.amir.ss.noteproject.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amir.ss.noteproject.Category;
import com.amir.ss.noteproject.data.repository.notes.insert.InsertNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.list.ListNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.remove.DeleteNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.update.UpdateNotesRepository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    ListNotesRepository listNotesRepository;
    InsertNotesRepository insertRepository;
    DeleteNotesRepository deleteRepository;
    UpdateNotesRepository updateNotesRepository;

    private final MutableLiveData<ArrayList<Category>> resultList = new MutableLiveData<ArrayList<Category>>();
    public LiveData<ArrayList<Category>> _resultList = resultList;


    public MainViewModel(ListNotesRepository listNotesRepository, InsertNotesRepository insertRepository, DeleteNotesRepository deleteRepository, UpdateNotesRepository updateNotesRepository) {
        this.listNotesRepository = listNotesRepository;
        this.insertRepository = insertRepository;
        this.deleteRepository = deleteRepository;
        this.updateNotesRepository = updateNotesRepository;
        event();
    }


    public void insertToNotes(String title, String detail, String Date) {
        insertRepository.insertToNotes(title, detail, Date);
    }

    public void updateToNotes(int CategoryId, String title, String detail) {
        updateNotesRepository.Update(CategoryId, title, detail);
    }


    public void deleteInItemNotes(int CategoryId) {
        deleteRepository.deleteToNotes(CategoryId);
    }


    public void event() {
        listNotesRepository.getListNotes().observeForever(resultList::postValue);
    }


}
