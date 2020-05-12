package com.tvz.zavrsnirad.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tvz.zavrsnirad.R;

public class FragmentHelper{

    private static FragmentHelper instance;

    private FragmentHelper() {}

    public static FragmentHelper getInstance() {
        if(instance == null) {
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
}