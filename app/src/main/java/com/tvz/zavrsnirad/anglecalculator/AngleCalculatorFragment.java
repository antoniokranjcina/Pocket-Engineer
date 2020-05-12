package com.tvz.zavrsnirad.anglecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;

public class AngleCalculatorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        View rootView = inflater.inflate(R.layout.fragment_angle_calculator, container, false);

        new AngleCalculator().calculate(rootView, this);

        return rootView;
    }
}