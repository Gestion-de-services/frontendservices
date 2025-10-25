package com.example.services_project.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.services_project.R;

public class ServicesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "services_db";
    private static final int DB_VERSION = 1;

    public ServicesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE services (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "category TEXT, " +
                "title TEXT, " +
                "description TEXT, " +
                "imageResId INTEGER)");

        // Données par défaut
        db.execSQL("INSERT INTO services (category, title, description, imageResId) VALUES " +
                "('COIFFURE', 'Coupe de Cheveux', 'Coupe de cheveux avec soins du cuir chevelu', " + R.drawable.ic_haircut + "), " +
                "('PLOMBERIE', 'Réparation Évier', 'Réparation et entretien du système de plomberie pour un évier fonctionnel', " + R.drawable.ic_plumbing + "), " +
                "('MÉNAGE', 'Massage Relaxant', 'Massage relaxant pour muscles endoloris', " + R.drawable.ic_massage + "), " +
                "('ÉLECTRICIEN', 'Installation Éclairage', 'Installation d’éclairage électrique pour une belle ambiance', " + R.drawable.ic_electrician + "), " +
                "('PÉDIATRIE', 'Consulting', 'Consultation avec un pédiatre qualifié', " + R.drawable.ic_pediatrics + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS services");
        onCreate(db);
    }
}
