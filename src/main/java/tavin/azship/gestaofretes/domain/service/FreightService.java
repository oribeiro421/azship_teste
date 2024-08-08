package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.FreightDTO;
import tavin.azship.gestaofretes.api.dto.update.FreightUpdateDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.*;
import tavin.azship.gestaofretes.infra.FreightSpecs;
import tavin.azship.gestaofretes.domain.repository.FreightRepository;
import tavin.azship.gestaofretes.domain.repository.filter.FreightFilter;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FreightService {

    private final FreightRepository freightRepository;
    private final ClientService clientService;
    private final AddressCollectService collectService;
    private final AddressDeliveryService deliveryService;
    private final DriverService driverService;


    public Page<Freight> getAll(FreightFilter filter,Pageable pageable){
        return this.freightRepository.findAll(FreightSpecs.usingFilter(filter),pageable);
    }

    public Freight seekOrFail(Long id) {
        return this.freightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.Type.ID,"Id não encontrado"));
    }

    @Transactional
    public Freight create (@Valid FreightDTO freightDTO) {
        Freight freight = new Freight();

        FreightDTO.toEntity(freightDTO,freight, clientService, driverService, collectService, deliveryService);

        return this.freightRepository.save(freight);
    }

    @Transactional
    public Freight update (Long id,@Valid FreightUpdateDTO freightUpdateDTO) {
        Freight freight = seekOrFail(id);

        FreightUpdateDTO.toEntityUpdate(freightUpdateDTO, freight,clientService,driverService,collectService,deliveryService);

        return this.freightRepository.save(freight);
    }


    @Transactional
    public void delete(Long id) {
        seekOrFail(id);
        this.freightRepository.deleteById(id);
    }


}
