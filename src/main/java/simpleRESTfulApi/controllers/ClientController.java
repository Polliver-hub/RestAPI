package simpleRESTfulApi.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import simpleRESTfulApi.dto.ClientDTO;
import simpleRESTfulApi.mappers.ClientMapper;
import simpleRESTfulApi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
        clientService.create(ClientMapper.INSTANCE.clientDtoToClient(clientDTO));
        log.info("client: " + clientDTO.toString() + " add");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<ClientDTO>> read() {
        final List<ClientDTO> clients = ClientMapper.INSTANCE.clientListToClientDtoList(clientService.readAll());
        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDTO> read(@PathVariable(name = "id") int id) {
        return clientService.read(id) != null
                ? new ResponseEntity<>(ClientMapper.INSTANCE.ClientToClientDTO
                (clientService.read(id)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable(name = "id") int id, @RequestBody ClientDTO dto) {
        final boolean updated = clientService.update(ClientMapper.INSTANCE.clientDtoToClient(dto), id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = clientService.delete(id);
        log.info("remove client by id: " + id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping(value = "/clients/{id}")
    public ResponseEntity<ClientDTO> updatePatch(@PathVariable(name = "id") int id, @RequestBody ClientDTO dto) {
        return clientService.read(id) != null
                ? new ResponseEntity<>(ClientMapper.INSTANCE.ClientToClientDTO
                (clientService.updatePatch(ClientMapper.INSTANCE.clientDtoToClient(dto),
                        id)), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
