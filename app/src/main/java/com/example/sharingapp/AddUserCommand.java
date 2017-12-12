package com.example.sharingapp;


import android.content.Context;

class AddUserCommand extends Command {

    private UserList userList;
    private User user;
    private Context context;

    AddUserCommand(UserList userList, User user, Context context) {
        this.userList = userList;
        this.user = user;
        this.context = context;
    }

    @Override
    public void execute() {
        userList.addUser(user);
        setIsExecuted(userList.saveUsers(context));
    }
}
