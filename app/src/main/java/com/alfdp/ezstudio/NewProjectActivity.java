package com.alfdp.ezstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfdp.ezstudio.core.Album;
import com.alfdp.ezstudio.core.DateHelper;
import com.alfdp.ezstudio.core.ProjectType;
import com.alfdp.ezstudio.core.Track;

public class NewProjectActivity extends AppCompatActivity {

    private ProjectType projectType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        setTitle(R.string.new_string);

        this.setProjectType();
        this.initButtons();
    }

    private void initButtons() {
        final Button cancel = (Button) findViewById(R.id.btn_cancelCreation);
        final Button create = (Button) findViewById(R.id.btn_createNewProject);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        create.setOnClickListener(creationListener());
    }

    private void setProjectType() {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            projectType = (ProjectType) bundle.getSerializable("projectType");
            Toast.makeText(this, projectType.toString(), Toast.LENGTH_SHORT).show();
        }

        if(ProjectType.ALBUM == projectType) {
            setTitle(getString(R.string.new_album_project));
        } else if(ProjectType.TRACK == projectType) {
            setTitle(getString(R.string.new_track_project));
        } else {
            setTitle(getString(R.string.new_project));
        }
    }

    private void createNewProject() {
        if(ProjectType.ALBUM == projectType) {
            createNewAlbum();
        } else {
            createTrack();
        }

    }

    private void createNewAlbum() {
        final EditText projectNameText = (EditText) findViewById(R.id.edit_projectName);
        final EditText projectCompositorText = (EditText) findViewById(R.id.edit_compositor);
        final EditText projectReleaseDate = (EditText) findViewById(R.id.edit_releaseDate);

        String date = new DateHelper().getActualDate();

        Album album = new Album();

        album.setName(projectNameText.getText().toString());
        album.setCompositor(projectCompositorText.getText().toString());
        album.setDate(date);

        String release = projectReleaseDate.getText().toString();

        if(null != release) {
            album.setRelease(release);
        } else {
            album.setRelease("NONE");
        }
    }

    private void createTrack() {
        final EditText projectNameText = (EditText) findViewById(R.id.edit_projectName);
        final EditText projectCompositorText = (EditText) findViewById(R.id.edit_compositor);

        String date = new DateHelper().getActualDate();

        Track track = new Track();

        track.setDate(date);
        track.setName(projectNameText.getText().toString());
        track.setCompositor(projectCompositorText.getText().toString());
    }


    private View.OnClickListener creationListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText projectNameText = (EditText) findViewById(R.id.edit_projectName);
                final EditText projectCompositorText = (EditText) findViewById(R.id.edit_compositor);

                String name = projectNameText.getText().toString();
                String compositor = projectCompositorText.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(compositor)) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    Log.i("CREATION", "FAILED");
                } else {
                    Log.i("CREATION", "SUCCESS");
                    createNewProject();
                }
            }
        };
    }

}
