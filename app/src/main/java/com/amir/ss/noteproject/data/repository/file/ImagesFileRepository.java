package com.amir.ss.noteproject.data.repository.file;

import androidx.lifecycle.LiveData;

import com.amir.ss.noteproject.data.model.ContentModel;

import java.util.ArrayList;

public interface ImagesFileRepository {
    LiveData<ArrayList<ContentModel>> loadImagesToRepository();
}
