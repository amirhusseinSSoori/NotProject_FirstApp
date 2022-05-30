package com.amir.ss.noteproject.data.db;

public class Query {

    public static final String CATEGORY_CREATE = "CREATE TABLE IF NOT EXISTS Category(Categoryid INTEGER PRIMARY KEY AUTOINCREMENT,Titel TEXT,Detail TEXT, Date TEXT)";
    public static final String CATEGORY_INSERT = "INSERT INTO Category (Titel,Detail,Date) VALUES(?,?,?)";
    public static final String CATEGORY_SELECT = "SELECT Categoryid,Titel ,Detail,Date From Category";
    public static final String Category_DELETE = "DELETE From Category Where Categoryid = ?";
    public static final String Category_UPDATE = "UPDATE Category SET Titel = ?, Detail = ?Where Categoryid = ?";
}
