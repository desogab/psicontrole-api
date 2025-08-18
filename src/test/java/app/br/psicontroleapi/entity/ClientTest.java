package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    /**
     * Tests for the create method of the Client class.
     * The create method is a static factory method that takes a patientId (in String format), name, and CPF to create a new Client instance.
     */

    @Test
    void shouldCreateClientWithValidInputs() {
        // Arrange
        String patientId = UUID.randomUUID().toString();
        String name = "John Doe";
        String cpf = "12345678909";

        // Act
        Client client = Client.create(patientId, name, cpf);

        // Assert
        assertNotNull(client);
        assertEquals(UUID.fromString(patientId), client.getPatientId());
        assertEquals(name, client.getName());
        assertEquals(cpf, client.getCpf());
        assertTrue(client.isActive());
    }

    @Test
    void shouldThrowExceptionWhenPatientIdIsInvalid() {
        // Arrange
        String invalidPatientId = "invalid-uuid";
        String name = "John Doe";
        String cpf = "12345678909";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Client.create(invalidPatientId, name, cpf));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        String patientId = UUID.randomUUID().toString();
        String name = null;
        String cpf = "12345678909";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Client.create(patientId, name, cpf));
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        // Arrange
        String patientId = UUID.randomUUID().toString();
        String name = " ";
        String cpf = "12345678909";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Client.create(patientId, name, cpf));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsNull() {
        // Arrange
        String patientId = UUID.randomUUID().toString();
        String name = "John Doe";
        String cpf = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Client.create(patientId, name, cpf));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsInvalid() {
        // Arrange
        String patientId = UUID.randomUUID().toString();
        String name = "John Doe";
        String cpf = "invalid-cpf";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Client.create(patientId, name, cpf));
    }
}