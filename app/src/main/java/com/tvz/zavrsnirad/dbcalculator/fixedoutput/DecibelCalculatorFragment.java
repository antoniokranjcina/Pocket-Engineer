package com.tvz.zavrsnirad.dbcalculator.fixedoutput;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.util.FragmentHelper;
import com.tvz.zavrsnirad.R;
import com.tvz.zavrsnirad.dbcalculator.variabloutput.VariableOutputPowerFragment;

public class DecibelCalculatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        View rootView = inflater.inflate(R.layout.w_output_power, container, false);

        new DbFixedOutputCalculator().calculate(rootView, this);

        return rootView;
    }

    /**
     * To avoid memory leaks
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.example_menu, menu);
        menu.findItem(R.id.settings_w_output_power).setVisible(false);
        menu.findItem(R.id.settings_resist_to_color).setVisible(false);
        menu.findItem(R.id.settings_color_to_resist).setVisible(false);
        menu.findItem(R.id.settings_resistance_calculator).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_variable_output_power:
                FragmentHelper.getInstance().switchFragments(new VariableOutputPowerFragment(), this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}