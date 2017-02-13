package com.alfdp.ezstudio.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by maxim on 11/02/2017.
 */

public class AlbumDAO extends BaseDAO {

    public static final String ALBUM_TABLE_NAME = "Album";
    public static final String ALBUM_COMPOSITOR = "compositor";
    public static final String ALBUM_RELEASE_DATE = "release";
    public static final String PROJECT_KEY = "id";
    public static final String PROJECT_NAME = "name";
    public static final String PROJECT_DATE = "date";

    public AlbumDAO(Context context) {
        super(context);
    }

    public static final String ALBUM_TABLE_CREATE = "CREATE TABLE " +
            ALBUM_TABLE_NAME + "(" +
            PROJECT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROJECT_NAME + " TEXT NOT NULL, " +
            PROJECT_DATE + " TEXT, " +
            ALBUM_COMPOSITOR + " TEXT, " +
            ALBUM_RELEASE_DATE + " TEXT);";

    public static final String ALBUM_TABLE_DROP = "DROP TABLE IF EXISTS " + ALBUM_TABLE_NAME + ";";


    /**
     *
     * @param album
     */
    public void add(Album album) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AlbumDAO.PROJECT_NAME, album.getName());
        contentValues.put(AlbumDAO.ALBUM_COMPOSITOR, album.getCompositor());
        long insert = db.insert(ALBUM_TABLE_NAME, null, contentValues);

        Log.e("INSERT", String.valueOf(insert));


    }

    /**
     *
     * @param id
     */
    public void delete(long id) {
        db.delete(ALBUM_TABLE_NAME, PROJECT_KEY + " = ?", new String[] {String.valueOf(id)});
    }

    /**
     *
     * @param album
     */
    public void update(Album album) {

    }

    /**
     *
     * @param id
     * @return
     */
    public Album get(long id) {
        Cursor c = db.rawQuery("select * from " + ALBUM_TABLE_NAME + " where id = ?", new String[]{String.valueOf(id)});
        Album newAlbum = new Album();
        c.moveToFirst();
        String name = c.getString(1);
        String compositor = c.getString(3);
        newAlbum.setId(id);
        newAlbum.setName(name);
        newAlbum.setCompositor(compositor);

        c.close();

        return newAlbum;
    }

    public Album get(String name) {
        Cursor c = db.rawQuery("select * from " + ALBUM_TABLE_NAME + " where "+PROJECT_NAME+" = ?", new String[]{name});
        Album newAlbum = new Album();
        long id = c.getLong(0);
        String compositor = c.getString(3);
        c.moveToFirst();
        newAlbum.setId(id);
        newAlbum.setName(name);
        newAlbum.setCompositor(compositor);
        c.close();

        return newAlbum;
    }

}
