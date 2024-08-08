package tavin.azship.gestaofretes.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tavin.azship.gestaofretes.api.dto.AddressCollectDTO;
import tavin.azship.gestaofretes.api.dto.update.AddressCollectUpdateDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.AddressCollect;
import tavin.azship.gestaofretes.domain.repository.AddressCollectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressCollectService {

    private final AddressCollectRepository collectRepository;

    public List<AddressCollect> getAll(){
        return collectRepository.findAll();
    }

    public List<AddressCollect> getInactive(){
        return collectRepository.findByInactive();
    }

    public AddressCollect seekOrFail(Long id){
        return collectRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException (ResourceNotFoundException.Type.ID, "Id não encontrado"));
    }

    public List<AddressCollect> seekOrFails(List<Long> ids){
        List<AddressCollect> collects = collectRepository.findByIdIn(ids);

        ids.forEach(id -> {
            if (collects.stream().noneMatch(collect -> collect.getId().equals(id))){
                throw new ResourceNotFoundException(ResourceNotFoundException.Type.ID, "Id não encontrado: " + id);
            }
        });

        return collects;
    }

    @Transactional
    public AddressCollect create(@Valid AddressCollectDTO dto){
        AddressCollect addressCollect = new AddressCollect();

        AddressCollectDTO.toEntity(addressCollect, dto);

        return collectRepository.save(addressCollect);
    }

    @Transactional
    public AddressCollect update(Long id,@Valid AddressCollectUpdateDTO dto){
        AddressCollect addressCollect = seekOrFail(id);

        AddressCollectUpdateDTO.toEntityUpdate(addressCollect, dto);

        return collectRepository.save(addressCollect);
    }

}
