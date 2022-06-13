package com.amir.ss.noteproject.data.datasource.file;

import android.graphics.Bitmap;

import java.io.IOException;

public interface InsertFileSource {
     void saveImage(Bitmap bitmap, String name) throws IOException;
}
