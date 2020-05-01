package com.tvz.zavrsnirad.numberingsystemcalculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;


final class Calculator {
    static void calculate(final View rootView, final Fragment fragment, final Spinner spinnerFrom, final Spinner spinnerIn) {
        Button buttonConvert = rootView.findViewById(R.id.button_convert_number);
        Button buttonReset = rootView.findViewById(R.id.button_num_sys_calc_reset);
        final EditText numberToConvert = rootView.findViewById(R.id.number_to_convert);
        final TextView result = rootView.findViewById(R.id.num_sys_cal_result);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerFromText = spinnerFrom.getSelectedItem().toString();
                String spinnerInText = spinnerIn.getSelectedItem().toString();

                if(spinnerFromText.equals(spinnerInText)) {
                    Toast.makeText(fragment.getActivity(), "From base and In base must be different", Toast.LENGTH_LONG).show();
                    return;
                }

                int fromBase = Integer.parseInt(spinnerFromText);
                int inBase = Integer.parseInt(spinnerInText);

                convert(fromBase, inBase, numberToConvert, result, fragment);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(numberToConvert, spinnerFrom, spinnerIn, result);
            }
        });
    }

    private static void reset(EditText inputNumber, Spinner spinnerFrom, Spinner spinnerIn, TextView result) {
        inputNumber.setText("");
        spinnerFrom.setSelection(0);
        spinnerIn.setSelection(1);
        result.setText("");
    }

    private static void convert(int fromBase, int inBase, EditText numberToConvert, TextView result, Fragment fragment) {
        try {
            int number;
            if(fromBase == 16) {
                number = Integer.parseInt(numberToConvert.getText().toString(), 16);
                fromBase = 10;
            } else {
                number = Integer.parseInt(numberToConvert.getText().toString());
            }

            if (checkNumber(number, fromBase)) {
                if (fromBase > inBase) {
                    convertHighToLow(fromBase, inBase, number, result);
                } else {
                    convertLowToHigh(fromBase, inBase, number, result);
                }
            } else {
                Toast.makeText(fragment.getActivity(), "Number digits cannot be greater than number's base.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(fragment.getActivity(), "Invalid input.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(fragment.getActivity(), "Number to Convert cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }

    private static void convertHighToLow(int fromBase, int inBase, int number, TextView result) {
        StringBuilder resultText = new StringBuilder();

        if(fromBase != 10 && fromBase != 16) {
            number = convertNumberToDecimal(number, fromBase);
        }

        if(inBase != 10) {
            while (number > 0) {
                resultText.append(number % inBase);
                number /= inBase;
            }
        }

        resultText.reverse();
        result.setText(resultText.toString());
    }

    private  static void convertLowToHigh(int fromBase, int inBase, int number, TextView result) {
        number = convertNumberToDecimal(number, fromBase);
        StringBuilder resultText = new StringBuilder();

        if(inBase < 10) {
            while (number > 0) {
                resultText.append(number % inBase);
                number /= inBase;
            }
            resultText.reverse();
        } else if (inBase > 10) {
            resultText.append(Integer.toHexString(number).toUpperCase());
        } else {
            resultText.append(number);
        }

        result.setText(resultText.toString());
    }

    private static int convertNumberToDecimal(int number, int base) {
        String numberString = String.valueOf(number);
        int convertedNumber = 0;
        int j = 0;
        for (int i = numberString.length() - 1; i >= 0; i--) {
            int currentDigit = Integer.parseInt(String.valueOf(numberString.charAt(i)));
            convertedNumber += currentDigit * Math.pow(base, j);
            j++;
        }
        return convertedNumber;
    }

    private static boolean checkNumber(int number, int base) {
        int digit;
        while (number > 0) {
            digit = number % 10;
            number /= 10;
            if(digit >= base) {
                return false;
            }
        }
        return true;
    }
}