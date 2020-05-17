package com.tvz.zavrsnirad.dbcalculator.variabloutput;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tvz.zavrsnirad.R;

public class VariableOutputPowerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_variable_output_power, container, false);

        new DbVariableOutputCalculator().calculate(rootView, this);

        return rootView;
    }
}