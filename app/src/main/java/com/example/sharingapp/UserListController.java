package com.example.sharingapp;


import android.content.Context;

import java.util.ArrayList;

public class UserListController {

    UserList userList;

    public UserListController(UserList userList) {
        this.userList = userList;
    }

    public void setUsers(ArrayList<User> users) {
        this.userList.setUsers(users);
    }
    public ArrayList<User> getUsers() {
        return userList.getUsers();
    }

    public int getIndex(User user) {
        return userList.getIndex(user);
    }

    public boolean hasUser(User user) {
        return userList.hasUser(user);
    }

    public User getUserByUsername(String username) {
        return userList.getUserByUsername(username);

    }

    public boolean isUsernameAvailable(String username) {
        return userList.isUsernameAvailable(username);
    }

    public void loadUsers(Context context) {
        userList.loadUsers(context);
    }

    public User getUser(int pos) {
        return userList.getUser(pos);
    }

    public ArrayList<String> getAllUsernames() {
        return userList.getAllUsernames();
    }

    public boolean addUser(User user, Context context) {
        AddUserCommand addUserCommand = new AddUserCommand(userList, user, context);
        addUserCommand.execute();
        return addUserCommand.isExecuted();
    }

    public boolean deleteUser(User user, Context context) {
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(userList, user, context);
        deleteUserCommand.execute();
        return deleteUserCommand.isExecuted();
    }

    public boolean editUser(User oldUser, User newUser, Context context) {
        EditUserCommand editUserCommand = new EditUserCommand(userList, oldUser, newUser, context);
        editUserCommand.execute();
        return editUserCommand.isExecuted();
    }

    public void addObserver(Observer observer) {
        userList.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        userList.removeObserver(observer);
    }
}
