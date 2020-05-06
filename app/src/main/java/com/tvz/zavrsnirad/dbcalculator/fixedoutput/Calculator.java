package com.tvz.zavrsnirad.dbcalculator.fixedoutput;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;

final class Calculator {
    static void calculatePowerDefinedOutput(final View rootView, final Fragment fragment) {
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

                switch (view.getId()) {
                    case R.id.wButton:
                        try {
                            double watts = Double.parseDouble(editTextWatts.getText().toString());
                            calculatePowerWatts(watts, editTextMilliWatts, editTextDb, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Watts Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.mwButton:
                        try {
                            double milliWatts = Double.parseDouble(editTextMilliWatts.getText().toString());
                            calculatePowerMilliWatts(milliWatts, editTextWatts, editTextDb, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Milli Watts Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dbButton:
                        try {
                            double db = Double.parseDouble(editTextDb.getText().toString());
                            calculatePowerDb(db, editTextWatts, editTextMilliWatts, editTextDbm);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "dB Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.dbmButton:
                        try {
                            double dbm = Double.parseDouble(editTextDbm.getText().toString());
                            calculatePowerDbm(dbm, editTextWatts, editTextMilliWatts, editTextDb);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "dBm Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.resetPowerButton:
                        clearScreen(editTextWatts, editTextMilliWatts, editTextDb, editTextDbm);
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
    private static void clearScreen(EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDb, EditText editTextDbm) {
        editTextWatts.setText("");
        editTextMilliWatts.setText("");
        editTextDb.setText("");
        editTextDbm.setText("");
    }

    private static void calculatePowerWatts(double watts, EditText editTextMilliWatts, EditText editTextDb, EditText editTextDbm) {
        double decimalPlaces = 100_000;

        double milliWats = Math.round(1000 * watts * decimalPlaces) / decimalPlaces;
        double db = Math.round(10 * Math.log10(watts) * decimalPlaces) / decimalPlaces;
        double dbm = Math.round(10 * Math.log10(watts / 0.001) * decimalPlaces) / decimalPlaces;

        editTextMilliWatts.setText(String.valueOf(milliWats));
        editTextDb.setText(String.valueOf(db));
        editTextDbm.setText(String.valueOf(dbm));
    }

    private static void calculatePowerMilliWatts(double milliWatts, EditText editTextWatts, EditText editTextDb, EditText editTextDbm) {
        double decimalPlaces = 100_000;

        double watts = Math.round(milliWatts / 1000 * decimalPlaces) / decimalPlaces;
        double db = Math.round(10 * Math.log10(watts) * decimalPlaces) / decimalPlaces;
        double dbm = Math.round(10 * Math.log10(watts / 0.001) * decimalPlaces) / decimalPlaces;

        editTextWatts.setText(String.valueOf(watts));
        editTextDb.setText(String.valueOf(db));
        editTextDbm.setText(String.valueOf(dbm));
    }

    private static void calculatePowerDb(double db, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDbm) {
        double decimalPlaces = 100_000;

        double watts = Math.round(Math.pow(10, db / 10) * decimalPlaces) / decimalPlaces;
        double dbm = Math.round(10 * Math.log10(watts / 0.001) * decimalPlaces) / decimalPlaces;
        double milliWats = Math.round(1000 * watts * decimalPlaces) / decimalPlaces;

        editTextWatts.setText(String.valueOf(watts));
        editTextMilliWatts.setText(String.valueOf(milliWats));
        editTextDbm.setText(String.valueOf(dbm));
    }

    private static void calculatePowerDbm(double dbm, EditText editTextWatts, EditText editTextMilliWatts, EditText editTextDb) {
        double decimalPlaces = 100_000;

        double milliWats = Math.round(Math.pow(10, dbm / 10) * decimalPlaces) / decimalPlaces;
        double watts = Math.round(milliWats * 0.001 * decimalPlaces) / decimalPlaces;
        double db = Math.round(10 * Math.log10(watts) * decimalPlaces) / decimalPlaces;

        editTextMilliWatts.setText(String.valueOf(milliWats));
        editTextDb.setText(String.valueOf(db));
        editTextWatts.setText(String.valueOf(watts));
    }
}
