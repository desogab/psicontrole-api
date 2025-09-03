package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PsychologistTest {

    @Test
    void testCreatePsychologistWithValidInput() {
        String name = "John Doe";
        String cpf = "12345678909";
        Psychologist psychologist = Psychologist.create(name, cpf);
        assertNotNull(psychologist);
        assertEquals(name, psychologist.getName());
        assertEquals(cpf, psychologist.getCpf());
    }

    @Test
    void testCreatePsychologistWithInvalidUUID() {
        String name = "John Doe";
        String cpf = "123.456.789-09";
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(name, cpf));
    }

    @Test
    void testCreatePsychologistWithNullName() {
        String cpf = "123.456.789-09";
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(null, cpf));
    }

    @Test
    void testCreatePsychologistWithBlankName() {
        String name = "   ";
        String cpf = "123.456.789-09";
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(name, cpf));
    }

    @Test
    void testCreatePsychologistWithInvalidCpf() {
        String name = "John Doe";
        String invalidCpf = "invalid-cpf";
        assertThrows(IllegalArgumentException.class, () -> Psychologist.create(name, invalidCpf));
    }
}