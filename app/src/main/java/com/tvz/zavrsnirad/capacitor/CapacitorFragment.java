package com.tvz.zavrsnirad.capacitor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

        new CapacitorCalculator().calculate(rootView, this);

        return rootView;
    }

}