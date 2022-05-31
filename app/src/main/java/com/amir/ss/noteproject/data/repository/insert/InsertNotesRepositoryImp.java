package com.amir.ss.noteproject.data.repository.insert;

import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSource;

public class InsertNotesRepositoryImp implements InsertNotesRepository {

    InsertNotesSource insertNotesSource;

    public InsertNotesRepositoryImp(InsertNotesSource insertNotesSource){
        this.insertNotesSource = insertNotesSource;
    }


    @Override
    public void insertToNotes(String title, String detail, String Date) {
        insertNotesSource.insert(title,detail,Date);
    }
}
