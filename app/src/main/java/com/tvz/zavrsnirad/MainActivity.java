package com.tvz.zavrsnirad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tvz.zavrsnirad.util.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragments = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        FragmentHelper.getInstance().buildFragmentsList(fragments);
        bottomNav.setSelectedItemId(fragments.get(0).getId());
        FragmentHelper.getInstance().switchFragments(0, fragments, getSupportFragmentManager());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int selectedFragment = 0;

            switch (item.getItemId()) {
                case R.id.angle_calculator:
                    selectedFragment = 0;
                    break;
                case R.id.capacitor_calculator:
                    selectedFragment = 1;
                    break;
                case R.id.numbering_system_calculator:
                    selectedFragment = 2;
                    break;
                case R.id.decibel_calculator:
                    selectedFragment = 3;
                    break;
                case R.id.resistor_calculator:
                    selectedFragment = 4;
                    break;
            }
            FragmentHelper.getInstance().switchFragments(selectedFragment, fragments, getSupportFragmentManager());
            return true;
        }
    };
}
