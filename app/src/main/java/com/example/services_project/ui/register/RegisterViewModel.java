package com.example.services_project.ui.register;

import android.content.Context;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    private RegisterRepository repository;

    public void init(Context context) {
        repository = new RegisterRepository(context);
    }

    public boolean register(String name, String email, String password) {
        return repository.register(name, email, password);
    }
}
