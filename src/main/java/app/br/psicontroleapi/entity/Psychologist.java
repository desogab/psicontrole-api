package app.br.psicontroleapi.entity;

import java.util.Objects;
import java.util.UUID;

public class Psychologist extends Person{
    private final UUID psychologistId;

    public Psychologist(UUID psychologistId, String name, String cpf) {
        super(name, cpf);
        this.psychologistId = psychologistId;
    }

    public static Psychologist create(String psychologistId, String name, String cpf) {
        return new Psychologist(UUID.fromString(psychologistId), name, cpf);
    }

    public UUID getPsychologistId() {
        return psychologistId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Psychologist that = (Psychologist) o;
        return Objects.equals(psychologistId, that.psychologistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), psychologistId);
    }

    @Override
    public String toString() {
        return "Psychologist{" +
                "psychologistId=" + psychologistId +
                '}';
    }
}
