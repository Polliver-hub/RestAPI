package simpleRESTfulApi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import simpleRESTfulApi.entites.Client;
import simpleRESTfulApi.repositories.ClientsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    private ClientsRepository clientsRepository;
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientsRepository = Mockito.mock(ClientsRepository.class);
        clientService = new ClientServiceImpl(clientsRepository);
    }

    @Test
    void create() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        when(clientsRepository.getReferenceById(1)).thenReturn(client1);
        clientService.create(client1);
        verify(clientsRepository,times(1)).save(client1);
        Client check = clientService.read(1);
        assertEquals(client1,check);
        verify(clientsRepository,times(1)).getReferenceById(1);
    }

    @Test
    void readAll() {
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        Client client2 = new Client(2,null,null,null);
        Client client3 = new Client();
        when(clientsRepository.findAll()).thenReturn(List.of(client1, client2, client3));
        assertEquals(clientService.readAll(), List.of(client1, client2, client3));
    }

    @Test
    void read() {
        Client client2 = new Client(2,null,null,null);
        clientService.create(client2);
        when(clientsRepository.getReferenceById(2)).thenReturn(client2);
        assertEquals(clientService.read(2), client2);
        verify(clientsRepository,times(1)).getReferenceById(2);
    }

    @Test
    void update() {
        Client client1 = new Client(1,null,null,null);
        Client client2 = new Client(2, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        Client clientNewClient1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        clientService.create(client1);
        clientService.update(client2, 1);
        when(clientsRepository.getReferenceById(1)).thenReturn(clientNewClient1);
        assertEquals(clientService.read( 1), clientNewClient1);
    }

    @Test
    void updatePatch() {
        Client client1 = new Client(1,"Petia",null,null);
        Client client2 = new Client(2, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        Client result = new Client(2, "Petia","ivan@gmail.com", "+7 925 444 33 22");
        clientService.create(client2);
        clientService.create(client2);
        when(clientsRepository.getReferenceById(2)).thenReturn(client2);
        clientService.updatePatch(client1, 2);
        assertEquals(clientService.read( 2), result);
    }

    @Test
    void delete() {
        Client client1 = new Client(1,null,null,null);
        Client client2 = new Client(2, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        clientService.create(client1);
        clientService.create(client2);
        when(clientsRepository.existsById(2)).thenReturn(true);
        assertTrue(clientService.delete( 2));
        verify(clientsRepository,times(1)).deleteById(2);
        assertNull(clientService.read( 2));
    }
}