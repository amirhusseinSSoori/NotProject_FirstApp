package com.amir.ss.noteproject.data.di.local;

import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSourceImp;
import com.amir.ss.noteproject.data.repository.notes.insert.InsertNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.insert.InsertNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.notes.list.ListNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.list.ListNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.notes.remove.DeleteNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.remove.DeleteNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.notes.update.UpdateNotesRepository;
import com.amir.ss.noteproject.data.repository.notes.update.UpdateNotesRepositoryImp;

public class RepositoryLocalModule {

    public InsertNotesRepository insertRepositoryInstance(InsertNotesSourceImp insertNotesSourceImp) {
        return new InsertNotesRepositoryImp(insertNotesSourceImp);
    }

    public DeleteNotesRepository deleteRepositoryInstance(DeleteNotesSourceImp deleteNotesSourceImp) {
        return new DeleteNotesRepositoryImp(deleteNotesSourceImp);
    }

    public ListNotesRepository showRepositoryInstance(ListNotesSourceImp listNotesSourceImp) {
        return new ListNotesRepositoryImp(listNotesSourceImp);
    }

    public UpdateNotesRepository updateNotesRepositoryInstance(UpdateNotesSourceImp updateNotesSourceImp) {
        return new UpdateNotesRepositoryImp(updateNotesSourceImp);
    }

}
