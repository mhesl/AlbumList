package com.example.alnumlist.database.photo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alnumlist.database.album.Contract;
import com.example.alnumlist.models.Album_Details_Model;
import com.example.alnumlist.models.Album_Model;

import java.util.ArrayList;
import java.util.List;

public class PhotoDataSource {
    private static PhotoSqlOpenHelper photoSqlOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static PhotoDataSource uniqueInstance;

    private PhotoDataSource(Context context) {
        photoSqlOpenHelper = new PhotoSqlOpenHelper(context);
    }

    public static PhotoDataSource getInstance() {
        if (uniqueInstance == null)
            throw new NullPointerException("Please call initialize() before getting the instance.");
        return uniqueInstance;
    }

    public synchronized static void initialize(Context applicationContext) {
        if (applicationContext == null)
            throw new NullPointerException("Provided application context is null");
        else if (uniqueInstance == null) {
            uniqueInstance = new PhotoDataSource(applicationContext);
        }
    }


    public void open() {
        sqLiteDatabase = photoSqlOpenHelper.getWritableDatabase();
    }

    public void close() {
        photoSqlOpenHelper.close();
    }

    public int getPhotoCounts(){
        String queryString = "select * from " + PhotoContract.PSI.TABLE_NAME;
        List<Album_Details_Model> models = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        try {
            while (cursor.moveToNext()) {
                Album_Details_Model model = new Album_Details_Model();
                model.setUrl(cursor.getString(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_URL)));
                models.add(model);
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return models.size();
    }

    public List<Album_Details_Model> getAlbumPhotos(int id) {
        String queryString = "select * from " + PhotoContract.PSI.TABLE_NAME;
        String where = PhotoContract.PSI.COLUMN_ALBUM_ID + " = ?";
        String idd = id+"";
        String[] whereArgs = {idd};
        //Cursor cursor = sqLiteDatabase.query(PhotoContract.PSI.TABLE_NAME , null , where , whereArgs ,null ,null,null);
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        List<Album_Details_Model> photos = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_ALBUM_ID)) == id) {
                    Album_Details_Model photo = new Album_Details_Model();
                    photo.setId(cursor.getInt(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_ID)));
                    photo.setTitle(cursor.getString(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_TITLE)));
                    photo.setAlbumId(id);
                    photo.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_THUMBNAIL)));
                    photo.setUrl(cursor.getString(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_URL)));
                    photos.add(photo);
                }
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return photos;
    }


    public Album_Details_Model getSinglePhoto(int id) {
        String queryString = "select * from " + PhotoContract.PSI.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        Album_Details_Model photo = new Album_Details_Model();
        try {
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_ID)) == id) {
                    photo.setId(cursor.getInt(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_ID)));
                    photo.setTitle(cursor.getString(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_TITLE)));
                    photo.setAlbumId(id);
                    photo.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_THUMBNAIL)));
                    photo.setUrl(cursor.getString(cursor.getColumnIndex(PhotoContract.PSI.COLUMN_PHOTO_URL)));
                }
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return photo;
    }

    public void addAllPhotos(List<Album_Details_Model> models){
        for (int i = 0; i < models.size(); i++) {
            Album_Details_Model model = models.get(i);
            ContentValues values = new ContentValues();
            values.put(PhotoContract.PSI.COLUMN_ALBUM_ID, model.getAlbumId());
            values.put(PhotoContract.PSI.COLUMN_PHOTO_ID, model.getId());
            values.put(PhotoContract.PSI.COLUMN_PHOTO_THUMBNAIL, model.getThumbnailUrl());
            values.put(PhotoContract.PSI.COLUMN_PHOTO_URL, model.getUrl());
            values.put(PhotoContract.PSI.COLUMN_PHOTO_TITLE, model.getTitle());
            sqLiteDatabase.insertWithOnConflict(PhotoContract.PSI.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

}
