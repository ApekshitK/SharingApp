package com.example.sharingapp;


import android.content.Context;

class DeleteUserCommand extends Command {

    private UserList userList;
    private User user;
    private Context context;

    DeleteUserCommand(UserList userList, User user, Context context) {
        this.userList = userList;
        this.user = user;
        this.context = context;
    }

    @Override
    public void execute() {
        userList.deleteUser(user);
        setIsExecuted(userList.saveUsers(context));
    }
}
