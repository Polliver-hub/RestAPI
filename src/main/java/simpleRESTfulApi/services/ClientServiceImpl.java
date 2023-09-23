package simpleRESTfulApi.services;

import org.springframework.stereotype.Service;
import simpleRESTfulApi.entites.Client;
import simpleRESTfulApi.repositories.ClientsRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientsRepository clientsRepository;

    public ClientServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public void create(Client client) {
        clientsRepository.save(client);
    }

    @Override
    public List<Client> readAll() {
        return clientsRepository.findAll();
    }

    @Override
    public Client read(int id) {
        return clientsRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (clientsRepository.existsById(id)) {
            client.setId(id);
            clientsRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public Client updatePatch(Client client, int id) {
        Client clientById = clientsRepository.getReferenceById(id);
        if (client.getName() != null) {
            clientById.setName(client.getName());
        }
        if (client.getEmail() != null) {
            clientById.setEmail(client.getEmail());
        }
        if (client.getPhone() != null) {
            clientById.setPhone(client.getPhone());
        }
        clientsRepository.save(clientById);
        return clientById;
    }

    @Override
    public boolean delete(int id) {
        if (clientsRepository.existsById(id)) {
            clientsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
