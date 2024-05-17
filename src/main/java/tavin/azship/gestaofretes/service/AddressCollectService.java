package tavin.azship.gestaofretes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tavin.azship.gestaofretes.dto.AddressCollectDTO;
import tavin.azship.gestaofretes.handler.exception.IdNotFoundException;
import tavin.azship.gestaofretes.model.AddressCollect;
import tavin.azship.gestaofretes.model.AddressDelivery;
import tavin.azship.gestaofretes.repository.AddressCollectRepository;

import java.util.List;

@Service
public class AddressCollectService {

    @Autowired
    private AddressCollectRepository collectRepository;

    public List<AddressCollect> getAll(){
        return collectRepository.findAll();
    }
    public AddressCollect seekOrFail(Long id){
        return collectRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id não encontrado"));
    }
    public List<AddressCollect> seekOrFails(List<Long> id){
        List<AddressCollect> collects = collectRepository.findByIdIn(id);
        if (collects.isEmpty() || collects.size() != id.size()){
            throw new IdNotFoundException("Id não encontrado");
        }
        return collects;
    }
    @Transactional
    public AddressCollect create(AddressCollectDTO dto){
        AddressCollect addressCollect = new AddressCollect(dto);
        return collectRepository.save(addressCollect);
    }
    @Transactional
    public AddressCollect update(Long id, AddressCollectDTO dto){
        seekOrFail(id);
        AddressCollect addressCollect = new AddressCollect(id, dto);
        return collectRepository.save(addressCollect);
    }
    @Transactional
    public void delete(Long id){
        seekOrFail(id);
        collectRepository.deleteById(id);
    }
    @Transactional
    public void active(Long id){
        AddressCollect collect = seekOrFail(id);
        collect.active();
    }
    @Transactional
    public void disable(Long id){
        AddressCollect collect = seekOrFail(id);
        collect.disable();
    }
}
