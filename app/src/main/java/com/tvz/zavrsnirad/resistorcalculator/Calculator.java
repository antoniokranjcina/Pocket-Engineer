package com.tvz.zavrsnirad.resistorcalculator;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.R;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

final class Calculator {
    private static final String TAG = "Calculator";
    private static RadioGroup radioGroupFirstDigit, radioGroupSecondDigit, radioGroupMultiply, radioGroupTolerance;
    private static RadioButton[] radioButton = new RadioButton[37];
    private static RadioButton radioButtonFirstDigit, radioButtonSecondDigit, radioButtonMultiplier, radioButtonTolerance;
    private static TextView color1stDigit, color2ndDigit, colorMultiplier, colorTolerance;
    private static String[] id;

    private Calculator() {}

    static void calculateColorsToResist(final View rootView, final Fragment fragment) {
//        radioButton groups
        radioGroupFirstDigit = rootView.findViewById(R.id.radioGroup1stDigit);
        radioGroupSecondDigit = rootView.findViewById(R.id.radioGroup2ndDigit);
        radioGroupMultiply = rootView.findViewById(R.id.radioGroupMultiplier);
        radioGroupTolerance = rootView.findViewById(R.id.radioGroupTolerance);

//        resist display
        TextView resist = rootView.findViewById(R.id.resistText);

//        resistor colors
        color1stDigit = rootView.findViewById(R.id.color1stDigit);
        color2ndDigit = rootView.findViewById(R.id.color2ndDigit);
        colorMultiplier = rootView.findViewById(R.id.colorMultiplier);
        colorTolerance = rootView.findViewById(R.id.colorTolerance);

//        radioButtons ids
        id = new String[]{
                "btn1stDigitBrown1", "btn1stDigitRed2", "btn1stDigitOrange3", "btn1stDigitYellow4", "btn1stDigitGreen5", "btn1stDigitBlue6", "btn1stDigitViolet7", "btn1stDigitGrey8", "btn1stDigitWhite9",
                "btn2ndDigitBlack0", "btn2ndDigitBrown1", "btn2ndDigitRed2", "btn2ndDigitOrange3", "btn2ndDigitYellow4", "btn2ndDigitGreen5", "btn2ndDigitBlue6", "btn2ndDigitViolet7", "btn2ndDigitGrey8", "btn2ndDigitWhite9",
                "btnMultiplierBlackX1", "btnMultiplierBrownX10", "btnMultiplierRedX100", "btnMultiplierOrangeX1k", "btnMultiplierYellowX10k", "btnMultiplierGreenX100k", "btnMultiplierBlueX1M", "btnMultiplierVioletX10M", "btnMultiplierGold01", "btnMultiplierSilver001",
                "btnToleranceBrown1", "btnToleranceRed2", "btnToleranceGreen05", "btnToleranceBlue025", "btnToleranceViolet010", "btnToleranceGrey005", "btnToleranceGold5", "btnToleranceSilver10"
        };

        radioButtonInitialization(radioButton, fragment, rootView);
        calculateResist(radioButton, resist, rootView);
    }

    private static void calculateResist(final RadioButton[] radioButton, final TextView resistTextView, final View rootView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonFirstDigit = rootView.findViewById(getSelectedButtonId(radioGroupFirstDigit));
                radioButtonSecondDigit = rootView.findViewById(getSelectedButtonId(radioGroupSecondDigit));
                radioButtonMultiplier = rootView.findViewById(getSelectedButtonId(radioGroupMultiply));
                radioButtonTolerance = rootView.findViewById(getSelectedButtonId(radioGroupTolerance));

                int rbFirstDigit = 1, rbSecondDigit = 0;
                double rbMultiplier = 1, resistResult;
                String rbTolerance = "";

                if (getSelectedButtonId(radioGroupFirstDigit) != -1) {
                    rbFirstDigit = Integer.parseInt(radioButtonFirstDigit.getText().toString());
                    updateColor(radioButtonFirstDigit, color1stDigit);
                }
                if (getSelectedButtonId(radioGroupSecondDigit) != -1) {
                    rbSecondDigit = Integer.parseInt(radioButtonSecondDigit.getText().toString());
                    updateColor(radioButtonSecondDigit, color2ndDigit);
                }
                if (getSelectedButtonId(radioGroupMultiply) != -1) {
                    rbMultiplier = readMultiplier(radioButtonMultiplier);
                    updateColor(radioButtonMultiplier, colorMultiplier);
                }
                if (getSelectedButtonId(radioGroupTolerance) != -1) {
                    rbTolerance = radioButtonTolerance.getText().toString();
                    updateColor(radioButtonTolerance, colorTolerance);
                }

                if (getSelectedButtonId(radioGroupFirstDigit) + getSelectedButtonId(radioGroupSecondDigit) + getSelectedButtonId(radioGroupMultiply) + getSelectedButtonId(radioGroupTolerance) > -3) {
                    if (getSelectedButtonId(radioGroupFirstDigit) == -1) {
                        radioGroupFirstDigit.check(R.id.btn1stDigitBrown1);
                    }
                    if (getSelectedButtonId(radioGroupSecondDigit) == -1) {
                        radioGroupSecondDigit.check(R.id.btn2ndDigitBlack0);

                    }
                    if (getSelectedButtonId(radioGroupMultiply) == -1) {
                        radioGroupMultiply.check(R.id.btnMultiplierBlackX1);
                    }
                }
                char ohmSign = getOhmSign();
                resistResult = (rbFirstDigit * 10 + rbSecondDigit) * rbMultiplier;
                String resist;
                if (resistResult - (int) resistResult == 0) {
                    String resistLong = format((long) resistResult);
                    resist = resistLong + "" + ohmSign + " " + rbTolerance;
                } else {
                    resistResult = (double) Math.round(resistResult * 100) / 100;
                    resist = resistResult + "" + ohmSign + " " + rbTolerance;
                }
                resist = resist.replace(".", ",");
                resistTextView.setText(resist);
            }
        };

