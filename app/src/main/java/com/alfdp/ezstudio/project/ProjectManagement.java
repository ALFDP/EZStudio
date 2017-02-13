package com.alfdp.ezstudio.project;

import android.content.Context;
import android.content.SharedPreferences;

import com.alfdp.ezstudio.core.Album;
import com.alfdp.ezstudio.core.AlbumDAO;
import com.alfdp.ezstudio.core.DataBaseHelper;
import com.alfdp.ezstudio.core.Project;
import com.alfdp.ezstudio.core.Track;
import com.alfdp.ezstudio.core.TrackDAO;

/**
 * Created by maxim on 13/02/2017.
 */

public class ProjectManagement {

    private Context context;

    public ProjectManagement(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Album addAlbum(Album album) {
        AlbumDAO dao = new AlbumDAO(this.context);
        dao.open();
        dao.add(album);

        Album fromBdd = dao.get(album.getName());
        dao.close();

        return fromBdd;
    }

    public Track addTrack(Track track) {
        TrackDAO dao = new TrackDAO(this.context);
        Track trackFromBdd = null;
        dao.open();
        dao.add(track);

        trackFromBdd = dao.get(track.getName());
        dao.close();

        return trackFromBdd;
    }
}
