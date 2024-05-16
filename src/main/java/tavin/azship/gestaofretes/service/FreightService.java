package tavin.azship.gestaofretes.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.dto.FreightDTO;
import tavin.azship.gestaofretes.handler.exception.*;
import tavin.azship.gestaofretes.infra.FreightSpecs;
import tavin.azship.gestaofretes.model.AddressCollect;
import tavin.azship.gestaofretes.model.AddressDelivery;
import tavin.azship.gestaofretes.model.Client;
import tavin.azship.gestaofretes.model.Freight;
import tavin.azship.gestaofretes.repository.FreightRepository;
import tavin.azship.gestaofretes.repository.filter.FreightFilter;


@Service
public class FreightService {

    @Autowired
    private FreightRepository freightRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AddressCollectService collectService;

    @Autowired
    private AddressDeliveryService deliveryService;


    public Page<Freight> getAll(FreightFilter filter,Pageable pageable){
        return this.freightRepository.findAll(FreightSpecs.usandoFiltro(filter),pageable);
    }
    public Freight seekOrFail(Long id) throws IdNotFoundException {
        return this.freightRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    public Freight create (@Valid FreightDTO freightDTO) throws RuntimeException {
        Client client = this.clientService.seekOrFail(freightDTO.clientId());
        AddressDelivery delivery = this.deliveryService.seekOrFail(freightDTO.addressDeliveryId());
        AddressCollect collect = this.collectService.seekOrFail(freightDTO.addressCollectId());
        Freight freight = new Freight(freightDTO);
        freight.setClient(client);
        freight.setAddressCollect(collect);
        freight.setAddressDelivery(delivery);
        if (freight.getClient() == null) throw new ClientNotFoundException("Cliente não encontrado");
        if (freight.getAddressCollect() == null) throw new AddressCollectNotFoundException("Endereço não encontrado");
        if (freight.getAddressDelivery() == null) throw new AddressDeliveryNotFoundException("Endereço não encontrado");
        if (freightDTO.properties().isEmpty()) throw new PropertiesNotFoundException("Propriedades não informadas");
        return this.freightRepository.save(freight);
    }
    public Freight update (Long id,@Valid FreightDTO freightDTO) throws IdNotFoundException {
        Freight freight = seekOrFail(id);
        Client client = this.clientService.seekOrFail(freight.getClient().getId());
        AddressDelivery delivery = this.deliveryService.seekOrFail(freightDTO.addressDeliveryId());
        AddressCollect collect = this.collectService.seekOrFail(freightDTO.addressCollectId());
        Freight freight1 = new Freight(id, freightDTO);
        if (freight.getId() == null || !freight.getId().equals(id)) throw new IdNotFoundException("Id não encontrado");
        freight1.setClient(client);
        freight1.setAddressDelivery(delivery);
        freight1.setAddressCollect(collect);
        return this.freightRepository.save(freight1);
    }
    public void delete(Long id) throws IdNotFoundException {
        seekOrFail(id);
        this.freightRepository.deleteById(id);
    }

}
