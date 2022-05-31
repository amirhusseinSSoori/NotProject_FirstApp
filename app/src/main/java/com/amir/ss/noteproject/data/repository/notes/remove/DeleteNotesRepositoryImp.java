package com.amir.ss.noteproject.data.repository.notes.remove;

import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSource;

public class DeleteNotesRepositoryImp implements DeleteNotesRepository {

    DeleteNotesSource deleteNotesSource;

    public DeleteNotesRepositoryImp(DeleteNotesSource deleteNotesSource){
        this.deleteNotesSource = deleteNotesSource;
    }

    @Override
    public void deleteToNotes(int CategoryId) {
        deleteNotesSource.deleteCategory(CategoryId);
    }
}
