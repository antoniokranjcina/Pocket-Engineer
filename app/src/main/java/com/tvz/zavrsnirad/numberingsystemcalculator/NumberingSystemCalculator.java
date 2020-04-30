package com.tvz.zavrsnirad.numberingsystemcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;

public class NumberingSystemCalculator extends Fragment {
    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_numbering_system_calculator, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1,
                this.getResources().getStringArray(R.array.spinner_num_sys_calculator));

        Spinner spinnerFrom = rootView.findViewById(R.id.spinner_from_base);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);

        Spinner spinnerIn = rootView.findViewById(R.id.spinner_in_base);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIn.setAdapter(adapter);
        spinnerIn.setSelection(1);

        Calculator.calculate(rootView, this, spinnerFrom, spinnerIn);

        return rootView;
    }
}