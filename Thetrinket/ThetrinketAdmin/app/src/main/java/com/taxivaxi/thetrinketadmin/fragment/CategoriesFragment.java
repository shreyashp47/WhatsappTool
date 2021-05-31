package com.taxivaxi.thetrinketadmin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taxivaxi.thetrinketadmin.R;
import com.taxivaxi.thetrinketadmin.adapter.CategoriesAdapter;
import com.taxivaxi.thetrinketadmin.model.home.CategoriesApiResponse;
import com.taxivaxi.thetrinketadmin.viewmodel.HomeViewModel;
import com.taxivaxi.thetrinketadmin.viewmodel.LoginInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements View.OnClickListener {
    View view;
    Toolbar toolbar;
    HomeViewModel homeViewModel;
    LoginInfoViewModel loginInfoViewModel;
    List<CategoriesApiResponse> categoriesList;
    CategoriesAdapter categoriesAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_categories, container, false);
        toolbar = view.findViewById(R.id.topAppBar);
        setHasOptionsMenu(false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        recyclerView = view.findViewById(R.id.recycleview);


        categoriesList=new ArrayList<>();
        categoriesAdapter=new CategoriesAdapter(categoriesList,getActivity());
        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        loginInfoViewModel = ViewModelProviders.of(getActivity()).get(LoginInfoViewModel.class);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoriesAdapter);
        homeViewModel.getListLiveData(loginInfoViewModel.getAccessToken().getValue()).observe(this, new Observer<List<CategoriesApiResponse>>() {
            @Override
            public void onChanged(List<CategoriesApiResponse> categoriesApiResponses) {
                if (categoriesApiResponses!=null&& categoriesApiResponses.size()>0){
                    categoriesList.clear();
                    categoriesList.addAll(categoriesApiResponses);
                    categoriesAdapter.notifyDataSetChanged();

                }

            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {

    }
}