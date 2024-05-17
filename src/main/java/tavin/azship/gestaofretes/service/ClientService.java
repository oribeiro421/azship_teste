package tavin.azship.gestaofretes.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import tavin.azship.gestaofretes.dto.ClientDTO;
import tavin.azship.gestaofretes.handler.exception.IdNotFoundException;
import tavin.azship.gestaofretes.model.Client;
import tavin.azship.gestaofretes.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }
    public List<Client> getInactive(){
        return this.clientRepository.findByInactive();
    }
    public Client seekOrFail(Long id) throws IdNotFoundException{
        return this.clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    @Transactional
    public Client create (@Valid ClientDTO clientDTO) throws Exception {
        if (clientDTO.email().isEmpty()) throw new Exception("Client sem email");
        if (clientDTO.cnpj().isEmpty()) throw new Exception("Client sem cnpj");
        Client client = new Client(clientDTO);
        return this.clientRepository.save(client);
    }
    @Transactional
    public Client update(Long id,@Valid ClientDTO clientDTO) throws IdNotFoundException {
        Client client = new Client(id, clientDTO);
        if (client.getId() == null || !client.getId().equals(id)) throw new IdNotFoundException("Id não encontrado");
        return this.clientRepository.save(client);
    }
    @Transactional
    public void delete(Long id) throws IdNotFoundException {
        seekOrFail(id);
        this.clientRepository.deleteById(id);
    }
    @Transactional
    public void active(Long id){
        Client client = seekOrFail(id);

        client.active();
    }
    @Transactional
    public void disable(Long id){
        Client client = seekOrFail(id);

        client.disable();
    }

    @Transactional
    public void active(List<Long> ids){
        ids.forEach(this::active);
    }

    @Transactional
    public void disable(List<Long> ids){
        ids.forEach(this::disable);
    }

}
