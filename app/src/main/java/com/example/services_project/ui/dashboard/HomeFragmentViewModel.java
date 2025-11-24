package com.example.services_project.ui.dashboard;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.services_project.model.Service;
import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Service>> services;
    private HomeFragmentRepository repository;

    public HomeFragmentViewModel(Application application) {
        super(application);
        repository = new HomeFragmentRepository(application);
        services = new MutableLiveData<>();
        loadServices();
    }

    private void loadServices() {
        // Charger depuis la base
        services.setValue(repository.getAllServices());
    }

    public LiveData<List<Service>> getServices() {
        return services;
    }

    // ----------------- AJOUTER UN SERVICE -----------------
    public void insertService(Service service) {
        repository.insertService(service);        // Ajouter dans la DB
        loadServices();                            // Recharger la liste pour notifier l'observer
    }
}
