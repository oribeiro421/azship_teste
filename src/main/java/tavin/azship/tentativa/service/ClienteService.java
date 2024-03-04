package tavin.azship.tentativa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.tentativa.dto.ClientDTO;
import tavin.azship.tentativa.handler.exception.IdNotFoundException;
import tavin.azship.tentativa.model.Client;
import tavin.azship.tentativa.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }
    public Client getById(Long id) throws IdNotFoundException {
        return this.clientRepository
                .findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    public Client create(ClientDTO data) throws RuntimeException{
        Client client = new Client(data);
        return this.clientRepository.save(client);
    }
    public Client update(Long id, ClientDTO data) throws IdNotFoundException {
        this.clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
        Client client = new Client(id, data);
        return this.clientRepository.save(client);
    }
    public void delete(Long id) throws IdNotFoundException {
        this.clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
        this.clientRepository.deleteById(id);
    }
    public Client findById(Long id){
        return this.clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
}
