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
        Cursor cursor = db.rawQuery("SELECT * FROM services", null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Service(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }
}
