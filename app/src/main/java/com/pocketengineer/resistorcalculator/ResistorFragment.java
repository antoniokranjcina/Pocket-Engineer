package com.pocketengineer.resistorcalculator;

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
import com.pocketengineer.resistorcalculator.colorstoresist.ColorsToResistFragment;
import com.pocketengineer.resistorcalculator.resistancecalculator.ResistanceCalculatorFragment;
import com.pocketengineer.resistorcalculator.resisttocolors.ResistToColorsFragment;
import com.pocketengineer.util.ViewPagerAdapter;

import java.util.Objects;

public class ResistorFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.resistor_calculator_fragment, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar_resistor);

        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Resistor Calculator");


        ViewPager viewPager = rootView.findViewById(R.id.view_pager_resistor_calculator);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout_resistor_calculator);

        tabLayout.setTabTextColors(Color.parseColor("#ffffffff"), Color.parseColor("#03DAC5"));

        ColorsToResistFragment colorsToResistFragment = new ColorsToResistFragment();
        ResistanceCalculatorFragment resistanceCalculatorFragment = new ResistanceCalculatorFragment();
        ResistToColorsFragment resistToColorsFragment = new ResistToColorsFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(colorsToResistFragment, "Colors to Resist");
        viewPagerAdapter.addFragment(resistanceCalculatorFragment, "Resistance");
        viewPagerAdapter.addFragment(resistToColorsFragment, "Resist to Colors");

        viewPager.setAdapter(viewPagerAdapter);

        return rootView;
    }
}
