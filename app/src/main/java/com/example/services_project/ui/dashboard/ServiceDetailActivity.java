package com.example.services_project.ui.dashboard;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.services_project.R;
import com.example.services_project.model.Service;

public class ServiceDetailActivity extends AppCompatActivity {

    private ImageView imageService;
    private TextView textCategory, textTitle, textDescription;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        // Récupérer les vues
        imageService = findViewById(R.id.imageServiceDetail);
        textCategory = findViewById(R.id.textCategoryDetail);
        textTitle = findViewById(R.id.textTitleDetail);
        textDescription = findViewById(R.id.textDescriptionDetail);
        applyButton = findViewById(R.id.buttonApply);

        // Récupérer le Service depuis l'Intent
        Service service = (Service) getIntent().getSerializableExtra("service");
        if (service != null) {
            imageService.setImageResource(service.getImageResId());
            textCategory.setText(service.getCategory());
            textTitle.setText(service.getTitle());
            textDescription.setText(service.getDescription());
        }

        // Bouton Postuler
        applyButton.setOnClickListener(v -> {
            Toast.makeText(this, "Vous avez postulé pour: " + service.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }
}
