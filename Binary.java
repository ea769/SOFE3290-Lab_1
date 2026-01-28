package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
    private String number = "0";  // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should contain only zeros or ones with any length and order.
     *               otherwise, the value of "0" will be stored. Leading zeros will be excluded and empty string will be considered as zero.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }

        // Validate the binary string (only '0' or '1' allowed)
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0";
                return;
            }
        }

        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        // If all digits are '0', ensure number is "0"
        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Adding two binary variables.
     */
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;

        int carry = 0;
        String num3 = "";

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;

            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }

            carry = sum / 2;
            sum = sum % 2;
            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    /**
     * Bitwise OR between two binary variables.
     * Aligns bits from the right (LSB), padding the shorter number with leading zeros.
     */
    public static Binary or(Binary a, Binary b) {
        String x = a.number;
        String y = b.number;

        int maxLen = Math.max(x.length(), y.length());
        StringBuilder out = new StringBuilder(maxLen);

        for (int i = 0; i < maxLen; i++) {
            char bx = getBitFromRight(x, i);
            char by = getBitFromRight(y, i);
            out.append((bx == '1' || by == '1') ? '1' : '0');
        }

        // out currently built from LSB->MSB, so reverse
        return new Binary(out.reverse().toString());
    }

    /**
     * Bitwise AND between two binary variables.
     * Aligns bits from the right (LSB), padding the shorter number with leading zeros.
     */
    public static Binary and(Binary a, Binary b) {
        String x = a.number;
        String y = b.number;

        int maxLen = Math.max(x.length(), y.length());
        StringBuilder out = new StringBuilder(maxLen);

        for (int i = 0; i < maxLen; i++) {
            char bx = getBitFromRight(x, i);
            char by = getBitFromRight(y, i);
            out.append((bx == '1' && by == '1') ? '1' : '0');
        }

        return new Binary(out.reverse().toString());
    }

    /**
     * Multiply two binary variables (unsigned).
     * Uses the classic shift-and-add method and the add() function.
     */
    public static Binary multiply(Binary a, Binary b) {
        if (a.number.equals("0") || b.number.equals("0")) {
            return new Binary("0");
        }

        // Choose multiplier as b, multiplicand as a
        String multiplicand = a.number;
        String multiplier = b.number;

        Binary result = new Binary("0");

        // Walk multiplier from LSB to MSB
        int shift = 0;
        for (int i = multiplier.length() - 1; i >= 0; i--) {
            if (multiplier.charAt(i) == '1') {
                Binary shifted = new Binary(shiftLeft(multiplicand, shift));
                result = add(result, shifted);
            }
            shift++;
        }

        return result;
    }

    // ----------------- helpers -----------------

    // Returns the bit "offsetFromRight" from the right side; if past length, returns '0'
    private static char getBitFromRight(String s, int offsetFromRight) {
        int idx = s.length() - 1 - offsetFromRight;
        if (idx < 0) return '0';
        return s.charAt(idx);
    }

    // Shift left by appending zeros (binary * 2^positions)
    private static String shiftLeft(String bin, int positions) {
        if (bin.equals("0")) return "0";
        StringBuilder sb = new StringBuilder(bin.length() + positions);
        sb.append(bin);
        for (int i = 0; i < positions; i++) sb.append('0');
        return sb.toString();
    }
}
