package com.amir.ss.noteproject.data.repository.notes.update;

import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSource;

public class UpdateNotesRepositoryImp implements UpdateNotesRepository {

    UpdateNotesSource updateNotesSource;
    public UpdateNotesRepositoryImp(UpdateNotesSource updateNotesSource){
        this.updateNotesSource=updateNotesSource;
    }
    @Override
    public void Update(int CategoryId, String title, String detail) {
        updateNotesSource.UpdateCategory(CategoryId,title,detail);
    }
}
