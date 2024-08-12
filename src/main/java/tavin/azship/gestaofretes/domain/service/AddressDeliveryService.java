package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.AddressDeliveryDTO;
import tavin.azship.gestaofretes.api.dto.response.AddressDeliveryResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.AddressDeliveryUpdateDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;
import tavin.azship.gestaofretes.domain.repository.AddressDeliveryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressDeliveryService {

    private final AddressDeliveryRepository deliveryRepository;

    public List<AddressDeliveryResponseDTO> getAll(){
        List<AddressDelivery> AddressDelivery = deliveryRepository.findAll();
        return AddressDelivery.stream().map(AddressDeliveryResponseDTO::fromEntity).toList();
    }

    public List<AddressDeliveryResponseDTO> getInactive(){
        List<AddressDelivery> AddressDelivery = deliveryRepository.findByInactive();
        return AddressDelivery.stream().map(AddressDeliveryResponseDTO::fromEntity).toList();
    }

    public AddressDeliveryResponseDTO getById(Long id){
        AddressDelivery addressDelivery = seekOrFail(id);
        return AddressDeliveryResponseDTO.fromEntity(addressDelivery);
    }

    @Transactional
    public AddressDeliveryResponseDTO create(@Valid AddressDeliveryDTO dto){
        AddressDelivery delivery = new AddressDelivery();

        AddressDeliveryDTO.toEntity(delivery, dto);

        deliveryRepository.save(delivery);

        return AddressDeliveryResponseDTO.fromEntity(delivery);
    }

    @Transactional
    public AddressDeliveryResponseDTO update(Long id,@Valid AddressDeliveryUpdateDTO dto){
        AddressDelivery delivery = seekOrFail(id);

        AddressDeliveryUpdateDTO.toEntityUpdate(delivery, dto);

        deliveryRepository.save(delivery);

        return AddressDeliveryResponseDTO.fromEntity(delivery);
    }

    public AddressDelivery seekOrFail(Long id){
        return deliveryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException (ResourceNotFoundException.Type.ID, "Id não encontrado"));
    }

    public List<AddressDelivery> seekOrFails(List<Long> ids){
        List<AddressDelivery> deliveries = deliveryRepository.findByIdIn(ids);

        ids.forEach(id -> {
            if (deliveries.stream().noneMatch(delivery -> delivery.getId().equals(id))){
                throw new ResourceNotFoundException(ResourceNotFoundException.Type.ID, "Id não encontrado: " + id);
            }
        });

        return deliveries;
    }

}
