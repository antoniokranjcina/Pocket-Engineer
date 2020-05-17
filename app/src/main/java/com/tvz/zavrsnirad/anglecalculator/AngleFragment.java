package com.tvz.zavrsnirad.anglecalculator;

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

public class AngleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.angle_fragment, container, false);

        ViewPager viewPager = rootView.findViewById(R.id.view_pager_angle);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout_angle);

        AngleCalculatorFragment angleCalculatorFragment = new AngleCalculatorFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(angleCalculatorFragment, "Angle Calculator");
        viewPager.setAdapter(viewPagerAdapter);

        return rootView;
    }
}
