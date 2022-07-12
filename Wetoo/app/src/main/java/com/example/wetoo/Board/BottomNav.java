package com.example.wetoo.Board;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wetoo.Fragment.FragmentHome;
import com.example.wetoo.Fragment.FragmentProfile;
import com.example.wetoo.Fragment.FragmentSearch;
import com.example.wetoo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNav extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentHome fragmentHome;
    private FragmentProfile fragmentProfile;
    private FragmentSearch fragmentSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        fragmentManager = getSupportFragmentManager();
        fragmentHome = new FragmentHome();
        fragmentProfile = new FragmentProfile();
        fragmentSearch = new FragmentSearch();

        // transaction.replace(R.id.menu_frame_layout,fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                int id = item.getItemId();

                switch (id){
                    case R.id.HomeMenu:
                        transaction.replace(R.id.menu_frame_layout,fragmentHome).commitAllowingStateLoss();
                        break;
                    case R.id.SearchMenu:
                        transaction.replace(R.id.menu_frame_layout,fragmentSearch).commitAllowingStateLoss();
                        break;
                    case R.id.ProfileMenu:
                        transaction.replace(R.id.menu_frame_layout,fragmentProfile).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });

    }
}