package app.br.psicontroleapi.usecase;

import app.br.psicontroleapi.persistance.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteClientUseCase {
    private final ClientRepository clientRepository;

    public DeleteClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean execute(UUID clientId) {
        return clientRepository.delete(clientId);
    }
}
