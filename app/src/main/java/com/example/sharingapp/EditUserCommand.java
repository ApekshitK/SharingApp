package com.example.sharingapp;


import android.content.Context;

class EditUserCommand extends Command {
    private UserList userList;
    private User oldUser;
    private User newUser;
    private Context context;

    EditUserCommand(UserList userList, User oldUser, User newUser, Context context) {
        this.userList = userList;
        this.oldUser = oldUser;
        this.newUser = newUser;
        this.context = context;
    }

    @Override
    public void execute() {
        userList.deleteUser(oldUser);
        userList.addUser(newUser);
        setIsExecuted(userList.saveUsers(context));
    }
}
