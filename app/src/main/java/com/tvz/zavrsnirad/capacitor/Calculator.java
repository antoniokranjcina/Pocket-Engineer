package com.tvz.zavrsnirad.capacitor;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.NumberFormatter;
import com.tvz.zavrsnirad.R;

final class Calculator {

    static void calculate(final View rootView, final Fragment fragment, final Spinner spinnerCapacitor1, final Spinner spinnerCapacitor2) {
        final EditText capacitor1InputEditText = rootView.findViewById(R.id.capacitor1_input_editText);
        final EditText capacitor2InputEditText = rootView.findViewById(R.id.capacitor2_input_editText);
        RadioGroup radioGroup = rootView.findViewById(R.id.radioGroup);
        Button buttonCalculate = rootView.findViewById(R.id.capacitor_calculate);
        Button buttonReset = rootView.findViewById(R.id.capacitor_reset);
        final TextView result = rootView.findViewById(R.id.capacitor_result);

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
                calculateCapacity(serial[0], result, capacitor1InputEditText, capacitor2InputEditText, spinnerCapacitor1, spinnerCapacitor2, fragment);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(result, capacitor1InputEditText, capacitor2InputEditText);
            }
        });
    }

    private static boolean read(View rootView, int id) {
        ImageView serialConnectionImageView = rootView.findViewById(R.id.capacitor_serial_connection);
        ImageView parallelConnectionImageView = rootView.findViewById(R.id.capacitor_parallel_connection);

        switch (id) {
            case R.id.capacitor_serial_radioButton:
                serialConnectionImageView.setVisibility(View.VISIBLE);
                parallelConnectionImageView.setVisibility(View.INVISIBLE);
                return true;
            case R.id.capacitor_parallel_radioButton:
                serialConnectionImageView.setVisibility(View.INVISIBLE);
                parallelConnectionImageView.setVisibility(View.VISIBLE);
                return false;
        }
        return false;
    }

    private static void calculateCapacity(boolean serial, TextView result, EditText capacitor1InputEditText, EditText capacitor2InputEditText, Spinner spinnerC1, Spinner spinnerC2, Fragment fragment) {
        try {
            double c1 = Double.parseDouble(capacitor1InputEditText.getText().toString());
            double c2 = Double.parseDouble(capacitor2InputEditText.getText().toString());

            double formula;

            if (spinnerC1.getSelectedItem().toString().equals("mF")) {
                c1 *= Math.pow(10, -3);
            } else if (spinnerC1.getSelectedItem().toString().equals("μF")) {
                c1 *= Math.pow(10, -6);
            } else if (spinnerC1.getSelectedItem().toString().equals("nF")) {
                c1 *= Math.pow(10, -9);
            } else if (spinnerC1.getSelectedItem().toString().equals("pF")) {
                c1 *= Math.pow(10, -12);
            }

            if (spinnerC2.getSelectedItem().toString().equals("mF")) {
                c2 *= Math.pow(10, -3);
            } else if (spinnerC2.getSelectedItem().toString().equals("μF")) {
                c2 *= Math.pow(10, -6);
            } else if (spinnerC2.getSelectedItem().toString().equals("nF")) {
                c2 *= Math.pow(10, -9);
            } else if (spinnerC2.getSelectedItem().toString().equals("pF")) {
                c2 *= Math.pow(10, -12);
            }

            if (serial) {
                formula = (c1 * c2) / (c1 + c2);
            } else {
                formula = c1 + c2;
            }

            String str = NumberFormatter.format(formula) + "F";

            result.setText(str);
        } catch (NumberFormatException e) {
            Toast.makeText(fragment.getActivity(), "You cannot leave C1 or C2 empty.", Toast.LENGTH_SHORT).show();
        }
    }

    private static void reset(TextView result, EditText capacitor1InputEditText, EditText capacitor2InputEditText) {
        result.setText("");
        capacitor1InputEditText.setText("");
        capacitor2InputEditText.setText("");
    }
}