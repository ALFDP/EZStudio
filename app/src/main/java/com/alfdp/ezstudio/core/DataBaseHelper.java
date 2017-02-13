package com.alfdp.ezstudio.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maxim on 04/02/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // Common string to all table
    public static final String PROJECT_KEY = "id";
    public static final String PROJECT_NAME = "name";
    public static final String PROJECT_DATE = "date";

    // String for the Album Type table
    public static final String ALBUM_TABLE_NAME = "Album";
    public static final String ALBUM_COMPOSITOR = "compositor";
    public static final String ALBUM_RELEASE_DATE = "release";
    public static final String ALBUM_TABLE_CREATE = "CREATE TABLE " +
                    ALBUM_TABLE_NAME + "(" +
                    PROJECT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PROJECT_NAME + " TEXT NOT NULL, " +
                    PROJECT_DATE + " TEXT, " +
                    ALBUM_COMPOSITOR + " TEXT, " +
                    ALBUM_RELEASE_DATE + " TEXT);";

    // String for the Track Type table
    public static final String TRACK_TABLE_NAME = "Track";
    public static final String TRACK_ALBUM_LINK = "link";
    public static final String TRACK_COMPOSITOR = "compositor";
    public static final String TRACK_TABLE_CREATE = "CREATE TABLE " +
            TRACK_TABLE_NAME + "(" +
            PROJECT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROJECT_NAME + " TEXT NOT NULL, " +
            PROJECT_DATE + " TEXT, " +
            TRACK_ALBUM_LINK + " INTEGER, " +
            TRACK_COMPOSITOR + " TEXT);";

    // Upgrade db const
    public static final String ALBUM_TABLE_DROP = "DROP TABLE IF EXISTS " + ALBUM_TABLE_NAME + ";";
    public static final String TRACK_TABLE_DROP = "DROP TABLE IF EXISTS " + TRACK_TABLE_NAME + ";";



    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ALBUM_TABLE_CREATE);
        db.execSQL(TRACK_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ALBUM_TABLE_DROP);
        db.execSQL(TRACK_TABLE_DROP);
        onCreate(db);
    }
}
