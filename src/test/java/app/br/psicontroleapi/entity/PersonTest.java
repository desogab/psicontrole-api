package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    /**
     * Test for the equals() method in Person class.
     * Validates different use cases for object equality.
     */

    @Test
    void testEquals_SameObject() {
        Person person = new Person("John Doe", "123.456.789-09");

        assertEquals(person, person, "An object should be equal to itself.");
    }

    @Test
    void testEquals_NullObject() {
        Person person = new Person("John Doe", "123.456.789-09");

        assertNotEquals(null, person, "An object should not be equal to null.");
    }

    @Test
    void testEquals_DifferentClass() {
        Person person = new Person("John Doe", "123.456.789-09");
        String dummy = "Not a Person";

        assertNotEquals(person, dummy, "An object should not be equal to an object of a different class.");
    }

    @Test
    void testEquals_EqualObjects() {
        Person person1 = new Person("John Doe", "123.456.789-09");
        Person person2 = new Person("John Doe", "123.456.789-09");

        assertEquals(person1, person2, "Two person objects with the same name and CPF should be equal.");
    }

    @Test
    void testEquals_DifferentName() {
        Person person1 = new Person("John Doe", "123.456.789-09");
        Person person2 = new Person("Jane Doe", "123.456.789-09");

        assertNotEquals(person1, person2, "Two person objects with different names should not be equal.");
    }

    @Test
    void testEquals_DifferentCpf() {
        Person person1 = new Person("John Doe", "123.456.789-09");
        Person person2 = new Person("John Doe", "987.654.321-00");

        assertNotEquals(person1, person2, "Two person objects with different CPFs should not be equal.");
    }

    @Test
    void testEquals_BothNameAndCpfDifferent() {
        Person person1 = new Person("John Doe", "123.456.789-09");
        Person person2 = new Person("Jane Doe", "987.654.321-00");

        assertNotEquals(person1, person2, "Two person objects with different names and CPFs should not be equal.");
    }
}