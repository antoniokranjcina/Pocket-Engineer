package com.pocketengineer.resistorcalculator.resisttocolors;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.pocketengineer.Calculator;
import com.pocketengineer.R;

final class ResistToColorsCalculator implements Calculator {

    private static void calculateColors(View rootView, Fragment fragment, EditText resist, Spinner spinner) {
        double resistNumber;
        try {
            resistNumber = Double.parseDouble(resist.getText().toString());

            if (spinner.getSelectedItem().toString().equals("Ω")) {
                if (resistNumber >= 0.1 && resistNumber <= 0.9) {
                    resistNumber = resistNumber * 100;
                    setColorDigits(resistNumber, 10, fragment, rootView);
                } else if (resistNumber >= 1.0 && resistNumber <= 9.9) {
                    resistNumber *= 10;
                    setColorDigits(resistNumber, 11, fragment, rootView);
                } else if (resistNumber >= 10 && resistNumber <= 99) {
                    setColorDigits(resistNumber, 0, fragment, rootView);
                } else if (resistNumber >= 100 && resistNumber <= 990) {
                    resistNumber /= 10;
                    setColorDigits(resistNumber, 1, fragment, rootView);
                } else if (resistNumber > 1_000 && resistNumber < 9_900) {
                    resistNumber /= 100;
                    setColorDigits(resistNumber, 2, fragment, rootView);
                } else if (resistNumber >= 10_000 && resistNumber <= 99_000) {
                    resistNumber /= 1_000;
                    setColorDigits(resistNumber, 3, fragment, rootView);
                } else if (resistNumber >= 100_000 && resistNumber <= 990_000) {
                    resistNumber /= 10_000;
                    setColorDigits(resistNumber, 4, fragment, rootView);
                } else if (resistNumber >= 1_000_000 && resistNumber <= 9_900_000) {
                    resistNumber /= 100_000;
                    setColorDigits(resistNumber, 5, fragment, rootView);
                } else if (resistNumber >= 10_000_000 && resistNumber <= 99_000_000) {
                    resistNumber /= 1_000_000;
                    setColorDigits(resistNumber, 6, fragment, rootView);
                } else if (resistNumber >= 100_000_000 && resistNumber <= 990_000_000) {
                    resistNumber /= 10_000_000;
                    setColorDigits(resistNumber, 7, fragment, rootView);
                } else {
                    Toast.makeText(fragment.getActivity(), "Invalid input.", Toast.LENGTH_SHORT).show();
                }
            } else if (spinner.getSelectedItem().toString().equals("kΩ")) {
                if (resistNumber >= 0.1 && resistNumber <= 0.9) {
                    resistNumber = resistNumber * 100;
                    setColorDigits(resistNumber, 1, fragment, rootView);
                } else if (resistNumber >= 1.0 && resistNumber <= 9.9) {
                    resistNumber *= 10;
                    setColorDigits(resistNumber, 2, fragment, rootView);
                } else if (resistNumber >= 10 && resistNumber <= 99) {
                    setColorDigits(resistNumber, 3, fragment, rootView);
                } else if (resistNumber >= 100 && resistNumber <= 990) {
                    resistNumber /= 10;
                    setColorDigits(resistNumber, 4, fragment, rootView);
                } else {
                    Toast.makeText(fragment.getActivity(), "Invalid input.", Toast.LENGTH_SHORT).show();
                }
            } else if (spinner.getSelectedItem().toString().equals("MΩ")) {
                if (resistNumber >= 0.1 && resistNumber <= 0.9) {
                    resistNumber *= 100;
                    setColorDigits(resistNumber, 4, fragment, rootView);
                } else if (resistNumber >= 1 && resistNumber <= 9.9) {
                    resistNumber *= 10;
                    setColorDigits(resistNumber, 5, fragment, rootView);
                } else if (resistNumber >= 10 && resistNumber <= 99) {
                    setColorDigits(resistNumber, 6, fragment, rootView);
                } else if (resistNumber >= 100 && resistNumber <= 990) {
                    resistNumber /= 10;
                    setColorDigits(resistNumber, 7, fragment, rootView);
                } else {
                    Toast.makeText(fragment.getActivity(), "Invalid input.", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(fragment.getActivity(), "Resist Value cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }

    private static void setColors(Fragment fragment, int firstDigit, TextView color) {
        switch (firstDigit) {
            case 0:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.black));
                break;
            case 1:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.brown));
                break;
            case 2:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.red));
                break;
            case 3:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.orange));
                break;
            case 4:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.yellow));
                break;
            case 5:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.green));
                break;
            case 6:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.blue));
                break;
            case 7:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.violet));
                break;
            case 8:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.grey));
                break;
            case 9:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.white));
                break;
            case 10:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.silver));
                break;
            case 11:
                color.setBackgroundColor(fragment.getResources().getColor(R.color.gold));
                break;
        }
    }

    private static void resetColors(View rootView, final Spinner spinner) {
        TextView colorFirstDigit = rootView.findViewById(R.id.color_first_digit);
        TextView colorSecondDigit = rootView.findViewById(R.id.color_second_digit);
        TextView colorMultiplier = rootView.findViewById(R.id.color_multiplier);
        EditText resistInput = rootView.findViewById(R.id.resist_input_editText);

        colorFirstDigit.setAlpha(0);
        colorSecondDigit.setAlpha(0);
        colorMultiplier.setAlpha(0);
        resistInput.setText("");
        spinner.setSelection(0);
    }

    private static void setColorDigits(double resist, int multiplier, Fragment fragment, View rootView) {
        TextView colorFirstDigit = rootView.findViewById(R.id.color_first_digit);
        TextView colorSecondDigit = rootView.findViewById(R.id.color_second_digit);
        TextView colorMultiplier = rootView.findViewById(R.id.color_multiplier);

        colorFirstDigit.setAlpha(1);
        colorSecondDigit.setAlpha(1);
        colorMultiplier.setAlpha(1);

        int firstDigit = (int) resist / 10;
        int secondDigit = (int) resist % 10;

        setColors(fragment, firstDigit, colorFirstDigit);
        setColors(fragment, secondDigit, colorSecondDigit);
        setColors(fragment, multiplier, colorMultiplier);

    }

    @Override
    public void calculate(final View rootView, final Fragment fragment) {
        final EditText resistInput = rootView.findViewById(R.id.resist_input_editText);
        Button buttonCalculate = rootView.findViewById(R.id.button_calculate);
        Button buttonReset = rootView.findViewById(R.id.button_reset_colors);

        final Spinner spinner = rootView.findViewById(R.id.spinner_resistor);

        ArrayAdapter<String> adapter;
        if (fragment.getActivity() != null) {
            adapter = new ArrayAdapter<>(
                    fragment.getActivity(),
                    android.R.layout.simple_list_item_1,
                    fragment.getResources().getStringArray(R.array.spinner_resistor_values));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateColors(rootView, fragment, resistInput, spinner);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColors(rootView, spinner);
            }
        });
    }
}