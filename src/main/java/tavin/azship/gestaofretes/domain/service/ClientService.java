package tavin.azship.gestaofretes.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tavin.azship.gestaofretes.api.dto.ClientDTO;
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

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }

    public List<Client> getInactive(){
        return this.clientRepository.findByInactive();
    }

    public Client seekOrFail(Long id) {
        return this.clientRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(ResourceNotFoundException.Type.ID, "Id não encontrado"));
    }

    @Transactional
    public Client create (@Valid ClientDTO clientDTO)  {
        Client client = new Client();

        ClientDTO.toEntity(client, clientDTO);

        return this.clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id,@Valid ClientUpdateDTO clientDTO)  {
        Client client = seekOrFail(id);

        ClientUpdateDTO.toEntityUpdate(client, clientDTO);

        return this.clientRepository.save(client);
    }

    @Transactional
    public void delete(Long id)  {
        seekOrFail(id);
        this.clientRepository.deleteById(id);
    }

}
