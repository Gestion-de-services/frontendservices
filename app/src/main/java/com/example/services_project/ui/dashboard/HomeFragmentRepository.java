package com.example.services_project.ui.dashboard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.services_project.data.ServicesDatabaseHelper;
import com.example.services_project.model.Service;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentRepository {
    private ServicesDatabaseHelper dbHelper;

    public HomeFragmentRepository(Context context) {
        dbHelper = new ServicesDatabaseHelper(context);
    }

    public List<Service> getAllServices() {
        List<Service> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // ✅ Récupération de toutes les colonnes
        Cursor cursor = db.rawQuery(
                "SELECT id, category, title, description, imageResId, location, price, moreDetails FROM services",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                list.add(new Service(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("category")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("imageResId")),
                        cursor.getString(cursor.getColumnIndexOrThrow("location")),
                        cursor.getString(cursor.getColumnIndexOrThrow("price")),
                        cursor.getString(cursor.getColumnIndexOrThrow("moreDetails"))
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }
}
