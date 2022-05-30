package com.amir.ss.noteproject.data.datasource.list;

import static com.amir.ss.noteproject.data.db.Query.CATEGORY_SELECT;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.amir.ss.noteproject.Category;
import com.amir.ss.noteproject.data.db.DataBaseHelper;

import java.util.ArrayList;

public class ListNotesSourceImp implements ListNotesSource {

    DataBaseHelper dataBaseHelper;


    public ListNotesSourceImp(DataBaseHelper dataBaseHelper){
        this.dataBaseHelper= dataBaseHelper;
    }


    @Override
    public LiveData<ArrayList<Category>> getCategory() {
        MutableLiveData<ArrayList<Category>> resultList = new MutableLiveData<ArrayList<Category>>();
        ArrayList<Category> categories = new ArrayList<>();
        Cursor cursor = dataBaseHelper.getReadableDatabase().rawQuery(CATEGORY_SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setCategoryId(cursor.getInt(0));
                category.setTitle(cursor.getString(1));
                category.setDetail(cursor.getString(2));
                category.setDate(cursor.getString(3));
                categories.add(category);
            } while (cursor.moveToNext());
            cursor.close();
        }
        resultList.postValue(categories);
        return resultList;
    }
}
