package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GuardianTest {

    /**
     * Test case for the successful creation of Guardian with valid parameters.
     */
    @Test
    void testCreateGuardianSuccessfully() {
        // Arrange
        String guardianId = UUID.randomUUID().toString();
        String name = "John Doe";
        String cpf = "12345678909";

        // Act
        Guardian guardian = Guardian.create(guardianId, name, cpf);

        // Assert
        assertNotNull(guardian);
        assertEquals(UUID.fromString(guardianId), guardian.getGuardianId());
        assertEquals(name, guardian.getName());
        assertEquals(cpf, guardian.getCpf());
    }

    /**
     * Test case for creation of Guardian with a null guardianId.
     */
    @Test
    void testCreateGuardianWithNullIdThrowsException() {
        // Arrange
        String name = "Jane Doe";
        String cpf = "123.456.789-09";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Guardian.create(null, name, cpf));
    }

    /**
     * Test case for creation of Guardian with a blank name.
     */
    @Test
    void testCreateGuardianWithBlankNameThrowsException() {
        // Arrange
        String guardianId = UUID.randomUUID().toString();
        String name = "   ";
        String cpf = "123.456.789-09";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Guardian.create(guardianId, name, cpf));
    }

    /**
     * Test case for creation of Guardian with a null name.
     */
    @Test
    void testCreateGuardianWithNullNameThrowsException() {
        // Arrange
        String guardianId = UUID.randomUUID().toString();
        String cpf = "123.456.789-09";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Guardian.create(guardianId, null, cpf));
    }

    /**
     * Test case for creation of Guardian with an invalid CPF value.
     */
    @Test
    void testCreateGuardianWithInvalidCpfThrowsException() {
        // Arrange
        String guardianId = UUID.randomUUID().toString();
        String name = "John Smith";
        String invalidCpf = "00000000000";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Guardian.create(guardianId, name, invalidCpf));
    }
}