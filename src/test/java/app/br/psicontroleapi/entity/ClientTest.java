package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void shouldCreateClientWithValidInputs() {
        String name = "John Doe";
        String cpf = "12345678909";
        Client client = Client.create(name, cpf);
        assertNotNull(client);
        assertEquals(name, client.getName());
        assertEquals(cpf, client.getCpf());
        assertTrue(client.isActive());
    }

    @Test
    void shouldThrowExceptionWhenPatientIdIsInvalid() {
        String name = "John Doe";
        String cpf = "12345678909";
        assertThrows(IllegalArgumentException.class, () -> Client.create(name, cpf));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        String cpf = "12345678909";
        assertThrows(IllegalArgumentException.class, () -> Client.create(null, cpf));
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        String name = " ";
        String cpf = "12345678909";
        assertThrows(IllegalArgumentException.class, () -> Client.create(name, cpf));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsNull() {
        String name = "John Doe";
        assertThrows(IllegalArgumentException.class, () -> Client.create(name, null));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsInvalid() {
        String name = "John Doe";
        String cpf = "invalid-cpf";
        assertThrows(IllegalArgumentException.class, () -> Client.create(name, cpf));
    }
}