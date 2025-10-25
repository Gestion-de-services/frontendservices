package com.example.services_project.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.services_project.R;
import com.example.services_project.ui.adapter.ServiceAdapter;

public class HomeFragment extends Fragment {

    private HomeFragmentViewModel viewModel;
    private RecyclerView recyclerView;
    private ServiceAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewServices);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        viewModel.getServices().observe(getViewLifecycleOwner(), services -> {
            adapter = new ServiceAdapter(services);
            recyclerView.setAdapter(adapter);
        });

        return root;
    }
}
