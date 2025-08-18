package app.br.psicontroleapi.entity;

import java.util.Objects;
import java.util.UUID;

public class Client extends Person {
    private final UUID patientId;
    private final boolean active = true;

    private Client(UUID patientId, String name, String cpf) {
        super(name, cpf);
        this.patientId = patientId;
    }

    public static Client create(String patientId, String name, String cpf) {
        return new Client(UUID.fromString(patientId), name, cpf);
    }

    public UUID getPatientId() {
        return patientId;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return active == client.active && Objects.equals(patientId, client.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patientId, active);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", active=" + active +
                '}';
    }
}
