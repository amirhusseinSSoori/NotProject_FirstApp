package com.amir.ss.noteproject.data.datasource.file;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.amir.ss.noteproject.MyApplication;
import com.amir.ss.noteproject.data.model.ContentModel;

import java.util.ArrayList;

public class ImageFilesSourceImp implements ImageFileSource {

    @Override
    public LiveData<ArrayList<ContentModel>> loadImage() {
        MutableLiveData<ArrayList<ContentModel>> resultList = new MutableLiveData<ArrayList<ContentModel>>();
        Uri collection;
        ArrayList<ContentModel> list = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String selection = MediaStore.Images.ImageColumns.RELATIVE_PATH + " like ? ";
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.RELATIVE_PATH,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.MediaColumns.WIDTH
        };
        String[] selectionArgs = new String[]{
                "%DCIM/NoteBook%"
        };
        String sortOrder = MediaStore.MediaColumns.DATE_ADDED + " COLLATE NOCASE DESC";
        try (Cursor cursor = MyApplication.getAppContext().getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder);
        ) {
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            int relativePathColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.RELATIVE_PATH);
            int widthPathColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.WIDTH);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumn);
                String displayName = cursor.getString(displayNameColumn);
                String relativePath = cursor.getString(relativePathColumn);
                int width = cursor.getInt(widthPathColumn);
                Uri contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                ContentModel contentModel = new ContentModel();
                contentModel.setContentUris(contentUri);
                contentModel.setDisplayName(displayName);
                contentModel.setWidth(width);
                contentModel.setId(id);
                contentModel.setRelativePath(relativePath);
                list.add(contentModel);

                resultList.postValue(list);
            }
        }


        return resultList;
    }
}
