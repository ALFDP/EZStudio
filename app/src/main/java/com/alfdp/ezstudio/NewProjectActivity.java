package com.alfdp.ezstudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alfdp.ezstudio.core.ProjectType;

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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
}
