package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;

@Service
@RequiredArgsConstructor
public class AddresDeliveryStatusService {

    private final AddressDeliveryService addressDeliveryService;


    @Transactional
    public void active(Long id){
        AddressDelivery delivery = addressDeliveryService.seekOrFail(id);
        delivery.active();
    }

    @Transactional
    public void disable(Long id){
        AddressDelivery delivery = addressDeliveryService.seekOrFail(id);
        delivery.disable();
    }
}
