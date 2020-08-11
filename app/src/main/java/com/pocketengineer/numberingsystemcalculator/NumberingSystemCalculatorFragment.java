package com.pocketengineer.numberingsystemcalculator;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.pocketengineer.R;

import java.util.Objects;

public class NumberingSystemCalculatorFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_numbering_system_calculator, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar_numbering_system_cal);
        if (toolbar != null) {
            ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Numbering System Calculator");
        }

        new NumberSystemCalculator().calculate(rootView, this);

        return rootView;
    }
}