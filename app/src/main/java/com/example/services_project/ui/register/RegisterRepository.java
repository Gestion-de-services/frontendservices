package com.example.services_project.ui.register;

import android.content.Context;
import com.example.services_project.data.DatabaseHelper;

public class RegisterRepository {
    private DatabaseHelper dbHelper;

    public RegisterRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean register(String name, String email, String password) {
        return dbHelper.insertUser(name, email, password);
    }
}
