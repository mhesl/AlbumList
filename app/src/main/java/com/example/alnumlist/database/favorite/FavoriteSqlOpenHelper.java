package com.example.alnumlist.database.favorite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.alnumlist.database.album.Contract;

public class FavoriteSqlOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databasefav";
    public static final int DATABASE_VERSION = 1;

    public FavoriteSqlOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FavoriteContract.CREATE_TABLE_MODEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" drop table if exists " + FavoriteContract.FSI.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
