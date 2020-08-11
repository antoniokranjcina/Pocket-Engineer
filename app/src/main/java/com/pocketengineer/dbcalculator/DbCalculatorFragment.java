package com.pocketengineer.dbcalculator;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pocketengineer.R;
import com.pocketengineer.dbcalculator.fixedoutput.FixedOutputPowerFragment;
import com.pocketengineer.dbcalculator.variabloutput.VariableOutputPowerFragment;
import com.pocketengineer.util.ViewPagerAdapter;

import java.util.Objects;

public class DbCalculatorFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.db_calculator_fragment, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar_decibel);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Decibel Calculator");

        final ViewPager viewPager = rootView.findViewById(R.id.view_pager_db_calculator);
        final TabLayout tabLayout = rootView.findViewById(R.id.tab_layout_db_calculator);

        tabLayout.setTabTextColors(Color.parseColor("#ffffffff"), Color.parseColor("#03DAC5"));

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
