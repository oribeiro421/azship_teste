package tavin.azship.gestaofretes.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tavin.azship.gestaofretes.domain.model.Freight;
import tavin.azship.gestaofretes.domain.repository.FreightRepository;

@Service
@RequiredArgsConstructor
public class FlowFreightService {

    private final FreightRepository freightRepository;
    private final FreightService freightService;

    @Transactional
    public void confirmed(Long id){
        Freight freight = freightService.seekOrFail(id);

        freight.confirmed();

        freightRepository.save(freight);
    }

    @Transactional
    public void inTransit(Long id){
        Freight freight = freightService.seekOrFail(id);

        freight.inTransit();

        freightRepository.save(freight);
    }

    @Transactional
    public void delivered(Long id){
        Freight freight = freightService.seekOrFail(id);

        freight.delivered();
    }

    @Transactional
    public void canceled(Long id){
        Freight freight = freightService.seekOrFail(id);

        freight.canceled();

        freightRepository.save(freight);
    }
}
