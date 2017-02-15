package com.alfdp.ezstudio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initButtons();
    }


    private void initButtons() {

        final Button login = (Button) findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText userEdit = (EditText) findViewById(R.id.username_edtext);
                final EditText passwordEdit = (EditText) findViewById(R.id.passwd_edtext);

                String password = passwordEdit.getText().toString();
                String user = userEdit.getText().toString();

                if(TextUtils.isEmpty(password) || TextUtils.isEmpty(user)) {
                    Toast.makeText(getBaseContext(), "Please fill the fields", Toast.LENGTH_SHORT).show();
                } else if (!user.equals("user") || !password.equals("password1234")) {
                    Toast.makeText(getBaseContext(), "Login / Password couple did not match", Toast.LENGTH_SHORT).show();
                } else {
                    launchMain(user);
                }
            }
        });

    }

    private void launchMain(String user) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("UserName", user);
        intent.putExtras(bundle);
        this.startActivity(intent);
        this.finish();
    }
}
