package com.tvz.zavrsnirad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.tvz.zavrsnirad.anglecalculator.AngleFragment;
import com.tvz.zavrsnirad.capacitor.CapacitorCalculatorFragment;
import com.tvz.zavrsnirad.capacitor.CapacitorFragment;
import com.tvz.zavrsnirad.dbcalculator.DbCalculator;
import com.tvz.zavrsnirad.numberingsystemcalculator.NumberingSystemCalculatorFragment;
import com.tvz.zavrsnirad.numberingsystemcalculator.NumberingSystemFragment;
import com.tvz.zavrsnirad.resistorcalculator.ResistorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragments = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        buildFragmentsList();
        bottomNav.setSelectedItemId(fragments.get(0).getId());
        switchFragment(0);
    }

    private void switchFragment(int pos) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragments.get(pos))
                .commit();
    }

    private void buildFragmentsList() {
        Fragment angleCalculatorFragment = new AngleFragment();
        Fragment capacitorFragment = new CapacitorFragment();
        Fragment decibelCalculatorFragment = new DbCalculator();
        Fragment numberingSystemCalculator = new NumberingSystemFragment();
        Fragment resistorFragment = new ResistorFragment();

        fragments.add(angleCalculatorFragment);
        fragments.add(capacitorFragment);
        fragments.add(decibelCalculatorFragment);
        fragments.add(numberingSystemCalculator);
        fragments.add(resistorFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.angle_calculator:
                    selectedFragment = fragments.get(0);
                    break;
                case R.id.capacitor_calculator:
                    selectedFragment = fragments.get(1);
                    break;
                case R.id.decibel_calculator:
                    selectedFragment = fragments.get(2);
                    break;
                case R.id.numbering_system_calculator:
                    selectedFragment = fragments.get(3);
                    break;
                case R.id.resistor_calculator:
                    selectedFragment = fragments.get(4);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}
