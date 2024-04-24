package tavin.azship.gestaofretes.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.dto.ClientDTO;
import tavin.azship.gestaofretes.handler.exception.IdNotFoundException;
import tavin.azship.gestaofretes.model.Client;
import tavin.azship.gestaofretes.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }
    public Client getById (Long id) throws IdNotFoundException{
        return this.clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    public Client create (ClientDTO clientDTO) throws Exception {
        if (clientDTO.email().isEmpty()) throw new Exception("Client sem email");
        if (clientDTO.cnpj().isEmpty()) throw new Exception("Client sem cnpj");
        Client client = new Client(clientDTO);
        return this.clientRepository.save(client);
    }
    public Client update(Long id, ClientDTO clientDTO) throws IdNotFoundException {
        Client client = new Client(id, clientDTO);
        if (client.getId() == null || !client.getId().equals(id)) throw new IdNotFoundException("Id não encontrado");
        return this.clientRepository.save(client);
    }
    public void delete(Long id) throws IdNotFoundException {
        this.clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
        this.clientRepository.deleteById(id);
    }
    public Client findById(Long id) throws IdNotFoundException {
        return this.clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }

}
