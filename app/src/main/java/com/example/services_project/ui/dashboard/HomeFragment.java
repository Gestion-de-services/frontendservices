package com.example.services_project.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.services_project.R;
import com.example.services_project.model.Service;
import com.example.services_project.ui.adapter.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeFragmentViewModel viewModel;
    private RecyclerView recyclerView;
    private ServiceAdapter adapter;
    private EditText searchBar;
    private ImageView filterIcon;

    private List<Service> allServices = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewServices);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchBar = root.findViewById(R.id.searchBar);
        filterIcon = root.findViewById(R.id.filterIcon);

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        // Charger les services depuis la base
        viewModel.getServices().observe(getViewLifecycleOwner(), services -> {
            allServices.clear();
            allServices.addAll(services);
            // 🔹 Correction : passer le context au constructeur
            adapter = new ServiceAdapter(requireContext(), allServices);
            recyclerView.setAdapter(adapter);
        });

        // 🔍 Recherche par mot-clé
        searchBar.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterServicesByText(s.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        // 🧩 Filtrage par catégorie
        filterIcon.setOnClickListener(v -> showFilterDialog());

        return root;
    }

    // 🔹 Filtrer par texte
    private void filterServicesByText(String query) {
        List<Service> filteredList = new ArrayList<>();
        for (Service s : allServices) {
            if (s.getTitle().toLowerCase().contains(query.toLowerCase())
                    || s.getCategory().toLowerCase().contains(query.toLowerCase())
                    || s.getDescription().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(s);
            }
        }
        adapter.updateList(filteredList);
    }

    // 🔹 Afficher la boîte de dialogue pour choisir une catégorie
    private void showFilterDialog() {
        String[] categories = {"TOUS", "COIFFURE", "PLOMBERIE", "MÉNAGE", "ÉLECTRICIEN", "PÉDIATRIE"};

        new AlertDialog.Builder(requireContext())
                .setTitle("Filtrer par catégorie")
                .setItems(categories, (dialog, which) -> {
                    String selectedCategory = categories[which];
                    if (selectedCategory.equals("TOUS")) {
                        adapter.updateList(allServices);
                    } else {
                        List<Service> filtered = new ArrayList<>();
                        for (Service s : allServices) {
                            if (s.getCategory().equalsIgnoreCase(selectedCategory)) {
                                filtered.add(s);
                            }
                        }
                        adapter.updateList(filtered);
                    }
                })
                .show();
    }
}
