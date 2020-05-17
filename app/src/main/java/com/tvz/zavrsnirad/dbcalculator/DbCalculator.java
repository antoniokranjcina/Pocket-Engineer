package com.tvz.zavrsnirad.dbcalculator;

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
import com.tvz.zavrsnirad.dbcalculator.fixedoutput.FixedOutputPowerFragment;
import com.tvz.zavrsnirad.dbcalculator.variabloutput.VariableOutputPowerFragment;
import com.tvz.zavrsnirad.util.ViewPagerAdapter;

public class DbCalculator extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.db_calculator_fragment, container, false);

        ViewPager viewPager = rootView.findViewById(R.id.view_pager_db_calculator);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout_db_calculator);

        FixedOutputPowerFragment fixedOutputPowerFragment = new FixedOutputPowerFragment();
        VariableOutputPowerFragment variableOutputPowerFragment = new VariableOutputPowerFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(fixedOutputPowerFragment, "Fixed output");
        viewPagerAdapter.addFragment(variableOutputPowerFragment, "Variable output");
        viewPager.setAdapter(viewPagerAdapter);

        return rootView;
    }
}
