package com.tvz.zavrsnirad;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tvz.zavrsnirad.anglecalculator.AngleCalculatorFragment;
import com.tvz.zavrsnirad.capacitor.CapacitorCalculatorFragment;
import com.tvz.zavrsnirad.dbcalculator.DbCalculatorFragment;
import com.tvz.zavrsnirad.numberingsystemcalculator.NumberingSystemCalculatorFragment;
import com.tvz.zavrsnirad.resistorcalculator.ResistorFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragments = new ArrayList<>(5);
    private BottomNavigationView mBottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int selectedFragment, fragmentTag;

            switch (item.getItemId()) {
                case R.id.angle_calculator:
                    fragmentTag = R.string.fragment_angle_calculator;
                    selectedFragment = 0;
                    break;
                case R.id.capacitor_calculator:
                    fragmentTag = R.string.fragment_capacitor;
                    selectedFragment = 1;
                    break;
                case R.id.numbering_system_calculator:
                    fragmentTag = R.string.fragment_numbering_system_calculator;
                    selectedFragment = 2;
                    break;
                case R.id.decibel_calculator:
                    fragmentTag = R.string.fragment_db_calculator;
                    selectedFragment = 3;
                    break;
                case R.id.resistor_calculator:
                    fragmentTag = R.string.fragment_resistor_calculator;
                    selectedFragment = 4;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + item.getItemId());
            }
            doFragmentTransaction(fragments.get(selectedFragment), getString(fragmentTag));
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        Fragment angleCalculatorFragment = new AngleCalculatorFragment();
        Fragment capacitorCalculatorFragment = new CapacitorCalculatorFragment();
        Fragment numberingSystemCalculatorFragment = new NumberingSystemCalculatorFragment();
        Fragment dbCalculatorFragment = new DbCalculatorFragment();
        Fragment resistorFragment = new ResistorFragment();

        fragments.add(angleCalculatorFragment);
        fragments.add(capacitorCalculatorFragment);
        fragments.add(numberingSystemCalculatorFragment);
        fragments.add(dbCalculatorFragment);
        fragments.add(resistorFragment);


        mBottomNavigationView.setSelectedItemId(fragments.get(0).getId());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, angleCalculatorFragment).commit();
    }

    @Override
    public void onBackPressed() {
        int backStackCounter = getSupportFragmentManager().getBackStackEntryCount();
        MenuItem menu = mBottomNavigationView.getMenu().getItem(0);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_angle_calculator));

        if ((fragment != null && fragment.isVisible()) || backStackCounter == 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            mBottomNavigationView.setSelectedItemId(menu.getItemId());
        }
    }

    private void doFragmentTransaction(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }
}
