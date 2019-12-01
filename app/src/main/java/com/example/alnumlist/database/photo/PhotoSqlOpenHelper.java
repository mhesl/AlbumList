package com.example.alnumlist.database.photo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PhotoSqlOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "photo_database";
    public static final int DATABASE_VERSION = 1;

    public PhotoSqlOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PhotoContract.CREATE_TABLE_MODEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" drop table if exists " + PhotoContract.PSI.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
