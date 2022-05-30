package com.amir.ss.noteproject.data.repository.remove;

import com.amir.ss.noteproject.data.datasource.remove.DeleteNotesSource;

public class DeleteRepositoryImp implements DeleteRepository {

    DeleteNotesSource deleteNotesSource;

    public DeleteRepositoryImp(DeleteNotesSource deleteNotesSource){
        this.deleteNotesSource=deleteNotesSource;
    }

    @Override
    public void deleteToNotes(int CategoryId) {
        deleteNotesSource.deleteCategory(CategoryId);
    }
}
