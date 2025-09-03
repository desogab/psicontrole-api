package app.br.psicontroleapi.persistance;

import app.br.psicontroleapi.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {
    boolean save(Client client);
    List<Client> findAll();
    boolean delete(UUID clientId);
}
