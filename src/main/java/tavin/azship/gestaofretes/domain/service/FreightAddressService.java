package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.domain.model.AddressCollect;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;
import tavin.azship.gestaofretes.domain.model.Freight;

@Service
public class FreightAddressService {

    @Autowired
    private FreightService freightService;

    @Autowired
    private AddressCollectService collectService;

    @Autowired
    private AddressDeliveryService deliveryService;

    @Transactional
    public void associationCollect(Long freightId, Long collectId){
        Freight freight = freightService.seekOrFail(freightId);
        AddressCollect addressCollect = collectService.seekOrFail(collectId);

        freight.associationCollect(addressCollect);
    }

    @Transactional
    public void disassociateCollect(Long freightId, Long collectId){
        Freight freight = freightService.seekOrFail(freightId);
        AddressCollect addressCollect = collectService.seekOrFail(collectId);

        freight.disassociateCollect(addressCollect);
    }

    @Transactional
    public void associationDelivery(Long freightId, Long deliveryId){
        Freight freight = freightService.seekOrFail(freightId);
        AddressDelivery addressDelivery = deliveryService.seekOrFail(deliveryId);

        freight.associationDelivery(addressDelivery);
    }

    @Transactional
    public void disassociateDelivery(Long freightId, Long deliveryId){
        Freight freight = freightService.seekOrFail(freightId);
        AddressDelivery addressDelivery = deliveryService.seekOrFail(deliveryId);

        freight.disassociateDelivery(addressDelivery);
    }
}
