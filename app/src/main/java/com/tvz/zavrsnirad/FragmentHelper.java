package com.tvz.zavrsnirad;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHelper{

    private static final FragmentHelper instance = new FragmentHelper();

    private FragmentHelper() {}

    public static FragmentHelper getInstance() {
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
