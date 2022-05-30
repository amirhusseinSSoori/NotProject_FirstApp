package com.amir.ss.noteproject.data.repository.insert;

import com.amir.ss.noteproject.data.datasource.insert.InsertNotesSource;

public class InsertRepositoryImp implements InsertRepository {

    InsertNotesSource insertNotesSource;

    public InsertRepositoryImp(InsertNotesSource insertNotesSource){
        this.insertNotesSource = insertNotesSource;
    }


    @Override
    public void insertToNotes(String title, String detail, String Date) {
        insertNotesSource.insert(title,detail,Date);
    }
}