//        attaching listeners to radioButtons
        for (RadioButton rb : radioButton) {
            rb.setOnClickListener(listener);
        }
    }

    /**
     * Method for setting color on resistor
     * @param radioButton currently pressed button for reading color
     * @param textView color for overriding new color
     */
    private static void updateColor(RadioButton radioButton, TextView textView) {
        ColorDrawable buttonColor = (ColorDrawable) radioButton.getBackground();
        int colorId = buttonColor.getColor();
        textView.setBackgroundColor(colorId);
    }

    /**
     * Method for initialization radioButtons
     * @param radioButton initialized radioButton
     */
    private static void radioButtonInitialization(RadioButton[] radioButton, Fragment fragment, View rootView) {
        for (int i = 0; i < id.length; i++) {
            int temp = fragment.getResources().getIdentifier(id[i], "id", fragment.getActivity().getPackageName());
            radioButton[i] = rootView.findViewById(temp);
            Log.d(TAG, "First Digit RadioButtons -> Successful radioButtonInitialization");
        }
    }

    /**
     * Method for passing selected id values of radioButton
     * @param radioGroup radioButton's group
     * @return radioButton's id
     */
    private static int getSelectedButtonId(RadioGroup radioGroup) {
        return radioGroup.getCheckedRadioButtonId();
    }

    /**
     * Method for reading multiplier value of first two digits
     * @param radioButton selected radioButton
     * @return number that multiplies first two digits
     */
    private static double readMultiplier(RadioButton radioButton) {
        String radioButtonText = radioButton.getText().toString();

        switch (radioButtonText) {
            case "x 1":
                return 1.0;
            case "x 10":
                return 10.0;
            case "x 100":
                return 100.0;
            case "x 1k":
                return 1_000.0;
            case "x 10k":
                return 10_000.0;
            case "x 100k":
                return 100_000.0;
            case "x 1M":
                return 1_000_000.0;
            case "x 10M":
                return 10_000_000.0;
            case "x 0.1":
                return 0.1;
            case "x 0.01":
                return 0.01;
        }
        return 1;
    }

    //    char array for extended ASCII table
    private static final char[] EXTENDED = {0x00C7, 0x00FC, 0x00E9, 0x00E2,
            0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
            0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
            0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
            0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
            0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
            0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
            0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
            0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
            0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
            0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
            0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
            0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
            0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
            0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
            0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
            0x207F, 0x00B2, 0x25A0, 0x00A0};

    /**
     * Method for displaying ohm
     * @return ohn sign value
     */
    private static char getOhmSign() {
        return EXTENDED[233 - 0x7F];
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    /**
     * Method which turns number into easier for reading
     * @param value number to turn
     * @return turned number
     */
    private static String format(long value) {
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    static void calculateResistToColor(final View rootView, final Fragment fragment, final Spinner spinner) {
        final EditText resistInput = rootView.findViewById(R.id.resist_input_editText);
        Button buttonCalculate = rootView.findViewById(R.id.button_calculate);
        Button buttonReset = rootView.findViewById(R.id.button_reset_colors);

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
                }
            }
        } catch (Exception e) {
            Toast.makeText(fragment.getActivity(), "You have to enter a number!", Toast.LENGTH_LONG).show();
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
}