package tavin.azship.tentativa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.tentativa.dto.FreightDTO;
import tavin.azship.tentativa.handler.exception.ClientNotFoundException;
import tavin.azship.tentativa.handler.exception.IdNotFoundException;
import tavin.azship.tentativa.model.Freight;
import tavin.azship.tentativa.repository.FreightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FreightService {

    @Autowired
    private FreightRepository freightRepository;

    public List<Freight> getAll(){
        return this.freightRepository.findAll();
    }
    public Optional<Freight> getById(Long id) throws Exception{
        return Optional.ofNullable(this.freightRepository.findById(id)
                .orElseThrow(() -> new Exception("Id não encontrado")));
    }
    public Freight create(FreightDTO data) throws ClientNotFoundException{
        Freight freight = new Freight(data);
        if (freight.getClient() == null) throw new ClientNotFoundException("Cliente não encontrado");
        return this.freightRepository.save(freight);
    }
    public Freight update(Long id, FreightDTO data) throws RuntimeException{
        this.freightRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
        Freight freight = new Freight(id, data);
        if (freight.getClient() == null) throw new ClientNotFoundException("Cliente não encontrado");
        return this.freightRepository.save(freight);
    }
    public void delete(Long id) throws IdNotFoundException{
        this.freightRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
        this.freightRepository.deleteById(id);
    }
}
