package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PsychologistTest {

    /**
     * Test the `create` method of Psychologist class.
     * <p>
     * This test ensures the correct creation of a Psychologist instance
     * with valid input data.
     */
    @Test
    void testCreatePsychologistWithValidInput() {
        // Arrange
        String psychologistId = "550e8400-e29b-41d4-a716-446655440000";
        String name = "John Doe";
        String cpf = "12345678909";

        // Act
        Psychologist psychologist = Psychologist.create(psychologistId, name, cpf);

        // Assert
        assertNotNull(psychologist);
        assertEquals(UUID.fromString(psychologistId), psychologist.getPsychologistId());
        assertEquals(name, psychologist.getName());
        assertEquals(cpf, psychologist.getCpf());
    }

    /**
     * Test the `create` method to check behavior when an invalid UUID is passed.
     * <p>
     * This test ensures that the create method throws an IllegalArgumentException
     * when the UUID is malformed.
     */
    @Test
    void testCreatePsychologistWithInvalidUUID() {
        // Arrange
        String invalidPsychologistId = "invalid-uuid";
        String name = "John Doe";
        String cpf = "123.456.789-09";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(invalidPsychologistId, name, cpf));
    }

    /**
     * Test the `create` method to check behavior when the name is null.
     * <p>
     * This test ensures that the create method throws an IllegalArgumentException
     * when the name is null.
     */
    @Test
    void testCreatePsychologistWithNullName() {
        // Arrange
        String psychologistId = "550e8400-e29b-41d4-a716-446655440000";
        String name = null;
        String cpf = "123.456.789-09";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(psychologistId, name, cpf));
    }

    /**
     * Test the `create` method to check behavior when the name is blank.
     * <p>
     * This test ensures that the create method throws an IllegalArgumentException
     * when the name is blank.
     */
    @Test
    void testCreatePsychologistWithBlankName() {
        // Arrange
        String psychologistId = "550e8400-e29b-41d4-a716-446655440000";
        String name = "   ";
        String cpf = "123.456.789-09";
;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(psychologistId, name, cpf));
    }

    /**
     * Test the `create` method to check behavior when the CPF is invalid.
     * <p>
     * This test ensures that the create method throws an IllegalArgumentException
     * when the CPF is invalid.
     */
    @Test
    void testCreatePsychologistWithInvalidCpf() {
        // Arrange
        String psychologistId = "550e8400-e29b-41d4-a716-446655440000";
        String name = "John Doe";
        String invalidCpf = "invalid-cpf";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(psychologistId, name, invalidCpf));
    }
}