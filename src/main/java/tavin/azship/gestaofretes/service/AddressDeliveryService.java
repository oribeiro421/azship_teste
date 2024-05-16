package tavin.azship.gestaofretes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.dto.AddressDeliveryDTO;
import tavin.azship.gestaofretes.handler.exception.IdNotFoundException;
import tavin.azship.gestaofretes.model.AddressDelivery;
import tavin.azship.gestaofretes.repository.AddressDeliveryRepository;

import java.util.List;

@Service
public class AddressDeliveryService {

    @Autowired
    private AddressDeliveryRepository deliveryRepository;

    public List<AddressDelivery> getAll(){
        return deliveryRepository.findAll();
    }
    public AddressDelivery seekOrFail(Long id){
        return deliveryRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    public AddressDelivery create(AddressDeliveryDTO dto){
        AddressDelivery delivery = new AddressDelivery(dto);
        return deliveryRepository.save(delivery);
    }
    public AddressDelivery update(Long id, AddressDeliveryDTO dto){
        seekOrFail(id);
        AddressDelivery delivery = new AddressDelivery(id, dto);
        return deliveryRepository.save(delivery);
    }
    public void delete(Long id){
        seekOrFail(id);
        deliveryRepository.deleteById(id);
    }
    public void active(Long id){
        AddressDelivery delivery = seekOrFail(id);
        delivery.active();
    }
    public void disable(Long id){
        AddressDelivery delivery = seekOrFail(id);
        delivery.disable();
    }
}
