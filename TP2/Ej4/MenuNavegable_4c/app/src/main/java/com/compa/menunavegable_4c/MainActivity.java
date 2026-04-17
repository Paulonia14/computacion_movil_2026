package com.compa.menunavegable_4c;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.compa.menunavegable_4c.ui.gallery.GalleryFragment;
import com.compa.menunavegable_4c.ui.home.HomeFragment;
import com.compa.menunavegable_4c.ui.slideshow.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new HomeFragment())
                .commit();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new HomeFragment())
                        .commit();

                setTitle("Home");

            } else if (item.getItemId() == R.id.nav_gallery) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new GalleryFragment())
                        .commit();

                setTitle("Gallery");

            } else if (item.getItemId() == R.id.nav_slideshow) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new SlideshowFragment())
                        .commit();

                setTitle("Slideshow");
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }
}