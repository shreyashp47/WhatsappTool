package com.trinket.corporate.gifting.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.trinket.corporate.gifting.Converter;
import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.activities.ProductActivity;
import com.trinket.corporate.gifting.adapter.SliderAdapterExample;
import com.trinket.corporate.gifting.model.SliderItem;
import com.trinket.corporate.gifting.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class BrowseFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    View view;
    MenuItem prevMenuItem;

    DrawerLayout drawer;
    NavigationView navigationView;
    navigation navi;
    SliderView sliderView;
    SliderAdapterExample adapter;
    private static int cart_count=0;
    TextView showAll;

    ViewFlipper viewFlipper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_home, container, false);


        setHasOptionsMenu(true);

        toolbar = view.findViewById(R.id.topAppBar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        navigationView = view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        drawer = view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(
                getActivity(),
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        sliderView = view.findViewById(R.id.image_slider);


        adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });


        showAll=view.findViewById(R.id.show_all);
        showAll.setOnClickListener(this::showAll);


        int gallery_grid_Images[]={R.drawable.trinket,R.drawable.trinket_main_logo
        };

        viewFlipper=view.findViewById(R.id.slider);
        for(int i=0;i<gallery_grid_Images.length;i++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(gallery_grid_Images[i]);
        }

        viewFlipper.setAutoStart(true);
        return view;
    }
    private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res+"");
        ImageView image = new ImageView(getContext().getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }
    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://images.unsplash.com/photo-1500771471050-fb3ee40b66c2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
            } else {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        adapter.addItem(sliderItem);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {

            navigationView.getMenu().getItem(0).setChecked(true);
        }
        if (id == R.id.hot) {
            navi.slide(2);
            navigationView.getMenu().getItem(1).setChecked(true);
        }
        if (id == R.id.categories) {
            navi.slide(2);
            navigationView.getMenu().getItem(2).setChecked(true);
        }
        if (id == R.id.profile) {
            navi.slide(3);
            navigationView.getMenu().getItem(3).setChecked(true);
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.top_menu, menu);
        MenuItem menuWishList = menu.findItem(R.id.wish_list);
        MenuItem menuCart = menu.findItem(R.id.cart);
       // menuCart
        menuCart.setIcon(Converter.convertLayoutToImage(getActivity(),cart_count,R.drawable.ic_outline_shopping_cart_24));

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.wish_list:
                Toast.makeText(getActivity(), "Action wish list selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart:
                add();
                break;
            default:
                break;
        }
        return true;
    }

    public interface navigation {
        void slide(int id);

    }

    @Override
    public void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navi = (navigation) context;
    }
    void add(){
        cart_count++;
        getActivity().invalidateOptionsMenu();
    }


    void showAll(View view){
        Intent intent=new Intent(getActivity(), ProductActivity.class);
        startActivity(intent);

    }

}