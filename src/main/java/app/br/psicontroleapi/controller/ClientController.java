package app.br.psicontroleapi.controller;

import app.br.psicontroleapi.dto.ClientDTO;
import app.br.psicontroleapi.usecase.CreateClientUseCase;
import app.br.psicontroleapi.usecase.DeleteClientUseCase;
import app.br.psicontroleapi.usecase.GetClientsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/psychologist")
public class ClientController {
    private final CreateClientUseCase createClientUseCase;
    private final GetClientsUseCase getClientsUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase, GetClientsUseCase getClientsUseCase, DeleteClientUseCase deleteClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.getClientsUseCase = getClientsUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
    }

    @PostMapping(value = "/client", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Map<String, String>> save(@RequestBody ClientDTO clientDTO) {
        final Map<String, String> response = new HashMap<>();
        final boolean created = createClientUseCase.execute(clientDTO);
        if (created) {
            response.put("message", "Cliente criado com sucesso");
            return ResponseEntity.ok(response);
        }
        response.put("message", "Erro ao criar cliente");
        return ResponseEntity.badRequest().body(response);
    }

    //TODO: should be return clients from psychologist only, need to be validated from jwt token
    @GetMapping(value = "/clients", produces = "application/json")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(getClientsUseCase.execute());
    }

    //TODO: should be return clients from psychologist only, need to be validated from jwt token
    @DeleteMapping(value = "/client/{id}", produces = "application/json")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") String id) {
        final Map<String, String> response = new HashMap<>();
        final boolean deleted = deleteClientUseCase.execute(UUID.fromString(id));
        if (deleted) {
            response.put("message", "Cliente excluido com sucesso");
            return ResponseEntity.ok(response);
        }
        response.put("message", "Erro ao excluir o cliente");
        return ResponseEntity.badRequest().body(response);
    }

}
