package com.tvz.zavrsnirad.resistorcalculator.colorstoresist;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tvz.zavrsnirad.util.NumberFormatter;
import com.tvz.zavrsnirad.R;

final class ColorsToResistCalculator implements com.tvz.zavrsnirad.Calculator {
    private RadioGroup radioGroupFirstDigit, radioGroupSecondDigit, radioGroupMultiply, radioGroupTolerance;
    private RadioButton[] radioButton = new RadioButton[37];
    private RadioButton radioButtonFirstDigit, radioButtonSecondDigit, radioButtonMultiplier, radioButtonTolerance;
    private TextView color1stDigit, color2ndDigit, colorMultiplier, colorTolerance;
    private String[] id;

    @Override
    public void calculate(final View rootView, final Fragment fragment) {
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

    private void calculateResist(final RadioButton[] radioButton, final TextView resistTextView, final View rootView) {
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
                    updateColor(radioButtonFirstDigit, color1stDigit, rootView);
                }
                if (getSelectedButtonId(radioGroupSecondDigit) != -1) {
                    rbSecondDigit = Integer.parseInt(radioButtonSecondDigit.getText().toString());
                    updateColor(radioButtonSecondDigit, color2ndDigit, rootView);
                }
                if (getSelectedButtonId(radioGroupMultiply) != -1) {
                    rbMultiplier = readMultiplier(radioButtonMultiplier);
                    updateColor(radioButtonMultiplier, colorMultiplier, rootView);
                }
                if (getSelectedButtonId(radioGroupTolerance) != -1) {
                    rbTolerance = radioButtonTolerance.getText().toString();
                    updateColor(radioButtonTolerance, colorTolerance, rootView);
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
                    String resistLong = NumberFormatter.getInstance().formatWithUnit((long) resistResult);
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
     *
     * @param radioButton currently pressed button for reading color
     * @param textView    color for overriding new color
     */
    private void updateColor(RadioButton radioButton, TextView textView, View rootView) {
        int colorId = 0;
        ColorDrawable buttonColor;
        try {
            buttonColor = (ColorDrawable) radioButton.getBackground();
            colorId = buttonColor.getColor();
        } catch (Exception e) {
            switch (radioButton.getId()) {
                case R.id.btn1stDigitBrown1:
                case R.id.btnToleranceBrown1:
                    colorId = Color.parseColor("#A0522D");
                    break;
                case R.id.btn1stDigitWhite9:
                case R.id.btn2ndDigitWhite9:
                    colorId = Color.parseColor("#ffffffff");
                    break;
                case R.id.btn2ndDigitBlack0:
                case R.id.btnMultiplierBlackX1:
                    colorId = Color.parseColor("#ff000000");
                    break;
                case R.id.btnMultiplierVioletX10M:
                    colorId = Color.parseColor("#9400D3");
                    break;
                case R.id.btnMultiplierGold01:
                case R.id.btnToleranceGold5:
                    colorId = Color.parseColor("#FFD700");
                    break;
                case R.id.btnMultiplierSilver001:
                case R.id.btnToleranceSilver10:
                    colorId = Color.parseColor("#C0C0C0");
                    break;
                case R.id.btnToleranceRed2:
                    colorId = Color.parseColor("#C51C1C");
                    break;
                case R.id.btnToleranceGreen05:
                    colorId = Color.parseColor("#54BB87");
                    break;
                case R.id.btnToleranceGrey005:
                    colorId = Color.parseColor("#B6A7A7");
                    break;

            }

        }
        textView.setBackgroundColor(colorId);
    }

    /**
     * Method for initialization radioButtons
     *
     * @param radioButton initialized radioButton
     */
    private void radioButtonInitialization(RadioButton[] radioButton, Fragment fragment, View rootView) {
        for (int i = 0; i < id.length; i++) {
            int temp;
            if (fragment.getActivity() != null) {
                temp = fragment.getResources().getIdentifier(id[i], "id", fragment.getActivity().getPackageName());
            } else {
                return;
            }
            radioButton[i] = rootView.findViewById(temp);
        }
    }

    /**
     * Method for passing selected id values of radioButton
     *
     * @param radioGroup radioButton's group
     * @return radioButton's id
     */
    private int getSelectedButtonId(RadioGroup radioGroup) {
        return radioGroup.getCheckedRadioButtonId();
    }

    /**
     * Method for reading multiplier value of first two digits
     *
     * @param radioButton selected radioButton
     * @return number that multiplies first two digits
     */
    private double readMultiplier(RadioButton radioButton) {
        switch (radioButton.getId()) {
            case R.id.btnMultiplierBlackX1:
                return 1.0;
            case R.id.btnMultiplierBrownX10:
                return 10.0;
            case R.id.btnMultiplierRedX100:
                return 100.0;
            case R.id.btnMultiplierOrangeX1k:
                return 1_000.0;
            case R.id.btnMultiplierYellowX10k:
                return 10_000.0;
            case R.id.btnMultiplierGreenX100k:
                return 100_000.0;
            case R.id.btnMultiplierBlueX1M:
                return 1_000_000.0;
            case R.id.btnMultiplierVioletX10M:
                return 10_000_000.0;
            case R.id.btnMultiplierGold01:
                return 0.1;
            case R.id.btnMultiplierSilver001:
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
     *
     * @return ohn sign value
     */
    private char getOhmSign() {
        return EXTENDED[233 - 0x7F];
    }
}