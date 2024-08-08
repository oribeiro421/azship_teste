package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.AddressDeliveryDTO;
import tavin.azship.gestaofretes.api.dto.update.AddressDeliveryUpdateDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;
import tavin.azship.gestaofretes.domain.repository.AddressDeliveryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressDeliveryService {

    private final AddressDeliveryRepository deliveryRepository;

    public List<AddressDelivery> getAll(){
        return deliveryRepository.findAll();
    }

    public List<AddressDelivery> getInactive(){
        return deliveryRepository.findByInactive();
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

    @Transactional
    public AddressDelivery create(@Valid AddressDeliveryDTO dto){
        AddressDelivery delivery = new AddressDelivery();

        AddressDeliveryDTO.toEntity(delivery, dto);

        return deliveryRepository.save(delivery);
    }

    @Transactional
    public AddressDelivery update(Long id,@Valid AddressDeliveryUpdateDTO dto){
        AddressDelivery delivery = seekOrFail(id);

        AddressDeliveryUpdateDTO.toEntityUpdate(delivery, dto);

        return deliveryRepository.save(delivery);
    }


}
