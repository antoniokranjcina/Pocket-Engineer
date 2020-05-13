package com.tvz.zavrsnirad.dbcalculator.variabloutput;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tvz.zavrsnirad.R;
import com.tvz.zavrsnirad.dbcalculator.fixedoutput.DecibelCalculatorFragment;
import com.tvz.zavrsnirad.util.FragmentHelper;

public class VariableOutputPowerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.variable_output_power, container, false);

        new DbVariableOutputCalculator().calculate(rootView, this);

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentHelper.getInstance().switchFragments(new DecibelCalculatorFragment(), this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}