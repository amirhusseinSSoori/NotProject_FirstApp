package com.amir.ss.noteproject.ui.viewmodel;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amir.ss.noteproject.data.model.ContentModel;
import com.amir.ss.noteproject.data.repository.file.ImagesFileRepository;
import com.amir.ss.noteproject.data.repository.file.InsertFileRepository;

import java.io.IOException;
import java.util.ArrayList;

public class DrawsViewModel extends ViewModel {


    ImagesFileRepository imagesFileRepository;
    InsertFileRepository insertFileRepository;


    private final MutableLiveData<ArrayList<ContentModel>> resultList = new MutableLiveData<ArrayList<ContentModel>>();
    public LiveData<ArrayList<ContentModel>> _resultList = resultList;

    public DrawsViewModel(ImagesFileRepository imagesFileRepository, InsertFileRepository insertFileRepository) {
        this.imagesFileRepository = imagesFileRepository;
        this.insertFileRepository = insertFileRepository;
        showAllImages();
    }



    public  void insertImages(Bitmap bitmap, String name) throws IOException {
        insertFileRepository.insertImageToRepository(bitmap,name);

    }
    public void showAllImages() {
        imagesFileRepository.loadImagesToRepository().observeForever(resultList::postValue);
    }

}
