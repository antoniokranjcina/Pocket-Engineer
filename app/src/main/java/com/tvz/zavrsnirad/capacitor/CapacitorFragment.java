package com.tvz.zavrsnirad.capacitor;

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

public class CapacitorFragment extends Fragment {
    private static final String TAG = "CapacitorFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        View rootView =  inflater.inflate(R.layout.fragment_capacitor, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1,
                this.getResources().getStringArray(R.array.spinner_capacitor_units));

        Spinner spinnerUnitC1 = rootView.findViewById(R.id.spinner_c1_capacitor_unit);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnitC1.setAdapter(adapter);
        spinnerUnitC1.setSelection(1);

        Spinner spinnerUnitC2 = rootView.findViewById(R.id.spinner_c2_capacitor_unit);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnitC2.setAdapter(adapter);
        spinnerUnitC2.setSelection(1);

        Calculator.calculate(rootView, this, spinnerUnitC1, spinnerUnitC2);

        return rootView;
    }

}