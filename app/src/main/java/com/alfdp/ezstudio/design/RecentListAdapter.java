package com.alfdp.ezstudio.design;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfdp.ezstudio.R;
import com.alfdp.ezstudio.core.Album;
import com.alfdp.ezstudio.core.Project;
import com.alfdp.ezstudio.core.Track;

/**
 * Created by maxim on 11/02/2017.
 */

public class RecentListAdapter extends ArrayAdapter<Project> {

    public RecentListAdapter(Context context, Project[] projects) {
        super(context, R.layout.custom_recent_row, projects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_recent_row, parent, false);

        Project singleItem = getItem(position);

        TextView title = (TextView) customView.findViewById(R.id.item_projectName);
        TextView detail = (TextView) customView.findViewById(R.id.item_projectDetail);
        ImageView imageView = (ImageView) customView.findViewById(R.id.item_image);

        title.setText(singleItem.getName());

        if(singleItem instanceof Album){
            detail.setText(((Album) singleItem).getCompositor());
            imageView.setImageResource(R.drawable.ic_album);
        } else {
            detail.setText(((Track) singleItem).getCompositor());
            imageView.setImageResource(R.drawable.ic_track);
        }

        return customView;
    }
}
