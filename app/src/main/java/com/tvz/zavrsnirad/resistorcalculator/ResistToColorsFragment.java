package com.tvz.zavrsnirad.resistorcalculator;

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

public class ResistToColorsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View rootView = inflater.inflate(R.layout.fragment_resist_to_color, container, false);

        Spinner spinner = rootView.findViewById(R.id.spinner_resistor);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1,
                this.getResources().getStringArray(R.array.spinner_resistor_values));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Calculator.calculateResistToColor(rootView, this, spinner);

        return rootView;
    }
}
