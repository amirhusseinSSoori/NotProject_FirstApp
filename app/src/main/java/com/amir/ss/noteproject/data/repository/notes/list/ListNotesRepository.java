package com.amir.ss.noteproject.data.repository.notes.list;

import androidx.lifecycle.LiveData;

import com.amir.ss.noteproject.data.model.Category;

import java.util.ArrayList;

public interface ListNotesRepository {
    LiveData<ArrayList<Category>> getListNotes();
}
