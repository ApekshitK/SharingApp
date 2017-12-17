package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Displays a list of all contacts
 * Note: You will not be able edit/delete contacts which are "active" borrowers
 */
public class ContactsActivity extends AppCompatActivity implements Observer {

    private UserList user_list = new UserList();
    private UserListController user_list_controller = new UserListController(user_list);

    private UserList active_borrowers_list = new UserList();
    private UserListController active_borrowers_list_controller = new UserListController(active_borrowers_list);

    private ItemList item_list = new ItemList();
    private ItemListController item_list_controller = new ItemListController(item_list);

    private ListView my_contacts;
    private ArrayAdapter<User> adapter;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        context = getApplicationContext();

        user_list_controller.addObserver(this);
        user_list_controller.loadUsers(context);
        item_list_controller.loadItems(context);

        // When contact is long clicked, this starts EditUserActivity
        my_contacts.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {

                User user = adapter.getItem(pos);

                // Do not allow an "active" borrower to be edited
                active_borrowers_list_controller.setUsers(item_list_controller.getActiveBorrowers());
                if (active_borrowers_list_controller != null) {
                    if (active_borrowers_list_controller.hasUser(user)) {
                        CharSequence text = "Cannot edit or delete active borrower!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                        return true;
                    }
                }

                user_list_controller.loadUsers(context); // must load contacts again here
                int meta_pos = user_list_controller.getIndex(user);

                Intent intent = new Intent(context, EditUserActivity.class);
                intent.putExtra("position", meta_pos);
                startActivity(intent);

                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        context = getApplicationContext();
        user_list_controller.loadUsers(context);
    }

    public void addUserActivity(View view){
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the activity is destroyed, thus we remove this activity as a listener
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        user_list_controller.removeObserver(this);
    }

    /**
     * Update the view
     */
    public void update(){
        my_contacts = (ListView) findViewById(R.id.my_contacts);
        adapter = new UserAdapter(ContactsActivity.this, user_list_controller.getUsers());
        my_contacts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
