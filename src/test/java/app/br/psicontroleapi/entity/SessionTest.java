package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    /**
     * This class, SessionTest, tests the `create` method of the `Session` class.
     * The `create` method is responsible for instantiating a new `Session`
     * with the specified psychologist, client, startDateTime, and endDateTime.
     * It validates that the startDateTime is not after the endDateTime.
     */

    @Test
    void testCreateValidSession() {
        // Arrange
        Psychologist psychologist = Psychologist.create(UUID.randomUUID().toString(), "Dr. Alice", "123.456.789-09");
        Client client = Client.create(UUID.randomUUID().toString(), "John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        // Act
        Session session = Session.create(psychologist, client, startDateTime, endDateTime);

        // Assert
        assertNotNull(session);
        assertEquals(psychologist, session.getPsychologist());
        assertEquals(client, session.getClient());
        assertEquals(startDateTime, session.getStartDateTime());
        assertEquals(endDateTime, session.getEndDateTime());
        assertEquals(SessionStatus.SCHEDULED, session.getStatus());
    }

    @Test
    void testCreateSessionStartDateAfterEndDateThrowsException() {
        // Arrange
        Psychologist psychologist = Psychologist.create(UUID.randomUUID().toString(), "Dr. Alice", "123.456.789-09");
        Client client = Client.create(UUID.randomUUID().toString(), "John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                        Session.create(psychologist, client, startDateTime, endDateTime),
                "Start date cannot be after end date"
        );
    }

    @Test
    void testCreateSessionWithNullPsychologistThrowsException() {
        // Arrange
        Client client = Client.create(UUID.randomUUID().toString(), "John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                Session.create(null, client, startDateTime, endDateTime)
        );
    }

    @Test
    void testCreateSessionWithNullClientThrowsException() {
        // Arrange
        Psychologist psychologist = Psychologist.create(UUID.randomUUID().toString(), "Dr. Alice", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                Session.create(psychologist, null, startDateTime, endDateTime)
        );
    }

    @Test
    void testCreateSessionWithNullStartDateTimeThrowsException() {
        // Arrange
        Psychologist psychologist = Psychologist.create(UUID.randomUUID().toString(), "Dr. Alice", "123.456.789-09");
        Client client = Client.create(UUID.randomUUID().toString(), "John Doe", "123.456.789-09");
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                Session.create(psychologist, client, null, endDateTime)
        );
    }

    @Test
    void testCreateSessionWithNullEndDateTimeThrowsException() {
        // Arrange
        Psychologist psychologist = Psychologist.create(UUID.randomUUID().toString(), "Dr. Alice", "123.456.789-09");
        Client client = Client.create(UUID.randomUUID().toString(), "John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                Session.create(psychologist, client, startDateTime, null)
        );
    }
}