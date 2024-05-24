package tavin.azship.gestaofretes.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tavin.azship.gestaofretes.domain.model.Freight;
import tavin.azship.gestaofretes.domain.repository.FreightRepository;

@Service
public class FlowFreightService {

    @Autowired
    private FreightRepository freightRepository;

    @Autowired
    private FreightService freightService;

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
