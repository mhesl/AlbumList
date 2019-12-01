package com.example.alnumlist.database.album;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alnumlist.models.Album_Model;

import java.util.ArrayList;
import java.util.List;

public class AlbumDataSource {
    private static MySqlOpenHelper mySqlOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private List<Album_Model> album_models;
    private static AlbumDataSource uniqueInstance;

    private AlbumDataSource(Context context) {
        mySqlOpenHelper = new MySqlOpenHelper(context);
    }

    public static AlbumDataSource getInstance() {
        if (uniqueInstance == null)
            throw new NullPointerException("Please call initialize() before getting the instance.");
        return uniqueInstance;
    }

    public synchronized static void initialize(Context applicationContext) {
        if (applicationContext == null)
            throw new NullPointerException("Provided application context is null");
        else if (uniqueInstance == null) {
            uniqueInstance = new AlbumDataSource(applicationContext);
        }
    }


    public void open() {
        sqLiteDatabase = mySqlOpenHelper.getWritableDatabase();
    }

    public void close() {
        mySqlOpenHelper.close();
    }

    public List<Album_Model> getAlbums(){
        String queryString = "select * from " + Contract.PTF.TABLE_NAME;
        List<Album_Model> models = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        try {
            while (cursor.moveToNext()) {
                Album_Model model = new Album_Model();
                model.setId(cursor.getInt(cursor.getColumnIndex(Contract.PTF.COLUMN_ALBUM_ID)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(Contract.PTF.COLUMN_ALBUM_TITLE)));
                model.setUserId(cursor.getInt(cursor.getColumnIndex(Contract.PTF.COLUMN_ALBUM_USERID)));
                models.add(model);
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return models;
    }


    public Album_Model getSingleAlbum(int id){
        String queryString = "select * from " + Contract.PTF.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        Album_Model album = new Album_Model();
        try {
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex(Contract.PTF.COLUMN_ALBUM_ID)) == id) {
                    album.setId(cursor.getInt(cursor.getColumnIndex(Contract.PTF.COLUMN_ALBUM_ID)));
                    album.setTitle(cursor.getString(cursor.getColumnIndex(Contract.PTF.COLUMN_ALBUM_TITLE)));
                    album.setUserId(cursor.getInt(cursor.getColumnIndex(Contract.PTF.COLUMN_ALBUM_USERID)));
                }
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return album;
    }

    public void addSingleAlbum(Album_Model model){
        ContentValues values = new ContentValues();
        values.put(Contract.PTF.COLUMN_ALBUM_ID, model.getId());
        values.put(Contract.PTF.COLUMN_ALBUM_TITLE, model.getTitle());
        values.put(Contract.PTF.COLUMN_ALBUM_USERID, model.getUserId());
        sqLiteDatabase.insertWithOnConflict(Contract.PTF.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }


    public void addAllAlbums(List<Album_Model> album_models){
        for (int i = 0; i < album_models.size(); i++) {
            Album_Model model = album_models.get(i);
            ContentValues values = new ContentValues();
            values.put(Contract.PTF.COLUMN_ALBUM_ID, model.getId());
            values.put(Contract.PTF.COLUMN_ALBUM_TITLE, model.getTitle());
            values.put(Contract.PTF.COLUMN_ALBUM_USERID, model.getUserId());
            sqLiteDatabase.insertWithOnConflict(Contract.PTF.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }
}

