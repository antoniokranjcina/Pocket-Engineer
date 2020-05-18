package com.tvz.zavrsnirad.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tvz.zavrsnirad.R;
import com.tvz.zavrsnirad.anglecalculator.AngleCalculatorFragment;
import com.tvz.zavrsnirad.capacitor.CapacitorCalculatorFragment;
import com.tvz.zavrsnirad.dbcalculator.DbCalculator;
import com.tvz.zavrsnirad.numberingsystemcalculator.NumberingSystemCalculatorFragment;
import com.tvz.zavrsnirad.resistorcalculator.ResistorFragment;

import java.util.List;

public class FragmentHelper {

    private static FragmentHelper instance;

    private FragmentHelper() {
    }

    public static FragmentHelper getInstance() {
        if (instance == null) {
            instance = new FragmentHelper();
        }
        return instance;
    }

    public void switchFragments(Fragment fragment, Fragment currentFragment) {
        FragmentManager fragmentManager = currentFragment.getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void switchFragments(int pos, List<Fragment> fragments, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragments.get(pos)).commit();
    }

    public void buildFragmentsList(List<Fragment> fragments) {
        Fragment angleCalculatorFragment = new AngleCalculatorFragment();
        Fragment capacitorFragment = new CapacitorCalculatorFragment();
        Fragment numberingSystemCalculator = new NumberingSystemCalculatorFragment();
        Fragment decibelCalculatorFragment = new DbCalculator();
        Fragment resistorFragment = new ResistorFragment();

        fragments.add(angleCalculatorFragment);
        fragments.add(capacitorFragment);
        fragments.add(numberingSystemCalculator);
        fragments.add(decibelCalculatorFragment);
        fragments.add(resistorFragment);
    }
}