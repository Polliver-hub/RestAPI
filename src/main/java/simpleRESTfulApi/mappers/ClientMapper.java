package simpleRESTfulApi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import simpleRESTfulApi.dto.ClientDTO;
import simpleRESTfulApi.entites.Client;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client clientDtoToClient(ClientDTO clientDTO);
    ClientDTO ClientToClientDTO(Client client);
    List<ClientDTO> clientListToClientDtoList(List<Client> clients);
}
