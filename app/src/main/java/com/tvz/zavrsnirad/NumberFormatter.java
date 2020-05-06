package com.tvz.zavrsnirad;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NumberFormatter {
    private NumberFormatter() {}

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
     *
     * @param value number to turn
     * @return turned number
     */
    public static String format(long value) {
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

    /**
     * Method which turns double value into easier for reading
     * @param number number to format
     * @return formatted number
     */
    public static String format(double number) {
        NumberFormat formatter = new DecimalFormat("0.###############E0");
        String string = formatter.format(number);
        string = string.replace(",", ".");

        int exp = 0;
        double num = 0;
        String siSystemUnit = "";
        int wantedExp = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == 'E') {
                num = Double.parseDouble(string.substring(0, i));
                exp = Integer.parseInt(string.substring(i + 1));
                break;
            }
        }

        if(exp > 0) {
            if(exp <= 2) {
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
            } else if(exp <= 20) {
                wantedExp = exp - 18;
                siSystemUnit = "E";
            } else if(exp <= 23) {
                wantedExp = exp - 21;
                siSystemUnit = "Z";
            } else if(exp <= 26) {
                wantedExp = exp - 24;
                siSystemUnit = "Y";
            }
        } else if(exp < 0) {
            exp *= -1;
            if(exp >= 1 && exp <= 3) {
                wantedExp = 3 - exp;
                siSystemUnit = "m";
            } else if (exp >= 4 && exp <= 6) {
                wantedExp = 6 - exp;
                siSystemUnit = "μ";
            } else if(exp >= 7 && exp <= 9) {
                wantedExp = 9 - exp;
                siSystemUnit = "n";
            } else if(exp >= 10 && exp <= 12) {
                wantedExp = 12 - exp;
                siSystemUnit = "p";
            } else if(exp >= 13 && exp <= 15) {
                wantedExp = 15 - exp;
                siSystemUnit = "f";
            } else if(exp >= 16 && exp <= 18) {
                wantedExp = 18 - exp;
                siSystemUnit = "a";
            } else if(exp >= 19 && exp <= 21) {
                wantedExp = 21 - exp;
                siSystemUnit = "z";
            } else if(exp >= 22 && exp <= 24) {
                wantedExp = 24 - exp;
                siSystemUnit = "y";
            }
        }
        num *= Math.pow(10, wantedExp);
        num = Math.round(num * 100_000) / 100_000.0;

        if(num == (int)num) {
            return (int)num + siSystemUnit;
        }

        return (num + siSystemUnit).replace(".", ",");
    }
}
