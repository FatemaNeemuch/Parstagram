package com.codepath.parstagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codepath.parstagram.fragments.ComposeFragment;
import com.codepath.parstagram.fragments.PostsFragment;
import com.codepath.parstagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    //class constant
    public static final String TAG = "MainActivity";

    //instance variables
    private BottomNavigationView bottomNavigation;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference to views
        bottomNavigation = findViewById(R.id.bottomNavigation);

        //tabs
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                //dictates which fragment to launch depending on which menu item clicked
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = new PostsFragment();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }
                //shows the fragment clicked
                fragmentManager.beginTransaction().replace(R.id.flcontainer, fragment).commit();
                return true;
            }
        });

        // Set default selection
        bottomNavigation.setSelectedItemId(R.id.action_home);
    }
}