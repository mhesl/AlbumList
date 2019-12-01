package com.example.alnumlist.database.photo;

public class PhotoContract {
    public static final String CREATE_TABLE_MODEL =
        " create table " +PSI.TABLE_NAME +
                " ( "
        + PSI.COLUMN_MODEL_ID+ " integer primary key autoincrement , "
        + PSI.COLUMN_PHOTO_ID+ " integer , "
        + PSI.COLUMN_ALBUM_ID+ " integer , "
        + PSI.COLUMN_PHOTO_TITLE+ " text , "
        + PSI.COLUMN_PHOTO_URL+ " text , "
        + PSI.COLUMN_PHOTO_THUMBNAIL+ " text , "
        +" unique ( " + PSI.COLUMN_PHOTO_ID + " ) on conflict replace "
        + " ) ";






    final static class PSI{
        public static final String TABLE_NAME = "table_photo";
        public static final String COLUMN_MODEL_ID = "_id";
        public static final String COLUMN_PHOTO_ID = "photo_id";
        public static final String COLUMN_ALBUM_ID = "album_id";
        public static final String COLUMN_PHOTO_TITLE = "photo_title";
        public static final String COLUMN_PHOTO_URL = "photo_url";
        public static final String COLUMN_PHOTO_THUMBNAIL = "thumbnail_url";
    }

}
