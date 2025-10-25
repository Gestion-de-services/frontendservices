package com.example.services_project.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.services_project.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword;
    private Button btnRegister;
    private RegisterViewModel viewModel = new RegisterViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);

        viewModel.init(this);

        btnRegister.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            if (viewModel.register(name, email, password))
                Toast.makeText(this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Erreur : email déjà utilisé", Toast.LENGTH_SHORT).show();
        });
    }
}
