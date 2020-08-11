package com.pocketengineer.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NumberFormatter {
    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    private static NumberFormatter instance;

    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    private NumberFormatter() {
    }

    public static NumberFormatter getInstance() {
        if (instance == null) {
            instance = new NumberFormatter();
        }
        return instance;
    }

    /**
     * Method which turns number into easier for reading
     *
     * @param value number to turn
     * @return turned number
     */
    public String formatWithUnit(long value) {
        if (value == Long.MIN_VALUE) return formatWithUnit(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + formatWithUnit(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    /**
     * Method which turns double value into easier for reading
     *
     * @param number number to format
     * @return formatted number
     */
    public String formatWithUnit(double number) {
        NumberFormat formatter = new DecimalFormat("0.###############E0");
        String string = formatter.format(number);
        string = string.replace(",", ".");

        String siSystemUnit = "";
        int wantedExp = 0;

        double num = parseGivenString(string)[0];
        int exp = (int) parseGivenString(string)[1];

        if (exp > 0) {
            if (exp <= 2) {
                wantedExp = exp;
                siSystemUnit = "";
            } else if (exp <= 5) {
                wantedExp = exp - 3;
                siSystemUnit = "k";
            } else if (exp <= 8) {
                wantedExp = exp - 6;
                siSystemUnit = "M";
            } else if (exp <= 11) {
                wantedExp = exp - 9;
                siSystemUnit = "G";
            } else if (exp <= 14) {
                wantedExp = exp - 12;
                siSystemUnit = "T";
            } else if (exp <= 17) {
                wantedExp = exp - 15;
                siSystemUnit = "P";
            } else if (exp <= 20) {
                wantedExp = exp - 18;
                siSystemUnit = "E";
            } else if (exp <= 23) {
                wantedExp = exp - 21;
                siSystemUnit = "Z";
            } else if (exp <= 26) {
                wantedExp = exp - 24;
                siSystemUnit = "Y";
            }
        } else if (exp < 0) {
            exp *= -1;
            if (exp >= 1 && exp <= 3) {
                wantedExp = 3 - exp;
                siSystemUnit = "m";
            } else if (exp >= 4 && exp <= 6) {
                wantedExp = 6 - exp;
                siSystemUnit = "μ";
            } else if (exp >= 7 && exp <= 9) {
                wantedExp = 9 - exp;
                siSystemUnit = "n";
            } else if (exp >= 10 && exp <= 12) {
                wantedExp = 12 - exp;
                siSystemUnit = "p";
            } else if (exp >= 13 && exp <= 15) {
                wantedExp = 15 - exp;
                siSystemUnit = "f";
            } else if (exp >= 16 && exp <= 18) {
                wantedExp = 18 - exp;
                siSystemUnit = "a";
            } else if (exp >= 19 && exp <= 21) {
                wantedExp = 21 - exp;
                siSystemUnit = "z";
            } else if (exp >= 22 && exp <= 24) {
                wantedExp = 24 - exp;
                siSystemUnit = "y";
            }
        }
        num *= Math.pow(10, wantedExp);
        num = Math.round(num * 100_000) / 100_000.0;

        if (num == (int) num) {
            return (int) num + siSystemUnit;
        }

        return (num + siSystemUnit).replace(".", ",");
    }

    public String formatInScientificNotation(double value) {
        NumberFormat baseFormat = NumberFormat.getInstance(Locale.ENGLISH);
        baseFormat.setMinimumFractionDigits(0);
        baseFormat.setMaximumFractionDigits(5);

        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return baseFormat.format(value);
        }

        double exp = Math.log10(Math.abs(value));

        if (exp <= 6) {
            if (value == (int) value) {
                return String.valueOf((int) value);
            }
            return String.valueOf(Math.round(value * 100_000) / 100_000.0).replace(".", ",");
        }

        exp = Math.floor(exp);

        double base = value / Math.pow(10, exp);

        String power = String.valueOf((long) exp);

        StringBuilder s = new StringBuilder();
        s.append(baseFormat.format(base));
        s.append("\u00d710");

        int len = power.length();
        for (int i = 0; i < len; i++) {
            char c = power.charAt(i);
            switch (c) {
                case '-':
                    s.append('\u207b');
                    break;
                case '1':
                    s.append('\u00b9');
                    break;
                case '2':
                    s.append('\u00b2');
                    break;
                case '3':
                    s.append('\u00b3');
                    break;
                default:
                    s.append((char) (0x2070 + (c - '0')));
                    break;
            }
        }
        return s.toString().replace(".", ",");
    }

    public String convertNumberToLittle(int number) {
        StringBuilder s = new StringBuilder();
        while (number > 0) {
            int digit = number % 10;
            number /= 10;
            switch (digit) {
                case 0:
                    s.append('\u2080');
                    break;
                case 1:
                    s.append('\u2081');
                    break;
                case 2:
                    s.append('\u2082');
                    break;
                case 3:
                    s.append('\u2083');
                    break;
                case 4:
                    s.append('\u2084');
                    break;
                case 5:
                    s.append('\u2085');
                    break;
                case 6:
                    s.append('\u2086');
                    break;
                case 7:
                    s.append('\u2087');
                    break;
                case 8:
                    s.append('\u2088');
                    break;
                case 9:
                    s.append('\u2089');
                    break;
            }
        }
        return s.reverse().toString();
    }

    private double[] parseGivenString(String string) {
        double num = 0;
        int exp = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == 'E') {
                num = Math.round(Double.parseDouble(string.substring(0, i)) * 100_000) / 100_000.0;
                exp = Integer.parseInt(string.substring(i + 1));
                break;
            }
        }

        return new double[]{num, exp};
    }
}
