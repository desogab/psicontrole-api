package app.br.psicontroleapi.entity;

import java.util.Objects;
import java.util.UUID;

public class Guardian extends Person {
    private final UUID guardianId;

    private Guardian(UUID guardianId, String name, String cpf) {
        super(name, cpf);
        this.guardianId = guardianId;
    }

    public static Guardian create(String guardianId, String name, String cpf) {
        if (guardianId == null || guardianId.isBlank()) throw new IllegalArgumentException("GuardianId cannot be null or blank");
        return new Guardian(UUID.fromString(guardianId), name, cpf);
    }

    public UUID getGuardianId() {
        return guardianId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Guardian guardian = (Guardian) o;
        return Objects.equals(guardianId, guardian.guardianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guardianId);
    }
}
