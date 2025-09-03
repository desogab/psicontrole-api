package app.br.psicontroleapi.persistance.impl;

import app.br.psicontroleapi.entity.Client;
import app.br.psicontroleapi.persistance.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ClientRepositoryInMemoryImpl implements ClientRepository {
    private final List<Client> clients = new ArrayList<>();

    @Override
    public boolean save(Client client) {
        if (clients.stream().anyMatch(c -> c.getCpf().equals(client.getCpf()))) return false;
        clients.add(client);
        return true;
    }

    //TODO: need to join with guardian and use a id of psychologist to filter
    @Override
    public List<Client> findAll() {
        return clients;
    }

    //TODO: only delete if the psychologist has relation with this client
    public boolean delete(UUID clientId) {
        return clients.removeIf(c -> c.getClientId().equals(clientId));
    }
}
