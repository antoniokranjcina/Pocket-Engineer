package com.pocketengineer.resistorcalculator.resisttocolors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pocketengineer.R;

public class ResistToColorsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resist_to_color, container, false);

        new ResistToColorsCalculator().calculate(rootView, this);

        return rootView;
    }
}
