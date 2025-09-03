package app.br.psicontroleapi.entity;

import java.util.Objects;
import java.util.UUID;

public class Psychologist extends Person {
    private final UUID psychologistId;

    private Psychologist(UUID psychologistId, String name, String cpf) {
        super(name, cpf);
        this.psychologistId = psychologistId;
    }

    public static Psychologist create(String name, String cpf) {
        return new Psychologist(UUID.randomUUID(), name, cpf);
    }

    public UUID getPsychologistId() {
        return this.psychologistId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Psychologist that = (Psychologist) o;
        return Objects.equals(this.psychologistId, that.psychologistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.psychologistId);
    }

    @Override
    public String toString() {
        return "Psychologist{" +
                "psychologistId=" + this.psychologistId +
                '}';
    }
}
