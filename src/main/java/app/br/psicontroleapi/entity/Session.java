package app.br.psicontroleapi.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private final Psychologist psychologist;
    private final Client client;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final SessionStatus status;

    private Session(Psychologist psychologist, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime, SessionStatus status) {
        this.psychologist = psychologist;
        this.client = client;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
    }

    public static Session create(Psychologist psychologist, Client client, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (psychologist == null || client == null || startDateTime == null || endDateTime == null) {
            throw new IllegalArgumentException("Cannot create session with null parameters");
        }
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        return new Session(psychologist, client, startDateTime, endDateTime, SessionStatus.SCHEDULED);
    }

    public Session updateStatus(Session session, SessionStatus newStatus) {
        if (!session.status.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException("Cannot transition from " + this.status + " to " + newStatus);
        }
        return new Session(session.psychologist, session.client, session.startDateTime, session.endDateTime, newStatus);
    }

    public Psychologist getPsychologist() {
        return this.psychologist;
    }

    public Client getClient() {
        return this.client;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public SessionStatus getStatus() {
        return this.status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(this.psychologist, session.psychologist) &&
                Objects.equals(this.client, session.client) &&
                Objects.equals(this.startDateTime, session.startDateTime) &&
                Objects.equals(this.endDateTime, session.endDateTime) &&
                this.status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.psychologist, this.client, this.startDateTime, this.endDateTime, this.status);
    }

    @Override
    public String toString() {
        return "Session{" +
                "psychologist=" + this.psychologist +
                ", client=" + this.client +
                ", startDateTime=" + this.startDateTime +
                ", endDateTime=" + this.endDateTime +
                ", status=" + this.status +
                '}';
    }
}
