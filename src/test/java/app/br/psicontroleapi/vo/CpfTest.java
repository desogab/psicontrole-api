package app.br.psicontroleapi.vo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    /**
     * Class under test: Cpf
     * <p>
     * The `isValid` method validates a CPF number ensuring it:
     * - Contains exactly 11 digits
     * - Has non-equal repeating digits
     * - Validates the checksum digits using mathematical rules
     */

    @Test
    void shouldReturnTrueForValidCpf() {
        // Arrange
        String validCpf = "12345678909";  // Valid CPF with correct checksum
        String validCpf2 = "12345678909";

        // Act
        boolean isValid = Cpf.isValid(validCpf);

        // Assert
        assertTrue(isValid, "Expected CPF to be valid");
        assertThat(new Cpf(validCpf2))
                .isInstanceOfSatisfying(Cpf.class, cpf -> assertThat(cpf.value()).isEqualTo(validCpf2));
    }

    @Test
    void shouldReturnFalseForInvalidCpfWithWrongChecksum() {
        // Arrange
        String invalidCpf = "12345678901";  // Invalid CPF with incorrect checksum
        String invalidCpf2 = "12345678901";

        // Act
        boolean isValid = Cpf.isValid(invalidCpf);

        // Assert
        assertFalse(isValid, "Expected CPF to be invalid due to wrong checksum");
        assertThrows(IllegalArgumentException.class, () -> new Cpf(invalidCpf2));
    }

    @Test
    void shouldReturnFalseForCpfWithFewerThan11Digits() {
        // Arrange
        String invalidCpf = "123456789";  // Invalid CPF with only 9 digits

        // Act
        boolean isValid = Cpf.isValid(invalidCpf);

        // Assert
        assertFalse(isValid, "Expected CPF to be invalid due to insufficient digits");
    }

    @Test
    void shouldReturnFalseForCpfWithMoreThan11Digits() {
        // Arrange
        String invalidCpf = "123456789012";  // Invalid CPF with 12 digits

        // Act
        boolean isValid = Cpf.isValid(invalidCpf);

        // Assert
        assertFalse(isValid, "Expected CPF to be invalid due to excess digits");
    }

    @Test
    void shouldReturnFalseForCpfWithAllEqualDigits() {
        // Arrange
        String invalidCpf = "11111111111";  // Invalid CPF with all equal digits

        // Act
        boolean isValid = Cpf.isValid(invalidCpf);

        // Assert
        assertFalse(isValid, "Expected CPF to be invalid due to all digits being equal");
    }

    @Test
    void shouldReturnFalseForNullCpf() {
        boolean isValid = Cpf.isValid(null);
        assertFalse(isValid, "Expected CPF to be invalid because it is null");
    }

    @Test
    void shouldReturnFalseForEmptyCpf() {
        String emptyCpf = "";
        boolean isValid = Cpf.isValid(emptyCpf);
        assertFalse(isValid, "Expected CPF to be invalid because it is empty");
    }

    @Test
    void shouldCalculateCheckDigitForPosition9() {
        String digits = "12345678909";
        boolean isValid = Cpf.isValid(digits);
        assertTrue(isValid, "Check digit for position 9 should match");
    }

}