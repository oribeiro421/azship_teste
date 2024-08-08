package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.domain.model.AddressCollect;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;

@Service
@RequiredArgsConstructor
public class AddresCollectStatusService {

    private final AddressCollectService addressCollectService;


    @Transactional
    public void active(Long id){
        AddressCollect collect = addressCollectService.seekOrFail(id);
        collect.active();
    }

    @Transactional
    public void disable(Long id){
        AddressCollect collect = addressCollectService.seekOrFail(id);
        collect.disable();
    }
}
