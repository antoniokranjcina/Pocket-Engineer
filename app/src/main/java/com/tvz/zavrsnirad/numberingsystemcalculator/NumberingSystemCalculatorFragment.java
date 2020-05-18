package com.tvz.zavrsnirad.numberingsystemcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;

public class NumberingSystemCalculatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_numbering_system_calculator, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar_numbering_system_cal);
        if(toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Numbering System Calculator");
        }

        new NumberSystemCalculator().calculate(rootView, this);

        return rootView;
    }
}