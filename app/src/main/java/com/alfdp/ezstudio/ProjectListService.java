package com.alfdp.ezstudio;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Aurelien on 13-Feb-17.
 */

public class ProjectListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ProjectListFactory(this.getApplicationContext(), intent);
    }
}
