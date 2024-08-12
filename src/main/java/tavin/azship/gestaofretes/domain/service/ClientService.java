package tavin.azship.gestaofretes.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tavin.azship.gestaofretes.api.dto.ClientDTO;
import tavin.azship.gestaofretes.api.dto.response.ClientResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.ClientUpdateDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceEmptyException;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.Client;
import tavin.azship.gestaofretes.domain.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<ClientResponseDTO> getAll(){
        List<Client> clients = this.clientRepository.findAll();
        return clients.stream().map(ClientResponseDTO::fromEntity).toList();
    }

    public List<ClientResponseDTO> getInactive(){
        List<Client> clients = this.clientRepository.findByInactive();
        return clients.stream().map(ClientResponseDTO::fromEntity).toList();
    }

    public ClientResponseDTO getById(Long id){
        Client client = seekOrFail(id);
        return ClientResponseDTO.fromEntity(client);
    }


    @Transactional
    public ClientResponseDTO create (@Valid ClientDTO clientDTO)  {
        Client client = new Client();

        ClientDTO.toEntity(client, clientDTO);

        this.clientRepository.save(client);

        return ClientResponseDTO.fromEntity(client);
    }

    @Transactional
    public ClientResponseDTO update(Long id,@Valid ClientUpdateDTO clientDTO)  {
        Client client = seekOrFail(id);

        ClientUpdateDTO.toEntityUpdate(client, clientDTO);

        this.clientRepository.save(client);

        return ClientResponseDTO.fromEntity(client);
    }

    @Transactional
    public void delete(Long id)  {
        seekOrFail(id);
        this.clientRepository.deleteById(id);
    }

    public Client seekOrFail(Long id) {
        return this.clientRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(ResourceNotFoundException.Type.ID, "Id não encontrado"));
    }
}
