package com.example.sharingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Add a new contact
 */
public class AddUserActivity extends AppCompatActivity {

    private UserList user_list = new UserList();
    private UserListController userListController = new UserListController(user_list);
    private Context context;

    private EditText username;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        context = getApplicationContext();
        userListController.loadUsers(context);
    }

    public void saveUser(View view) {

        String username_str = username.getText().toString();
        String email_str = email.getText().toString();

        if (username_str.equals("")) {
            username.setError("Empty field!");
            return;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return;
        }

        if (!userListController.isUsernameAvailable(username_str)){
            username.setError("Username already taken!");
            return;
        }

        User user = new User(username_str, email_str, null);

        boolean success = userListController.addUser(user, context);
        if (!success){
            return;
        }

        // End AddUserActivity
        finish();
    }
}
