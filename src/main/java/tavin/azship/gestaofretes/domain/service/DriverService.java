package tavin.azship.gestaofretes.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.DriverDTO;
import tavin.azship.gestaofretes.api.dto.update.DriverUpdateDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.Driver;
import tavin.azship.gestaofretes.domain.repository.DriverRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> getAll(){
        return driverRepository.findAll();
    }

    public Driver seekOrFail(Long id){
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.Type.ID, "Id não encontrado"));
    }

    public Driver create(@Valid DriverDTO dto){
        Driver driver = new Driver();

        DriverDTO.toEntity(driver, dto);

        return driverRepository.save(driver);
    }

    public Driver update(Long id, @Valid DriverUpdateDTO dto){
        Driver driver = seekOrFail(id);

        DriverUpdateDTO.toEntityUpdate(driver, dto);

        return driverRepository.save(driver);
    }

    public void delete(Long id){
        seekOrFail(id);
        driverRepository.deleteById(id);
    }

}
