package com.example.alnumlist.database.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alnumlist.database.album.AlbumDataSource;
import com.example.alnumlist.database.album.Contract;
import com.example.alnumlist.database.album.MySqlOpenHelper;
import com.example.alnumlist.models.Album_Model;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDataSource {
    private static MySqlOpenHelper mySqlOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static FavoriteDataSource uniqueInstance;

    private FavoriteDataSource(Context context) {
        mySqlOpenHelper = new MySqlOpenHelper(context);
    }
    public static FavoriteDataSource getInstance() {
        if (uniqueInstance == null)
            throw new NullPointerException("Please call initialize() before getting the instance.");
        return uniqueInstance;
    }


    public synchronized static void initialize(Context applicationContext) {
        if (applicationContext == null)
            throw new NullPointerException("Provided application context is null");
        else if (uniqueInstance == null) {
            uniqueInstance = new FavoriteDataSource(applicationContext);
        }
    }


    public void open() {
        sqLiteDatabase = mySqlOpenHelper.getWritableDatabase();
    }

    public void close() {
        mySqlOpenHelper.close();
    }


    public List<Album_Model> getAlbums(){
        String queryString = "select * from " + FavoriteContract.FSI.TABLE_NAME;
        List<Album_Model> models = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        try {
            while (cursor.moveToNext()) {
                Album_Model model = new Album_Model();
                model.setId(cursor.getInt(cursor.getColumnIndex(FavoriteContract.FSI.COLUMN_ALBUM_ID)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(FavoriteContract.FSI.COLUMN_ALBUM_TITLE)));
                model.setUserId(cursor.getInt(cursor.getColumnIndex(FavoriteContract.FSI.COLUMN_ALBUM_USERID)));
                models.add(model);
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return models;
    }

    public void addSingleAlbum(Album_Model model){
        ContentValues values = new ContentValues();
        values.put(FavoriteContract.FSI.COLUMN_ALBUM_ID, model.getId());
        values.put(FavoriteContract.FSI.COLUMN_ALBUM_TITLE, model.getTitle());
        values.put(FavoriteContract.FSI.COLUMN_ALBUM_USERID, model.getUserId());
        sqLiteDatabase.insertWithOnConflict(FavoriteContract.FSI.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }


}
