package com.tvz.zavrsnirad.resistorcalculator.resisttocolors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;
import com.tvz.zavrsnirad.resistorcalculator.colorstoresist.ResistorCalculatorFragment;
import com.tvz.zavrsnirad.util.FragmentHelper;

public class ResistToColorsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View rootView = inflater.inflate(R.layout.fragment_resist_to_color, container, false);

        new ResistToColorsCalculator().calculate(rootView, this);

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentHelper.getInstance().switchFragments(new ResistorCalculatorFragment(), this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
