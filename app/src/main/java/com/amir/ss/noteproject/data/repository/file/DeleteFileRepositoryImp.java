package com.amir.ss.noteproject.data.repository.file;

import android.net.Uri;

import com.amir.ss.noteproject.data.datasource.file.DeleteFileSource;

public class DeleteFileRepositoryImp implements DeleteFileRepository {
    DeleteFileSource deleteFileSource;

    public DeleteFileRepositoryImp(DeleteFileSource deleteFileSource) {
        this.deleteFileSource = deleteFileSource;
    }

    @Override
    public void deleteFileInRepository(Uri uri,int id) {
        deleteFileSource.deleteFile(uri,id);
    }
}
