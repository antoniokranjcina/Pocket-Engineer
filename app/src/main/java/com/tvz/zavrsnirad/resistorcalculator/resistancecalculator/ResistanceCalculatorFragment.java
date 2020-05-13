package com.tvz.zavrsnirad.resistorcalculator.resistancecalculator;

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
import com.tvz.zavrsnirad.dbcalculator.fixedoutput.DecibelCalculatorFragment;
import com.tvz.zavrsnirad.resistorcalculator.colorstoresist.ResistorCalculatorFragment;
import com.tvz.zavrsnirad.util.FragmentHelper;

public class ResistanceCalculatorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View rootView = inflater.inflate(R.layout.fragment_resistance_calculator, container, false);

        new ResistanceCalculator().calculate(rootView, this);

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
