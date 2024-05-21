package tavin.azship.gestaofretes.service;

import jakarta.transaction.Transactional;
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
    public List<AddressDelivery> getInactive(){
        return deliveryRepository.findByInactive();
    }
    public AddressDelivery seekOrFail(Long id){
        return deliveryRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    public List<AddressDelivery> seekOrFails(List<Long> id){
        List<AddressDelivery> deliveries = deliveryRepository.findByIdIn(id);
        if (deliveries.isEmpty() || deliveries.size() != id.size()){
            throw new IdNotFoundException("Id não encontrado");
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
