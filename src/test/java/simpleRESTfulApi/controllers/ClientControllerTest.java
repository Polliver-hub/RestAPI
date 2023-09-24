package simpleRESTfulApi.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import simpleRESTfulApi.dto.ClientDTO;
import simpleRESTfulApi.entites.Client;
import simpleRESTfulApi.mappers.ClientMapper;
import simpleRESTfulApi.services.ClientService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientControllerTest {

    private ClientService clientService;
    private ClientController clientController;


    @BeforeEach
    void setUp() {
        clientService = Mockito.mock(ClientService.class);
        clientController = new ClientController(clientService);
    }

    @Test
    void create() {
        Client client = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto = ClientMapper.INSTANCE.ClientToClientDTO(client);

        ResponseEntity<ClientDTO> responseEntityTarget = new ResponseEntity<>(HttpStatus.CREATED);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.create(dto);
        Mockito.verify(clientService, Mockito.times(1)).create(client);
        assertEquals(responseEntityTarget,responseEntityTest);
    }

    @Test
    void readAllClientsReturnOK() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        Client client2 = new Client(2, "Petr", "Petr@gmail.com", null);
        ClientDTO dto1 = ClientMapper.INSTANCE.ClientToClientDTO(client1);
        ClientDTO dto2 = ClientMapper.INSTANCE.ClientToClientDTO(client2);

        ResponseEntity<List<ClientDTO>> responseEntityTarget = new ResponseEntity<>(List.of(dto1, dto2), HttpStatus.OK);
        Mockito.when(clientService.readAll()).thenReturn(List.of(client1, client2));
        ResponseEntity<List<ClientDTO>> responseEntityTest = clientController.readAllClients();
        Mockito.verify(clientService, Mockito.times(1)).readAll();
        assertEquals(responseEntityTarget.getStatusCode(),responseEntityTest.getStatusCode());
    }

    @Test
    void readAllClientsReturnNotFound() {
        Mockito.when(clientService.readAll()).thenReturn(null);
        ResponseEntity<List<ClientDTO>> responseEntityTest = clientController.readAllClients();
        Mockito.verify(clientService, Mockito.times(1)).readAll();
        assertEquals(HttpStatus.NOT_FOUND,responseEntityTest.getStatusCode());
    }

    @Test
    void readByIdReturnOk() {
        Client client = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto = ClientMapper.INSTANCE.ClientToClientDTO(client);
        ResponseEntity<ClientDTO> responseEntityTarget = new ResponseEntity<>(dto,HttpStatus.OK);
        Mockito.when(clientService.read(1)).thenReturn(client);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.readById(1);
        Mockito.verify(clientService, Mockito.times(2)).read(1);
        assertEquals(responseEntityTarget.getStatusCode(),responseEntityTest.getStatusCode());
    }

    @Test
    void readByIdReturnNotFound() {
        Mockito.when(clientService.read(2)).thenReturn(null);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.readById(2);
        Mockito.verify(clientService, Mockito.times(1)).read(2);
        assertEquals(HttpStatus.NOT_FOUND,responseEntityTest.getStatusCode());
    }

    @Test
    void updateReturnOk() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto1 = ClientMapper.INSTANCE.ClientToClientDTO(client1);

        Mockito.when(clientService.update(client1,2)).thenReturn(true);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.update(2, dto1);
        Mockito.verify(clientService, Mockito.times(1)).update(client1,2);
        assertEquals(HttpStatus.OK,responseEntityTest.getStatusCode());
    }

    @Test
    void updateReturnNOT_MODIFIED() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto1 = ClientMapper.INSTANCE.ClientToClientDTO(client1);

        Mockito.when(clientService.update(client1,2)).thenReturn(false);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.update(2, dto1);
        Mockito.verify(clientService, Mockito.times(1)).update(client1,2);
        assertEquals(HttpStatus.NOT_MODIFIED,responseEntityTest.getStatusCode());
    }

    @Test
    void deleteReturnOK() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto1 = ClientMapper.INSTANCE.ClientToClientDTO(client1);

        Mockito.when(clientService.delete(2)).thenReturn(true);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.delete(2);
        Mockito.verify(clientService, Mockito.times(1)).delete(2);
        assertEquals(HttpStatus.OK,responseEntityTest.getStatusCode());
    }

    @Test
    void deleteReturnNOT_MODIFIED() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto1 = ClientMapper.INSTANCE.ClientToClientDTO(client1);

        Mockito.when(clientService.delete(2)).thenReturn(false);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.delete(2);
        Mockito.verify(clientService, Mockito.times(1)).delete(2);
        assertEquals(HttpStatus.NOT_MODIFIED,responseEntityTest.getStatusCode());
    }

    @Test
    void updatePatchReturnOk() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto1 = ClientMapper.INSTANCE.ClientToClientDTO(client1);
        Mockito.when(clientService.read(2)).thenReturn(client1);
        Mockito.when(clientService.updatePatch(client1,2)).thenReturn(client1);
        ResponseEntity<ClientDTO> responseEntityTarget = new ResponseEntity<>(dto1,HttpStatus.OK);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.updatePatch(2, dto1);
        Mockito.verify(clientService, Mockito.times(1)).updatePatch(client1,2);
        assertEquals(responseEntityTarget.getBody(), dto1);
        assertEquals(responseEntityTarget.getStatusCode(),responseEntityTest.getStatusCode());
    }

    @Test
    void updatePatchReturnNOT_MODIFIED() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        ClientDTO dto1 = ClientMapper.INSTANCE.ClientToClientDTO(client1);
        Mockito.when(clientService.read(2)).thenReturn(null);
        ResponseEntity<ClientDTO> responseEntityTest = clientController.updatePatch(2, dto1);
        Mockito.verify(clientService, Mockito.times(0)).updatePatch(client1,2);
        assertEquals(HttpStatus.NOT_MODIFIED, responseEntityTest.getStatusCode());
    }
}