package com.tvz.zavrsnirad.capacitor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
        setHasOptionsMenu(true);
        View rootView =  inflater.inflate(R.layout.fragment_capacitor, container, false);

        blankMethod();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.example_menu, menu);
        menu.findItem(R.id.settings_settings).setVisible(false);
        menu.findItem(R.id.settings_w_output_power).setVisible(false);
        menu.findItem(R.id.settings_variable_output_power).setVisible(false);
        menu.findItem(R.id.settings_color_to_resist).setVisible(false);
        menu.findItem(R.id.settings_resist_to_color).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_about:
                Toast.makeText(getActivity(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings_settings:
                Toast.makeText(getActivity(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings_history:
                Toast.makeText(getActivity(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void blankMethod() {

    }
}