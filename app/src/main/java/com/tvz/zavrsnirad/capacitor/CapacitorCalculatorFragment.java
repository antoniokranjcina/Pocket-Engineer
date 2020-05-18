package com.tvz.zavrsnirad.capacitor;

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

public class CapacitorCalculatorFragment extends Fragment {
    private static final String TAG = "CapacitorFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_capacitor_calcualtor, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar_capacitor);
        if(toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Capacitor Calculator");
        }

        new CapacitorCalculator().calculate(rootView, this);

        return rootView;
    }

}