package com.amir.ss.noteproject.data.di;

import com.amir.ss.noteproject.data.datasource.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.repository.insert.InsertRepository;
import com.amir.ss.noteproject.data.repository.insert.InsertRepositoryImp;
import com.amir.ss.noteproject.data.repository.list.ListNotesRepository;
import com.amir.ss.noteproject.data.repository.list.ListNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.remove.DeleteRepository;
import com.amir.ss.noteproject.data.repository.remove.DeleteRepositoryImp;

public class RepositoryModule {

    private static InsertRepositoryImp insertRepositoryImp;
    private static DeleteRepositoryImp deleteRepositoryImp;
    private static ListNotesRepositoryImp listNotesRepositoryImp;




    public static InsertRepositoryImp insertRepositoryInstance(InsertNotesSourceImp insertNotesSourceImp) {
        if (insertRepositoryImp == null) {
            insertRepositoryImp = new InsertRepositoryImp(insertNotesSourceImp);
        }
        return insertRepositoryImp;
    }


    public static DeleteRepository deleteRepositoryInstance(DeleteNotesSourceImp deleteNotesSourceImp) {
        if (deleteRepositoryImp == null) {
            deleteRepositoryImp = new DeleteRepositoryImp(deleteNotesSourceImp);
        }
        return deleteRepositoryImp;
    }

    public static ListNotesRepository showRepositoryInstance(ListNotesSourceImp listNotesSourceImp) {
        if (listNotesRepositoryImp == null) {
            listNotesRepositoryImp = new ListNotesRepositoryImp(listNotesSourceImp);
        }
        return listNotesRepositoryImp;
    }

}
