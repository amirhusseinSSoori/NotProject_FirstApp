package com.amir.ss.noteproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String CATEGORY_CREATE="CREATE TABLE IF NOT EXISTS Category(Categoryid INTEGER PRIMARY KEY AUTOINCREMENT,Titel TEXT,Detail TEXT, Date TEXT)";
    static final String CATEGORY_INSERT="INSERT INTO Category (Titel,Detail,Date) VALUES(?,?,?)";
    static final String CATEGORY_SELECT="SELECT Categoryid,Titel ,Detail,Date From Category";
    static final String Category_DELETE="DELETE From Category Where Categoryid = ?";
    static final String Category_UPDATE="UPDATE Category SET Titel = ?, Detail = ?Where Categoryid = ?";
    public void UpdateCategory(int Categoryid,String titel,String detail){
        getWritableDatabase().execSQL(Category_UPDATE,new Object[]{titel,detail,Categoryid});
    }
    public DataBaseHelper(Context context) {
        super(context,"mydata_base",null,1);
    }

    public void deleteCategory( int Categoryid){
        getWritableDatabase().execSQL(Category_DELETE,new Object[]{Categoryid});

    }

    public List<Category> getCategory(){
        ArrayList<Category> categories=new ArrayList<>();
        Cursor cursor=getReadableDatabase().rawQuery(CATEGORY_SELECT,null);
        if(cursor.moveToFirst()){
            do{
                Category category=new Category();
                category.setCategoryid(cursor.getInt(0));
                category.setTitel(cursor.getString(1));
                category.setDetail(cursor.getString(2));
                category.setDate(cursor.getString(3));
                categories.add(category);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return categories;

    }
     public  void  add_Category(String titel,String detail,String Date){
        getWritableDatabase().execSQL(CATEGORY_INSERT,new Object[]{titel,detail,Date});
     }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CATEGORY_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
