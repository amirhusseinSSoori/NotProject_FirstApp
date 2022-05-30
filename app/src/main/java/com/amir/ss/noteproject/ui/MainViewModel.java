package com.amir.ss.noteproject.ui;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.amir.ss.noteproject.Category;
import com.amir.ss.noteproject.data.repository.list.ListNotesRepository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    ListNotesRepository listNotesRepository;

    private final MutableLiveData<ArrayList<Category>> resultList = new MutableLiveData<ArrayList<Category>>();
    public LiveData<ArrayList<Category>> _resultList = resultList;


    MainViewModel(ListNotesRepository listNotesRepository) {
        this.listNotesRepository = listNotesRepository;

        event();

    }





    void event() {
        resultList.postValue(listNotesRepository.getListNotes().getValue());
    }


}
