package com.example.alnumlist.database.favorite;

import com.example.alnumlist.database.album.Contract;

public class FavoriteContract {
    public static final String CREATE_TABLE_MODEL =
            " create table " + FavoriteContract.FSI.TABLE_NAME +
                    " ( "
                    + FavoriteContract.FSI.COLUMN_MODEL_ID + " integer primary key autoincrement ,"
                    + FavoriteContract.FSI.COLUMN_ALBUM_ID + " integer , "
                    + FavoriteContract.FSI.COLUMN_ALBUM_TITLE + " text , "
                    + FavoriteContract.FSI.COLUMN_ALBUM_USERID + " integer , "
                    + " unique ( " + FavoriteContract.FSI.COLUMN_ALBUM_ID + " ) on conflict replace "
                    + " ) ";


    static final class FSI {
        public static final String TABLE_NAME = "table_favorite";
        public static final String COLUMN_MODEL_ID = "_id";
        public static final String COLUMN_ALBUM_ID = "album_id";
        public static final String COLUMN_ALBUM_TITLE = "album_title";
        public static final String COLUMN_ALBUM_USERID = "album_user_id";
    }
}
