package app.br.psicontroleapi.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private final Psychologist psychologist;
    private final Client client;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private SessionStatus status = SessionStatus.SCHEDULED;

    private Session(Psychologist psychologist, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.psychologist = psychologist;
        this.client = client;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static Session create(Psychologist psychologist, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (psychologist == null || client == null || startDateTime == null || endDateTime == null) {
            throw new IllegalArgumentException("Cannot create session with null parameters");
        }
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        return new Session(psychologist, client, startDateTime, endDateTime);
    }

    public void updateStatus(SessionStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException("Cannot transition from " + this.status + " to " + newStatus);
        }
        this.status = newStatus;
    }

    public Psychologist getPsychologist() {
        return psychologist;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public SessionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(psychologist, session.psychologist) && Objects.equals(client, session.client) && Objects.equals(startDateTime, session.startDateTime) && Objects.equals(endDateTime, session.endDateTime) && status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(psychologist, client, startDateTime, endDateTime, status);
    }

    @Override
    public String toString() {
        return "Session{" +
                "psychologist=" + psychologist +
                ", client=" + client +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", status=" + status +
                '}';
    }
}
