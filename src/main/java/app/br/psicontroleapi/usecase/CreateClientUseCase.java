package app.br.psicontroleapi.usecase;

import app.br.psicontroleapi.dto.ClientDTO;
import app.br.psicontroleapi.entity.Client;
import app.br.psicontroleapi.persistance.ClientRepository;
import app.br.psicontroleapi.persistance.impl.ClientRepositoryInMemoryImpl;
import org.springframework.stereotype.Service;

@Service
public class CreateClientUseCase {
    private final ClientRepository clientRepository;

    public CreateClientUseCase() {
        this.clientRepository = new ClientRepositoryInMemoryImpl();
    }

    public boolean execute(ClientDTO newClient) {
        final Client client = Client.create(newClient.name(), newClient.cpf());
        return clientRepository.save(client);
    }
}
