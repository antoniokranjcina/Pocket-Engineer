package com.tvz.zavrsnirad.dbcalculator.fixedoutput;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.tvz.zavrsnirad.R;

public class FixedOutputPowerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fixed_output_power, container, false);

        new DbFixedOutputCalculator().calculate(rootView, this);

        return rootView;
    }
}