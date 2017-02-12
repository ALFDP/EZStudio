package com.alfdp.ezstudio.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by maxim on 04/02/2017.
 */

public abstract class BaseDAO {
    // Base Version
    protected final static int VERSION = 1;
    // Base name
    protected final static String NAME = "database.db";

    protected SQLiteDatabase db = null;
    protected DataBaseHelper dbHelper = null;

    public BaseDAO(Context context) {
        this.dbHelper = new DataBaseHelper(context, NAME, null, VERSION);
    }

    public SQLiteDatabase open() {
        db = dbHelper.getWritableDatabase();
        return db;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
