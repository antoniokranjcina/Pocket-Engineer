package com.pocketengineer.dbcalculator.variabloutput;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.pocketengineer.Calculator;
import com.pocketengineer.R;
import com.pocketengineer.util.NumberFormatter;

final class DbVariableOutputCalculator implements Calculator {
    private static void clearScreen(EditText editTextOutputPower, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDb, EditText editTextDbm) {
        editTextOutputPower.setText("");
        editTextWatts.setText("");
        editTextMilliWatts.setText("");
        editTextDb.setText("");
        editTextDbm.setText("");
        editTextDbm.setText("");
    }

    private static void calculatePowerWatts(Double outputPower, double watts, EditText editTextMilliWatts, EditText editTextDb, EditText editTextDbm) {
        if (outputPower == null) {
            return;
        }

        double milliWats = 1000 * watts;
        double db = 10 * Math.log10(watts / outputPower);
        double dbm = 10 * Math.log10(watts / (0.001 * outputPower));

        editTextMilliWatts.setText(NumberFormatter.getInstance().formatInScientificNotation(milliWats));
        editTextDb.setText(NumberFormatter.getInstance().formatInScientificNotation(db));
        editTextDbm.setText(NumberFormatter.getInstance().formatInScientificNotation(dbm));
    }

    private static void calculatePowerMilliWatts(Double outputPower, double milliWatts, EditText editTextWatts, EditText editTextDb, EditText editTextDbm) {
        if (outputPower == null) {
            return;
        }

        double watts = milliWatts / 1000;
        double db = 10 * Math.log10(watts / outputPower);
        double dbm = 10 * Math.log10(watts / (0.001 * outputPower));

        editTextWatts.setText(NumberFormatter.getInstance().formatInScientificNotation(watts));
        editTextDb.setText(NumberFormatter.getInstance().formatInScientificNotation(db));
        editTextDbm.setText(NumberFormatter.getInstance().formatInScientificNotation(dbm));
    }

    private static void calculatePowerDb(Double outputPower, double db, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDbm) {
        if (outputPower == null) {
            return;
        }

        double watts = Math.pow(10, db / (10 * outputPower));
        double dbm = 10 * Math.log10(watts / (0.001 * outputPower));
        double milliWats = 1000 * watts;

        editTextWatts.setText(NumberFormatter.getInstance().formatInScientificNotation(watts));
        editTextMilliWatts.setText(NumberFormatter.getInstance().formatInScientificNotation(milliWats));
        editTextDbm.setText(NumberFormatter.getInstance().formatInScientificNotation(dbm));
    }

    private static void calculatePowerDbm(Double outputPower, double dbm, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDb) {
        if (outputPower == null) {
            return;
        }

        double milliWats = Math.pow(10, dbm / (10 * outputPower));
        double watts = milliWats * 0.001;
        double db = 10 * Math.log10(watts / outputPower);

        editTextMilliWatts.setText(NumberFormatter.getInstance().formatInScientificNotation(milliWats));
        editTextDb.setText(NumberFormatter.getInstance().formatInScientificNotation(db));
        editTextWatts.setText(NumberFormatter.getInstance().formatInScientificNotation(watts));
    }

    private static double recalculateInWatts(String unit, String outputPower) {
        double watts;
        double outputPowerDouble = Double.parseDouble(outputPower);

        switch (unit) {
            case "W":
                watts = outputPowerDouble;
                break;
            case "mW":
                watts = outputPowerDouble * 0.001;
                break;
            case "dB":
                watts = Math.pow(10, outputPowerDouble / 10);
                break;
            case "dBm":
                watts = Math.pow(10, outputPowerDouble / 10) * 0.001;
                break;
            default:
                watts = 1;
        }
        return watts;
    }

    @Override
    public void calculate(final View rootView, final Fragment fragment) {
        Button btnConvertWatts = rootView.findViewById(R.id.wButton);
        Button btnConvertMilliWatts = rootView.findViewById(R.id.mwButton);
        Button btnConvertDb = rootView.findViewById(R.id.dbButton);
        Button btnConvertDbm = rootView.findViewById(R.id.dbmButton);
        Button btnReset = rootView.findViewById(R.id.resetPowerButton);
        final Spinner spinner = rootView.findViewById(R.id.spinner_output_power_db);

        ArrayAdapter<String> adapter;
        if (fragment.getActivity() != null) {
            adapter = new ArrayAdapter<>(fragment.getActivity(),
                    android.R.layout.simple_list_item_1,
                    fragment.getResources().getStringArray(R.array.spinner_items));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextWatts = rootView.findViewById(R.id.wattsEditText);
                EditText editTextMilliWatts = rootView.findViewById(R.id.milliWattsEditText);
                EditText editTextDb = rootView.findViewById(R.id.dbEditText);
                EditText editTextDbm = rootView.findViewById(R.id.dbmEditText);
                EditText editTextOutputPower = rootView.findViewById(R.id.outputPower);

                String spinnerValue = spinner.getSelectedItem().toString();

//                double outputPower;
//                try {
//                    outputPower = recalculateInWatts(spinnerValue, editTextOutputPower.getText().toString());
//                } catch (NumberFormatException e) {
//                    Toast.makeText(fragment.getActivity(), "Output Power cannot be empty.", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                switch (view.getId()) {
                    case R.id.wButton:
                        try {
                            double watts = Double.parseDouble(editTextWatts.getText().toString());
                            calculatePowerWatts(outputPower(fragment, spinnerValue, editTextOutputPower), watts, editTextMilliWatts, editTextDb, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Watts Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.mwButton:
                        try {
                            double milliWatts = Double.parseDouble(editTextMilliWatts.getText().toString());
                            calculatePowerMilliWatts(outputPower(fragment, spinnerValue, editTextOutputPower), milliWatts, editTextWatts, editTextDb, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Milli Watts Value cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dbButton:
                        try {
                            double db = Double.parseDouble(editTextDb.getText().toString());
                            calculatePowerDb(outputPower(fragment, spinnerValue, editTextOutputPower), db, editTextWatts, editTextMilliWatts, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "dB Value cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dbmButton:
                        try {
                            double dbm = Double.parseDouble(editTextDbm.getText().toString());
                            calculatePowerDbm(outputPower(fragment, spinnerValue, editTextOutputPower), dbm, editTextWatts, editTextMilliWatts, editTextDb);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "dBm Value cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.resetPowerButton:
                        clearScreen(editTextOutputPower, editTextWatts, editTextMilliWatts, editTextDb, editTextDbm);
                        break;
                }
            }
        };

        btnConvertWatts.setOnClickListener(listener);
        btnConvertMilliWatts.setOnClickListener(listener);
        btnConvertDb.setOnClickListener(listener);
        btnConvertDbm.setOnClickListener(listener);
        btnReset.setOnClickListener(listener);
    }

    private Double outputPower(Fragment fragment, String spinnerValue, EditText editTextOutputPower) {
        try {
            return recalculateInWatts(spinnerValue, editTextOutputPower.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(fragment.getActivity(), "Output Power cannot be empty.", Toast.LENGTH_SHORT).show();
//            return 0;
            return null;
        }
    }
}