package com.tvz.zavrsnirad.capacitor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tvz.zavrsnirad.R;
import com.tvz.zavrsnirad.util.ViewPagerAdapter;

public class CapacitorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.capacitor_fragment, container, false);

        ViewPager viewPager = rootView.findViewById(R.id.view_pager_capacitor);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout_capacitor);

        CapacitorCalculatorFragment capacitorCalculatorFragment = new CapacitorCalculatorFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(capacitorCalculatorFragment, "Capacitor Calculator");
        viewPager.setAdapter(viewPagerAdapter);

        return rootView;
    }
}
