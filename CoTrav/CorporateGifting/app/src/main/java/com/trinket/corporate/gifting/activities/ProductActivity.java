package com.trinket.corporate.gifting.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trinket.corporate.gifting.Converter;
import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.adapter.ProductsAdapter;
import com.trinket.corporate.gifting.model.products.Product;
import com.trinket.corporate.gifting.model.products.ProductApiResponse;
import com.trinket.corporate.gifting.viewmodel.LoginInfoViewModel;
import com.trinket.corporate.gifting.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductsAdapter.AddRemoveListener {
    Toolbar toolbar;
    ProductViewModel productViewModel;
    LoginInfoViewModel loginInfoViewModel;
    List<Product> productList;
    ProductsAdapter productsAdapter;
    RecyclerView recyclerView;
    CardView cartView;
    int page = 1;
    String categoryID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        toolbar = findViewById(R.id.topAppBar);
        cartView = findViewById(R.id.view_cart);
        toolbar.setTitle("Products");
        toolbar.setTitleTextColor(getResources().getColor(R.color.darkGrey));
        setSupportActionBar(toolbar);
        loginInfoViewModel = ViewModelProviders.of(this).get(LoginInfoViewModel.class);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        if (getIntent().getStringExtra("category_id") != null)
            categoryID = getIntent().getStringExtra("category_id");
        productList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(productList, this);
        recyclerView = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(productsAdapter);
        productViewModel.getListLiveData(loginInfoViewModel.getAccessToken().getValue(), categoryID, page).observe(this, new Observer<ProductApiResponse>() {
            @Override
            public void onChanged(ProductApiResponse productApiResponse) {
                if (productApiResponse != null)
                    if (productApiResponse.getProductList() != null && productApiResponse.getProductList().size() > 0) {
                        productList.addAll(productApiResponse.getProductList());
                    }
                productsAdapter.notifyDataSetChanged();
            }
        });

    }

    private static int cart_count = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.cart);
        menuItem.setIcon(Converter.convertLayoutToImage(this, cart_count, R.drawable.ic_outline_shopping_cart_24));
        MenuItem menuItem2 = menu.findItem(R.id.wish_list);
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void add(Product product) {

        cart_count++;
        invalidateOptionsMenu();
        cartView.setVisibility(View.VISIBLE);
    }

    @Override
    public void remove(Product product) {
        if (cart_count != 0) {
            cart_count--;
            invalidateOptionsMenu();
            if (cart_count == 0)
                cartView.setVisibility(View.GONE);

        }


    }
}