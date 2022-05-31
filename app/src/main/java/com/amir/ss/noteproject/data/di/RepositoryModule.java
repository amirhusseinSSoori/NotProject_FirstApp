package com.amir.ss.noteproject.data.di;

import com.amir.ss.noteproject.data.datasource.local.insert.InsertNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.list.ListNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.remove.DeleteNotesSourceImp;
import com.amir.ss.noteproject.data.datasource.local.update.UpdateNotesSourceImp;
import com.amir.ss.noteproject.data.repository.insert.InsertNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.list.ListNotesRepository;
import com.amir.ss.noteproject.data.repository.list.ListNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.remove.DeleteNotesRepository;
import com.amir.ss.noteproject.data.repository.remove.DeleteNotesRepositoryImp;
import com.amir.ss.noteproject.data.repository.update.UpdateNotesRepository;
import com.amir.ss.noteproject.data.repository.update.UpdateNotesRepositoryImp;

public class RepositoryModule {

    private static InsertNotesRepositoryImp insertRepositoryImp;
    private static DeleteNotesRepositoryImp deleteRepositoryImp;
    private static ListNotesRepositoryImp listNotesRepositoryImp;
    private static UpdateNotesRepositoryImp updateNotesRepositoryImp;




    public static InsertNotesRepositoryImp insertRepositoryInstance(InsertNotesSourceImp insertNotesSourceImp) {
        if (insertRepositoryImp == null) {
            insertRepositoryImp = new InsertNotesRepositoryImp(insertNotesSourceImp);
        }
        return insertRepositoryImp;
    }


    public static DeleteNotesRepository deleteRepositoryInstance(DeleteNotesSourceImp deleteNotesSourceImp) {
        if (deleteRepositoryImp == null) {
            deleteRepositoryImp = new DeleteNotesRepositoryImp(deleteNotesSourceImp);
        }
        return deleteRepositoryImp;
    }

    public static ListNotesRepository showRepositoryInstance(ListNotesSourceImp listNotesSourceImp) {
        if (listNotesRepositoryImp == null) {
            listNotesRepositoryImp = new ListNotesRepositoryImp(listNotesSourceImp);
        }
        return listNotesRepositoryImp;
    }

    public static UpdateNotesRepository updateNotesRepositoryInstance(UpdateNotesSourceImp updateNotesSourceImp){
        if(updateNotesRepositoryImp == null){
            updateNotesRepositoryImp = new UpdateNotesRepositoryImp(updateNotesSourceImp);
        }
        return updateNotesRepositoryImp;
    }

}
