package tavin.azship.gestaofretes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.dto.FreightDTO;
import tavin.azship.gestaofretes.handler.exception.ClientNotFoundException;
import tavin.azship.gestaofretes.handler.exception.CodigoNotFoundException;
import tavin.azship.gestaofretes.handler.exception.IdNotFoundException;
import tavin.azship.gestaofretes.handler.exception.PropertiesNotFoundException;
import tavin.azship.gestaofretes.infra.FreightSpecs;
import tavin.azship.gestaofretes.model.Client;
import tavin.azship.gestaofretes.model.Freight;
import tavin.azship.gestaofretes.repository.FreightRepository;
import tavin.azship.gestaofretes.repository.filter.FreightFilter;

import java.util.Optional;

@Service
public class FreightService {

    @Autowired
    private FreightRepository freightRepository;

    @Autowired
    private ClientService clientService;


    public Page<Freight> getAll(FreightFilter filter,Pageable pageable){
        return this.freightRepository.findAll(FreightSpecs.usandoFiltro(filter),pageable);
    }
    public Freight getById(Long id) throws IdNotFoundException {
        return this.freightRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    public Freight create (FreightDTO freightDTO) throws RuntimeException {
        Client client = this.clientService.findById(freightDTO.clientId());
        Freight freight = new Freight(freightDTO);
        freight.setClient(client);
        if (freight.getClient() == null) throw new ClientNotFoundException("Cliente não encontrado");
        if (freightDTO.properties().isEmpty()) throw new PropertiesNotFoundException("Propriedades não informadas");
        return this.freightRepository.save(freight);
    }
    public Freight update (Long id, FreightDTO freightDTO) throws IdNotFoundException {
        Freight freight = new Freight(id, freightDTO);
        if (freight.getId() == null || !freight.getId().equals(id)) throw new IdNotFoundException("Id não encontrado");
        return this.freightRepository.save(freight);
    }
    public void delete(Long id) throws IdNotFoundException {
        this.freightRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
        this.freightRepository.deleteById(id);
    }

}
