package com.example.services_project.ui.login;

import android.content.Context;
import com.example.services_project.data.DatabaseHelper;

public class LoginRepository {
    private DatabaseHelper dbHelper;

    public LoginRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean login(String email, String password) {
        return dbHelper.checkUser(email, password);
    }
}
