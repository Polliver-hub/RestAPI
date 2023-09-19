package simpleRESTfulApi.services;

import org.springframework.stereotype.Service;
import simpleRESTfulApi.entites.Client;
import simpleRESTfulApi.repositories.ClientsRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    //    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();
//    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();
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
    public boolean delete(int id) {
        if (clientsRepository.existsById(id)) {
            clientsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
