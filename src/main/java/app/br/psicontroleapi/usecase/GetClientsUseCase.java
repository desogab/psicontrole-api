package app.br.psicontroleapi.usecase;

import app.br.psicontroleapi.persistance.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetClientsUseCase {
    private final ClientRepository clientRepository;

    public GetClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //TODO: need to join with guardian and use a id of psychologist to filter
    public List<?> execute() {
        return clientRepository.findAll();
    }
}
