package com.tvz.zavrsnirad.dbcalculator.variabloutput;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.NumberFormatter;
import com.tvz.zavrsnirad.R;

final class Calculator {
    private Calculator() { }


    static void calculatePowerVariableOutput(final View rootView, final Fragment fragment, final Spinner spinner) {
        Button btnConvertWatts = rootView.findViewById(R.id.wButton);
        Button btnConvertMilliWatts = rootView.findViewById(R.id.mwButton);
        Button btnConvertDb = rootView.findViewById(R.id.dbButton);
        Button btnConvertDbm = rootView.findViewById(R.id.dbmButton);
        Button btnReset = rootView.findViewById(R.id.resetPowerButton);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextWatts = rootView.findViewById(R.id.wattsEditText);
                EditText editTextMilliWatts = rootView.findViewById(R.id.milliWattsEditText);
                EditText editTextDb = rootView.findViewById(R.id.dbEditText);
                EditText editTextDbm = rootView.findViewById(R.id.dbmEditText);
                EditText editTextOutputPower = rootView.findViewById(R.id.outputPower);

                String spinnerValue = spinner.getSelectedItem().toString();

                double outputPower;
                try {
                    outputPower = recalculateInWatts(spinnerValue, editTextOutputPower.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(fragment.getActivity(), "Output Power cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                switch (view.getId()) {
                    case R.id.wButton:
                        try {
                            double watts = Double.parseDouble(editTextWatts.getText().toString());
                            calculatePowerWatts(outputPower, watts, editTextMilliWatts, editTextDb, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Watts Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.mwButton:
                        try {
                            double milliWatts = Double.parseDouble(editTextMilliWatts.getText().toString());
                            calculatePowerMilliWatts(outputPower, milliWatts, editTextWatts, editTextDb, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Milli Watts Value cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dbButton:
                        try {
                            double db = Double.parseDouble(editTextDb.getText().toString());
                            calculatePowerDb(outputPower, db, editTextWatts, editTextMilliWatts, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "dB Value cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dbmButton:
                        try {
                            double dbm = Double.parseDouble(editTextDbm.getText().toString());
                            calculatePowerDbm(outputPower, dbm, editTextWatts, editTextMilliWatts, editTextDb);
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

    private static void clearScreen(EditText editTextOutputPower, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDb, EditText editTextDbm) {
        editTextOutputPower.setText("");
        editTextWatts.setText("");
        editTextMilliWatts.setText("");
        editTextDb.setText("");
        editTextDbm.setText("");
        editTextDbm.setText("");
    }

    private static void calculatePowerWatts(double outputPower, double watts, EditText editTextMilliWatts, EditText editTextDb, EditText editTextDbm) {
        double milliWats = 1000 * watts;
        double db = 10 * Math.log10(watts / outputPower);
        double dbm = 10 * Math.log10(watts / (0.001 * outputPower));

        editTextMilliWatts.setText(NumberFormatter.formatWithoutUnit(milliWats));
        editTextDb.setText(NumberFormatter.formatWithoutUnit(db));
        editTextDbm.setText(NumberFormatter.formatWithoutUnit(dbm));
    }

    private static void calculatePowerMilliWatts(double outputPower, double milliWatts, EditText editTextWatts, EditText editTextDb, EditText editTextDbm) {
        double watts = milliWatts / 1000;
        double db = 10 * Math.log10(watts / outputPower);
        double dbm = 10 * Math.log10(watts / (0.001 * outputPower));

        editTextWatts.setText(NumberFormatter.formatWithoutUnit(watts));
        editTextDb.setText(NumberFormatter.formatWithoutUnit(db));
        editTextDbm.setText(NumberFormatter.formatWithoutUnit(dbm));
    }

    private static void calculatePowerDb(double outputPower, double db, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDbm) {
        double watts = Math.pow(10, db / (10 * outputPower));
        double dbm = 10 * Math.log10(watts / (0.001 * outputPower));
        double milliWats = 1000 * watts;

        editTextWatts.setText(NumberFormatter.formatWithoutUnit(watts));
        editTextMilliWatts.setText(NumberFormatter.formatWithoutUnit(milliWats));
        editTextDbm.setText(NumberFormatter.formatWithoutUnit(dbm));
    }

    private static void calculatePowerDbm(double outputPower, double dbm, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDb) {
        double milliWats = Math.pow(10, dbm / (10 * outputPower));
        double watts = milliWats * 0.001;
        double db = 10 * Math.log10(watts / outputPower);

        editTextMilliWatts.setText(NumberFormatter.formatWithoutUnit(milliWats));
        editTextDb.setText(NumberFormatter.formatWithoutUnit(db));
        editTextWatts.setText(NumberFormatter.formatWithoutUnit(watts));
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
}