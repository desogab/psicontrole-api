package app.br.psicontroleapi.entity;

import java.util.Objects;
import java.util.UUID;

public class Client extends Person {
    private final UUID clientId;
    private final boolean active = true;

    private Client(UUID clientId, String name, String cpf) {
        super(name, cpf);
        this.clientId = clientId;
    }

    public static Client create(String name, String cpf) {
        return new Client(UUID.randomUUID(), name, cpf);
    }

    public UUID getClientId() {
        return this.clientId;
    }

    public boolean isActive() {
        return this.active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return this.active == client.active && Objects.equals(this.clientId, client.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.clientId, this.active);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", active=" + active +
                '}';
    }
}
