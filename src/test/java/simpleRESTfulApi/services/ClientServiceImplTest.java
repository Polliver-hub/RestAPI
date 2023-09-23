package simpleRESTfulApi.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import simpleRESTfulApi.entites.Client;
import simpleRESTfulApi.repositories.ClientsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ClientServiceImplTest {

    @Test
    void create() {
        ClientsRepository clientsRepository = Mockito.mock(ClientsRepository.class);
        final ClientService clientService = new ClientServiceImpl(clientsRepository);
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        when(clientsRepository.getReferenceById(1)).thenReturn(client1);
        clientService.create(client1);
        Client check = clientService.read(1);
        assertEquals(client1,check);
    }

    @Test
    void readAll() {
        ClientsRepository clientsRepository = Mockito.mock(ClientsRepository.class);
        final ClientService clientService = new ClientServiceImpl(clientsRepository);
        Client client1 = new Client(1, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        Client client2 = new Client(2,null,null,null);
        Client client3 = new Client();
        List <Client> list = new ArrayList<>();
        list.add(client1);
        list.add(client2);
        list.add(client3);
        when(clientsRepository.findAll()).thenReturn(list);
        assertEquals(clientService.readAll(), list);
    }

    @Test
    void read() {
        ClientsRepository clientsRepository = Mockito.mock(ClientsRepository.class);
        final ClientService clientService = new ClientServiceImpl(clientsRepository);
        Client client2 = new Client(2,null,null,null);
        clientService.create(client2);
        when(clientsRepository.getReferenceById(2)).thenReturn(client2);
        assertEquals(clientService.read(2), client2);
    }

    @Test
    void update() {
        ClientsRepository clientsRepository = Mockito.mock(ClientsRepository.class);
        final ClientService clientService = new ClientServiceImpl(clientsRepository);
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
        ClientsRepository clientsRepository = Mockito.mock(ClientsRepository.class);
        final ClientService clientService = new ClientServiceImpl(clientsRepository);
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
        ClientsRepository clientsRepository = Mockito.mock(ClientsRepository.class);
        final ClientService clientService = new ClientServiceImpl(clientsRepository);
        Client client1 = new Client(1,null,null,null);
        Client client2 = new Client(2, "ivan", "ivan@gmail.com", "+7 925 444 33 22");
        clientService.create(client1);
        clientService.create(client2);
        when(clientsRepository.existsById(2)).thenReturn(true);
        assertEquals(clientService.delete( 2), true);

    }
}