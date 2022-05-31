package com.amir.ss.noteproject.data.datasource.local.list;

import androidx.lifecycle.LiveData;

import com.amir.ss.noteproject.Category;

import java.util.ArrayList;

public interface ListNotesSource {
     LiveData<ArrayList<Category>> getCategory();
}
