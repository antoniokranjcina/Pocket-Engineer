package com.tvz.zavrsnirad.resistorcalculator;

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

import com.tvz.zavrsnirad.FragmentHelper;
import com.tvz.zavrsnirad.R;

public class ResistorCalculatorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        View rootView = inflater.inflate(R.layout.fragment_resistor_calculator, container, false);

        Calculator.calculateColorsToResist(rootView, this);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.example_menu, menu);
        menu.findItem(R.id.settings_about).setVisible(false);
        menu.findItem(R.id.settings_history).setVisible(false);
        menu.findItem(R.id.settings_settings).setVisible(false);
        menu.findItem(R.id.settings_w_output_power).setVisible(false);
        menu.findItem(R.id.settings_variable_output_power).setVisible(false);
        menu.findItem(R.id.settings_color_to_resist).setVisible(false);

        menu.findItem(R.id.settings_resist_to_color).setVisible(true);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_resist_to_color:
                FragmentHelper.getInstance().switchFragments(new ResistToColorsFragment(), this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
