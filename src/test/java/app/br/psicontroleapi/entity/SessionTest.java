package app.br.psicontroleapi.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void testCreateValidSession() {
        Psychologist psychologist = Psychologist.create("Dr. Alice", "123.456.789-09");
        Client client = Client.create("John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        Session session = Session.create(psychologist, client, startDateTime, endDateTime);

        assertNotNull(session);
        assertEquals(psychologist, session.getPsychologist());
        assertEquals(client, session.getClient());
        assertEquals(startDateTime, session.getStartDateTime());
        assertEquals(endDateTime, session.getEndDateTime());
        assertEquals(SessionStatus.SCHEDULED, session.getStatus());
    }

    @Test
    void testCreateSessionStartDateAfterEndDateThrowsException() {
        Psychologist psychologist = Psychologist.create("Dr. Alice", "123.456.789-09");
        Client client = Client.create("John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);

        assertThrows(IllegalArgumentException.class, () ->
                        Session.create(psychologist, client, startDateTime, endDateTime),
                "Start date cannot be after end date"
        );
    }

    @Test
    void testCreateSessionWithNullPsychologistThrowsException() {
        Client client = Client.create("John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        assertThrows(IllegalArgumentException.class, () ->
                Session.create(null, client, startDateTime, endDateTime)
        );
    }

    @Test
    void testCreateSessionWithNullClientThrowsException() {
        Psychologist psychologist = Psychologist.create("Dr. Alice", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        assertThrows(IllegalArgumentException.class, () ->
                Session.create(psychologist, null, startDateTime, endDateTime)
        );
    }

    @Test
    void testCreateSessionWithNullStartDateTimeThrowsException() {
        Psychologist psychologist = Psychologist.create("Dr. Alice", "123.456.789-09");
        Client client = Client.create("John Doe", "123.456.789-09");
        LocalDateTime endDateTime = LocalDateTime.of(2025, 8, 20, 11, 0);

        assertThrows(IllegalArgumentException.class, () ->
                Session.create(psychologist, client, null, endDateTime)
        );
    }

    @Test
    void testCreateSessionWithNullEndDateTimeThrowsException() {
        Psychologist psychologist = Psychologist.create("Dr. Alice", "123.456.789-09");
        Client client = Client.create("John Doe", "123.456.789-09");
        LocalDateTime startDateTime = LocalDateTime.of(2025, 8, 20, 10, 0);

        assertThrows(IllegalArgumentException.class, () ->
                Session.create(psychologist, client, startDateTime, null)
        );
    }
}