package simpleRESTfulApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simpleRESTfulApi.entites.Client;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {

}
