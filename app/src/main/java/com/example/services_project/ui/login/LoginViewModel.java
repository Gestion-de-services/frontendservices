package com.example.services_project.ui.login;

import android.content.Context;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private LoginRepository repository;

    public void init(Context context) {
        repository = new LoginRepository(context);
    }

    public boolean login(String email, String password) {
        return repository.login(email, password);
    }
}
