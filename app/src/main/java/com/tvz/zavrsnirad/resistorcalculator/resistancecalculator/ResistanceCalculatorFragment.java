package com.tvz.zavrsnirad.resistorcalculator.resistancecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;

public class ResistanceCalculatorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View rootView = inflater.inflate(R.layout.fragment_resistance_calculator, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this.getActivity(),
                android.R.layout.simple_list_item_1,
                this.getResources().getStringArray(R.array.spinner_resistor_values));

        Spinner spinnerUnitC1 = rootView.findViewById(R.id.spinner_r1_resistor_units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnitC1.setAdapter(adapter);
        spinnerUnitC1.setSelection(0);

        Spinner spinnerUnitC2 = rootView.findViewById(R.id.spinner_r2_resistor_units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnitC2.setAdapter(adapter);
        spinnerUnitC2.setSelection(0);

        Calculator.calculate(rootView, this, spinnerUnitC1, spinnerUnitC2);

        return rootView;
    }
}
