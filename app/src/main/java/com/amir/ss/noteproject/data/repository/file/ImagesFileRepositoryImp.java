package com.amir.ss.noteproject.data.repository.file;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.amir.ss.noteproject.data.model.ContentModel;
import com.amir.ss.noteproject.data.datasource.file.ImageFileSource;

import java.util.ArrayList;

public class ImagesFileRepositoryImp implements ImagesFileRepository {

    ImageFileSource imageFileSource;

    public ImagesFileRepositoryImp(ImageFileSource imageFileSource) {
        this.imageFileSource = imageFileSource;
    }

    @Override
    public LiveData<ArrayList<ContentModel>> loadImagesToRepository() {
        return imageFileSource.loadImage();
    }
}
