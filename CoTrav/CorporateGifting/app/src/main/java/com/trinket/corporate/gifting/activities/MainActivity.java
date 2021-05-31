package com.trinket.corporate.gifting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.fragment.BrowseFragment;
import com.trinket.corporate.gifting.fragment.CategoriesFragment;
import com.trinket.corporate.gifting.fragment.HotFragment;
import com.trinket.corporate.gifting.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity implements BrowseFragment.navigation {
    // ViewPager viewPager;
    BrowseFragment browseFragment;
    ProfileFragment profileFragment;
    //CartFragment cartFragment;
    HotFragment hotFragment;
    CategoriesFragment categoriesFragment;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        hotFragment = new HotFragment();
        browseFragment = new BrowseFragment();
        // cartFragment = new CartFragment();
        profileFragment = new ProfileFragment();
        categoriesFragment = new CategoriesFragment();
        frameLayout = findViewById(R.id.fra_container);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        //viewPager.setCurrentItem(0);

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fra_container, browseFragment, "Home")
                                .commit();

                        break;
                    case R.id.categories:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fra_container, categoriesFragment, "Categories")
                                .commit();
                        break;
                    case R.id.hot:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fra_container, hotFragment, "Hot")
                                .commit();
                        break;

                    case R.id.profile:


                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fra_container, profileFragment, "Profile")
                                .commit();
                        break;
                }

                return false;
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fra_container, browseFragment, "Home")
                .commit();

        //setupViewPager(viewPager);
       /* BadgeDrawable badge = bottomNavigation.getOrCreateBadge(R.id.cart);
        badge.setVisible(true);
        badge.setNumber(2);*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void slide(int id) {

        if (id == 1) {
            //viewPager.setCurrentItem(1);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fra_container, categoriesFragment, "Categories")
                    .commit();

        }
        if (id == 2) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fra_container, hotFragment, "Hot")
                    .commit();

        }
        if (id == 3) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fra_container, profileFragment, "Profile")
                    .commit();

        }
    }

    private static long back_pressed;

    @Override
    public void onBackPressed() {

        {
            if (back_pressed + 1000 > System.currentTimeMillis()) {
                super.onBackPressed();
                finishAffinity();
                finish();
            } else {
                if (getSupportFragmentManager().findFragmentByTag("Home") != null
                        && getSupportFragmentManager().findFragmentByTag("Home").isVisible())
                    //  if (navigationView.getMenu().getItem(0).isChecked()) {

                    Toast.makeText(getBaseContext(), "Press once again to exit",
                            Toast.LENGTH_SHORT).show();

                else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fra_container, browseFragment, "Home")
                            .commit();

                }
            }

            //fabAddBookingMenu.close(true);
            back_pressed = System.currentTimeMillis();
        }
    }

}