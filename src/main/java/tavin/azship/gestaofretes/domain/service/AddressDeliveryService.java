package tavin.azship.gestaofretes.domain.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.AddressDeliveryDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;
import tavin.azship.gestaofretes.domain.repository.AddressDeliveryRepository;

import java.util.List;

@Service
public class AddressDeliveryService {

    @Autowired
    private AddressDeliveryRepository deliveryRepository;

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

    public List<AddressDelivery> seekOrFails(List<Long> id){
        List<AddressDelivery> deliveries = deliveryRepository.findByIdIn(id);
        if (deliveries.isEmpty() || deliveries.size() != id.size()){
            throw new ResourceNotFoundException (ResourceNotFoundException.Type.ID, "Id não encontrado");
        }
        return deliveries;
    }

    @Transactional
    public AddressDelivery create(AddressDeliveryDTO dto){
        AddressDelivery delivery = new AddressDelivery(dto);
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public AddressDelivery update(Long id, AddressDeliveryDTO dto){
        seekOrFail(id);
        AddressDelivery delivery = new AddressDelivery(id, dto);
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public void delete(Long id){
        seekOrFail(id);
        deliveryRepository.deleteById(id);
    }

    @Transactional
    public void active(Long id){
        AddressDelivery delivery = seekOrFail(id);
        delivery.active();
    }

    @Transactional
    public void disable(Long id){
        AddressDelivery delivery = seekOrFail(id);
        delivery.disable();
    }
}
