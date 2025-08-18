package app.br.psicontroleapi.vo;

import java.util.regex.Pattern;

public record Cpf(String value) {

    /**
     * A precompiled {@link Pattern} used to validate if a string contains exactly
     * 11 consecutive numeric digits.
     * This pattern is primarily utilized in CPF validation to ensure the input
     * adheres to the expected format of containing only numeric characters
     * and having a length of 11.
     */
    private static final Pattern ONLY_DIGITS = Pattern.compile("\\d{11}");

    public Cpf {
        final String cleaned = clean(value);
        if (!isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid CPF: " + value);
        }
        value = cleaned;
    }

    /**
     * Cleans a given string by removing all non-digit characters.
     *
     * @param raw the input string to clean; can be null
     * @return a string containing only the digit characters from the input,
     *         or an empty string if the input is null
     */
    public static String clean(String raw) {
        return raw == null ? "" : raw.replaceAll("\\D", "");
    }

    /**
     * Validates whether the given CPF string is valid according to CPF rules.
     * The validation involves:
     * - Cleaning the CPF string to include only digits.
     * - Checking if the string contains only digits and has the correct length.
     * - Confirming that the digits are not all identical.
     * - Verifying the check digits using a weighted sum algorithm.
     *
     * @param cpf the CPF string to validate
     * @return true if the CPF string is valid, false otherwise
     */
    public static boolean isValid(String cpf) {
        String digits = clean(cpf);
        if (!ONLY_DIGITS.matcher(digits).matches()) return false;
        if (allDigitsEqual(digits)) return false;

        return checkDigit(digits, 9) == charToInt(digits.charAt(9))
                && checkDigit(digits,10) == charToInt(digits.charAt(10));
    }

    /**
     * Calculates the verification digit for a given position in a digit sequence
     * using a weighted sum algorithm as per CPF validation rules.
     *
     * @param digits the string containing only the digits to validate
     * @param position the position for which the check digit is calculated (0-indexed)
     * @return the calculated check digit for the specified position
     */
    private static int checkDigit(String digits, int position) {
        int weight = position + 1;               // 10 or 11
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += charToInt(digits.charAt(i)) * (weight--);
        }
        int mod = (sum * 10) % 11;
        return (mod == 10) ? 0 : mod;
    }

    /**
     * Determines if all characters in the provided string are identical.
     *
     * @param digits the string containing the sequence of digits to evaluate
     * @return true if all characters in the string are the same, false otherwise
     */
    private static boolean allDigitsEqual(String digits) {
        char c = digits.charAt(0);
        for (int i = 1; i < digits.length(); i++) {
            if (digits.charAt(i) != c) return false;
        }
        return true;
    }

    /**
     * Converts a numeric character to its corresponding integer value.
     *
     * @param c the numeric character to convert ('0'-'9')
     * @return the integer representation of the character
     * @throws IllegalArgumentException if the character is not a numeric digit
     */
    private static int charToInt(char c) {
        return c - '0';
    }
}