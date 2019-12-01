package com.example.alnumlist.database.album;

public class Contract {
    public static final String CREATE_TABLE_MODEL =
            " create table " + PTF.TABLE_NAME +
                    " ( "
                    + PTF.COLUMN_MODEL_ID + " integer primary key autoincrement ,"
                    + PTF.COLUMN_ALBUM_ID + " integer , "
                    + PTF.COLUMN_ALBUM_TITLE + " text , "
                    + PTF.COLUMN_ALBUM_USERID + " integer , "
                    + " unique ( " + PTF.COLUMN_ALBUM_ID + " ) on conflict replace "
                    + " ) ";


    static final class PTF {
        public static final String TABLE_NAME = "table_model";
        public static final String COLUMN_MODEL_ID = "_id";
        public static final String COLUMN_ALBUM_ID = "album_id";
        public static final String COLUMN_ALBUM_TITLE = "album_title";
        public static final String COLUMN_ALBUM_USERID = "album_user_id";

    }
}
