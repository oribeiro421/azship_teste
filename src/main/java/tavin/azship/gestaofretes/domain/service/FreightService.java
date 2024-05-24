package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.FreightDTO;
import tavin.azship.gestaofretes.domain.exception.IdNotFoundException;
import tavin.azship.gestaofretes.domain.exception.PropertiesNotFoundException;
import tavin.azship.gestaofretes.handler.exception.*;
import tavin.azship.gestaofretes.infra.FreightSpecs;
import tavin.azship.gestaofretes.domain.model.AddressCollect;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;
import tavin.azship.gestaofretes.domain.model.Client;
import tavin.azship.gestaofretes.domain.model.Freight;
import tavin.azship.gestaofretes.domain.repository.FreightRepository;
import tavin.azship.gestaofretes.domain.repository.filter.FreightFilter;

import java.util.List;


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
    @Transactional
    public Freight create (@Valid FreightDTO freightDTO) throws RuntimeException {
        Client client = this.clientService.seekOrFail(freightDTO.clientId());
        List<AddressDelivery> deliveries = this.deliveryService.seekOrFails(freightDTO.addressDeliveryId());
        List<AddressCollect> collects = this.collectService.seekOrFails(freightDTO.addressCollectId());
        Freight freight = new Freight(freightDTO);
        if (freightDTO.properties().isEmpty()) throw new PropertiesNotFoundException("Propriedades não informadas");
        freight.setClient(client);
        freight.setAddressDelivery(deliveries);
        freight.setAddressCollect(collects);
        return this.freightRepository.save(freight);
    }
    @Transactional
    public Freight update (Long id,@Valid FreightDTO freightDTO) throws IdNotFoundException {
        Freight freight = seekOrFail(id);
        if (freightDTO.clientId() != null){
            Client client = this.clientService.seekOrFail(freightDTO.clientId());
            freight.setClient(client);
        }
        if(freightDTO.addressCollectId() != null && !freightDTO.addressCollectId().isEmpty()){
            List<AddressCollect> collects = this.collectService.seekOrFails(freightDTO.addressCollectId());
            freight.setAddressCollect(collects);
        }
        if(freightDTO.addressDeliveryId() != null && !freightDTO.addressDeliveryId().isEmpty()){
            List<AddressDelivery> deliveries = this.deliveryService.seekOrFails(freightDTO.addressDeliveryId());
            freight.setAddressDelivery(deliveries);
        }
        if (freightDTO.properties() != null && !freightDTO.properties().isEmpty()){
            freight.setProperties(freightDTO.properties());
        }
        return this.freightRepository.save(freight);
    }
    @Transactional
    public void delete(Long id) throws IdNotFoundException {
        seekOrFail(id);
        this.freightRepository.deleteById(id);
    }
    @Transactional
    public void associationCollect(Long freightId, Long collectId){
        Freight freight = seekOrFail(freightId);
        AddressCollect addressCollect = collectService.seekOrFail(collectId);

        freight.associationCollect(addressCollect);
    }
    @Transactional
    public void disassociateCollect(Long freightId, Long collectId){
        Freight freight = seekOrFail(freightId);
        AddressCollect addressCollect = collectService.seekOrFail(collectId);

        freight.disassociateCollect(addressCollect);
    }
    @Transactional
    public void associationDelivery(Long freightId, Long deliveryId){
        Freight freight = seekOrFail(freightId);
        AddressDelivery addressDelivery = deliveryService.seekOrFail(deliveryId);

        freight.associationDelivery(addressDelivery);
    }
    @Transactional
    public void disassociateDelivery(Long freightId, Long deliveryId){
        Freight freight = seekOrFail(freightId);
        AddressDelivery addressDelivery = deliveryService.seekOrFail(deliveryId);

        freight.disassociateDelivery(addressDelivery);
    }


}
