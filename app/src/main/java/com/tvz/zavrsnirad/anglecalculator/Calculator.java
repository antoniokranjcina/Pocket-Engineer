package com.tvz.zavrsnirad.anglecalculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;

final class Calculator {
    private Calculator() {
    }

    static void calculateAngle(final View rootView, final Fragment fragment) {
        Button btnConvertDegree = rootView.findViewById(R.id.degreeButton);
        Button btnConvertRadian = rootView.findViewById(R.id.radianButton);
        Button btnConvertGradient = rootView.findViewById(R.id.gradientButton);
        Button btnReset = rootView.findViewById(R.id.resetAngleButton);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextDegree = rootView.findViewById(R.id.degreeEditText);
                EditText editTextRadian = rootView.findViewById(R.id.radianEditText);
                EditText editTextGradient = rootView.findViewById(R.id.gradianEditText);

                switch (view.getId()) {
                    case R.id.degreeButton:
                        try {
                            double degrees = Double.parseDouble(editTextDegree.getText().toString());
                            calculateAngleDegree(degrees, editTextRadian, editTextGradient);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Degree Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.radianButton:
                        try {
                            double radian = Double.parseDouble(editTextRadian.getText().toString());
                            calculateAngleRadian(radian, editTextDegree, editTextGradient);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Radian Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.gradientButton:
                        try {
                            double gradient = Double.parseDouble(editTextGradient.getText().toString());
                            calculateAngleGradient(gradient, editTextDegree, editTextRadian);
                        } catch (NumberFormatException e) {
                            Toast.makeText(fragment.getActivity(), "Gradient Value cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.resetAngleButton:
                        clearScreen(editTextDegree, editTextRadian, editTextGradient);
                }
            }
        };

        btnConvertDegree.setOnClickListener(listener);
        btnConvertRadian.setOnClickListener(listener);
        btnConvertGradient.setOnClickListener(listener);
        btnReset.setOnClickListener(listener);
    }

    private static void clearScreen(EditText editTextDegree, EditText editTextRadian, EditText editTextGradient) {
        editTextDegree.setText("");
        editTextRadian.setText("");
        editTextGradient.setText("");
    }

    private static void calculateAngleDegree(double degree, EditText editTextRadian, EditText editTextGradient) {
        double decimalPlaces = 100000;

        double radian = Math.round((degree * (Math.PI / 180)) * decimalPlaces) / decimalPlaces;
        double gradient = Math.round((degree * 1.1111111111111) * decimalPlaces) / decimalPlaces;

        editTextRadian.setText(String.valueOf(radian));
        editTextGradient.setText(String.valueOf(gradient));
    }

    private static void calculateAngleRadian(double radian, EditText editTextDegree, EditText editTextGradient) {
        double decimalPlaces = 100000;

        double degree = Math.round((radian * (180 / Math.PI) * decimalPlaces)) / decimalPlaces;
        double gradient = Math.round((radian * 63.661977236758) * decimalPlaces) / decimalPlaces;

        editTextDegree.setText(String.valueOf(degree));
        editTextGradient.setText(String.valueOf(gradient));
    }

    private static void calculateAngleGradient(double gradient, EditText editTextDegree, EditText editTextRadian) {
        double decimalPlaces = 100000;

        double degree = Math.round((gradient * 0.9) * decimalPlaces) / decimalPlaces;
        double radian = Math.round((gradient * 0.015707963267949) * decimalPlaces) / decimalPlaces;

        editTextDegree.setText(String.valueOf(degree));
        editTextRadian.setText(String.valueOf(radian));
    }
}
