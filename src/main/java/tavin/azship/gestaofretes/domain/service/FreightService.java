package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.FreightDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.*;
import tavin.azship.gestaofretes.infra.FreightSpecs;
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

    @Autowired
    private DriverService driverService;


    public Page<Freight> getAll(FreightFilter filter,Pageable pageable){
        return this.freightRepository.findAll(FreightSpecs.usandoFiltro(filter),pageable);
    }

    public Freight seekOrFail(Long id) {
        return this.freightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.Type.ID,"Id não encontrado"));
    }

    @Transactional
    public Freight create (@Valid FreightDTO freightDTO) {

        Client client = this.clientService.seekOrFail(freightDTO.clientId());
        List<AddressDelivery> deliveries = this.deliveryService.seekOrFails(freightDTO.addressDeliveryId());
        List<AddressCollect> collects = this.collectService.seekOrFails(freightDTO.addressCollectId());
        Driver driver = this.driverService.seekOrFail(freightDTO.driverId());

        validatedFreight(collects, deliveries);

        Freight freight = new Freight(freightDTO);

        if (freightDTO.properties().isEmpty()) {
            throw new ResourceNotFoundException(ResourceNotFoundException.Type.PROPERTY, "Propriedades não informadas");
        }

        freight.setClient(client);
        freight.setAddressDelivery(deliveries);
        freight.setAddressCollect(collects);
        freight.setDriver(driver);

        return this.freightRepository.save(freight);
    }

    @Transactional
    public Freight update (Long id,@Valid FreightDTO freightDTO) {
        Freight freight = seekOrFail(id);
        if (freightDTO.clientId() != null){
            Client client = this.clientService.seekOrFail(freightDTO.clientId());
            freight.setClient(client);
        }
        if (freightDTO.driverId() != null){
            Driver driver = this.driverService.seekOrFail(freightDTO.driverId());
            freight.setDriver(driver);
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
    public void delete(Long id) {
        seekOrFail(id);
        this.freightRepository.deleteById(id);
    }


    private void validatedFreight(List<AddressCollect> collects, List<AddressDelivery> deliveries){
        for (AddressCollect collect : collects){
            if (!collect.isActive()){
                throw new ResourceNotFoundException(ResourceNotFoundException.Type.COLLECT, "Local de coleta inativo");
            }
        }
        for (AddressDelivery delivery : deliveries){
            if (!delivery.isActive()){
                throw new ResourceNotFoundException(ResourceNotFoundException.Type.DELIVERY, "Local de entrega inativo");
            }
        }
    }


}
