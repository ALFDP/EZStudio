package com.alfdp.ezstudio.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by maxim on 13/02/2017.
 */

public class TrackDAO extends BaseDAO {
    // Common string to all table
    final String PROJECT_KEY = "id";
    final String PROJECT_NAME = "name";
    final String PROJECT_DATE = "date";

    // String for the Track Type table
    final String TRACK_TABLE_NAME = "Album";
    final String TRACK_ALBUM_LINK = "link";
    final String TRACK_COMPOSITOR = "compositor";
    final String TRACK_TABLE_CREATE = "CREATE TABLE " +
            TRACK_TABLE_NAME + "(" +
            PROJECT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROJECT_NAME + " TEXT NOT NULL, " +
            PROJECT_DATE + " TEXT, " +
            TRACK_ALBUM_LINK + " INTEGER, " +
            TRACK_COMPOSITOR + " TEXT);";


    final String TRACK_TABLE_DROP = "DROP TABLE IF EXISTS " + TRACK_TABLE_NAME + ";";


    public TrackDAO(Context context) {
        super(context);
    }


    /**
     *
     * @param track
     */
    public void add(Track track) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AlbumDAO.PROJECT_NAME, track.getName());
        contentValues.put(AlbumDAO.ALBUM_COMPOSITOR, track.getCompositor());
        db.insert(TRACK_TABLE_NAME, null, contentValues);
    }

    /**
     *
     * @param id
     */
    public void delete(long id) {
        db.delete(TRACK_TABLE_NAME, PROJECT_KEY + " = ?", new String[] {String.valueOf(id)});
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
    public Track get(long id) {
        Cursor c = db.rawQuery("select " + PROJECT_NAME + " from " + TRACK_TABLE_NAME + " where id = ?", new String[]{String.valueOf(id)});
        Track newTrack = new Track();
        newTrack.setId(id);
        c.moveToNext();
        newTrack.setName(c.getString(0));

        c.close();

        return newTrack;
    }

    public Track get(String name) {
        Cursor c = db.rawQuery("select " + PROJECT_NAME + " from " + TRACK_TABLE_NAME + " where "+PROJECT_NAME+" = ?", new String[]{name});
        Track newTrack = new Track();
        c.moveToNext();
        newTrack.setId(c.getLong(0));
        c.close();

        return newTrack;
    }
}
