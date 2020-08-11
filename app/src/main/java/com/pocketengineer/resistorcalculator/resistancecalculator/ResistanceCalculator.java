package com.pocketengineer.resistorcalculator.resistancecalculator;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.pocketengineer.Calculator;
import com.pocketengineer.R;
import com.pocketengineer.util.NumberFormatter;

final class ResistanceCalculator implements Calculator {

    @Override
    public void calculate(final View rootView, final Fragment fragment) {
        final EditText resistor1InputEditText = rootView.findViewById(R.id.resistor1_input_editText);
        final EditText resistor2InputEditText = rootView.findViewById(R.id.resistor2_input_editText);
        RadioGroup radioGroup = rootView.findViewById(R.id.radioGroupResistors);
        Button buttonCalculate = rootView.findViewById(R.id.resistor_calculate);
        Button buttonReset = rootView.findViewById(R.id.resistor_reset);
        final TextView result = rootView.findViewById(R.id.resistor_result);

        final Spinner spinnerUnitR1 = rootView.findViewById(R.id.spinner_r1_resistor_units);
        final Spinner spinnerUnitR2 = rootView.findViewById(R.id.spinner_r2_resistor_units);

        ArrayAdapter<String> adapter;
        if (fragment.getActivity() != null) {
            adapter = new ArrayAdapter<>(
                    fragment.getActivity(),
                    android.R.layout.simple_list_item_1,
                    fragment.getResources().getStringArray(R.array.spinner_resistor_values));

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerUnitR1.setAdapter(adapter);
            spinnerUnitR1.setSelection(0);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerUnitR2.setAdapter(adapter);
            spinnerUnitR2.setSelection(0);
        }

        final boolean[] serial = {true};

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                serial[0] = read(rootView, checkedId);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCapacity(serial[0], result, resistor1InputEditText, resistor2InputEditText, spinnerUnitR1, spinnerUnitR2, fragment);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(result, resistor1InputEditText, resistor2InputEditText);
            }
        });
    }

    private boolean read(View rootView, int id) {
        ImageView serialConnectionImageView = rootView.findViewById(R.id.resistor_serial);
        ImageView parallelConnectionImageView = rootView.findViewById(R.id.resistor_parallel);

        switch (id) {
            case R.id.resistor_serial_radioButton:
                serialConnectionImageView.setVisibility(View.VISIBLE);
                parallelConnectionImageView.setVisibility(View.INVISIBLE);
                return true;
            case R.id.resistor_parallel_radioButton:
                serialConnectionImageView.setVisibility(View.INVISIBLE);
                parallelConnectionImageView.setVisibility(View.VISIBLE);
                return false;
        }
        return false;
    }

    private void calculateCapacity(boolean serial, TextView result, EditText resistor1InputEditText, EditText resistor2InputEditText, Spinner spinnerR1, Spinner spinnerR2, Fragment fragment) {
        try {
            double r1 = Double.parseDouble(resistor1InputEditText.getText().toString());
            double r2 = Double.parseDouble(resistor2InputEditText.getText().toString());

            double formula;

            if (spinnerR1.getSelectedItem().toString().equals("Ω")) {
                r1 *= Math.pow(10, 0);
            } else if (spinnerR1.getSelectedItem().toString().equals("kΩ")) {
                r1 *= Math.pow(10, 3);
            } else if (spinnerR1.getSelectedItem().toString().equals("MΩ")) {
                r1 *= Math.pow(10, 6);
            }

            if (spinnerR2.getSelectedItem().toString().equals("Ω")) {
                r2 *= Math.pow(10, 0);
            } else if (spinnerR2.getSelectedItem().toString().equals("kΩ")) {
                r2 *= Math.pow(10, 3);
            } else if (spinnerR2.getSelectedItem().toString().equals("MΩ")) {
                r2 *= Math.pow(10, 6);
            }

            if (serial) {
                formula = r1 + r2;
            } else {
                formula = (r1 * r2) / (r1 + r2);
            }

            String str = "Ruk = " + NumberFormatter.getInstance().formatWithUnit(formula) + "Ω";

            result.setText(str);
        } catch (NumberFormatException e) {
            Toast.makeText(fragment.getActivity(), "You cannot leave R1 or R2 empty.", Toast.LENGTH_SHORT).show();
        }
    }


    private void reset(TextView result, EditText capacitor1InputEditText, EditText capacitor2InputEditText) {
        result.setText("");
        capacitor1InputEditText.setText("");
        capacitor2InputEditText.setText("");
    }
}
