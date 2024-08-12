package tavin.azship.gestaofretes.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.DriverDTO;
import tavin.azship.gestaofretes.api.dto.response.DriverResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.DriverUpdateDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.Driver;
import tavin.azship.gestaofretes.domain.repository.DriverRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<DriverResponseDTO> getAll(){
        List<Driver> drivers = this.driverRepository.findAll();
        return drivers.stream().map(DriverResponseDTO::fromEntity).toList();
    }

    public DriverResponseDTO getById(Long id){
        Driver driver = seekOrFail(id);
        return DriverResponseDTO.fromEntity(driver);
    }

    public DriverResponseDTO create(@Valid DriverDTO dto){
        Driver driver = new Driver();

        DriverDTO.toEntity(driver, dto);

        driverRepository.save(driver);

        return DriverResponseDTO.fromEntity(driver);
    }

    public DriverResponseDTO update(Long id, @Valid DriverUpdateDTO dto){
        Driver driver = seekOrFail(id);

        DriverUpdateDTO.toEntityUpdate(driver, dto);

        driverRepository.save(driver);

        return DriverResponseDTO.fromEntity(driver);
    }

    public void delete(Long id){
        seekOrFail(id);
        driverRepository.deleteById(id);
    }

    public Driver seekOrFail(Long id){
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.Type.ID, "Id não encontrado"));
    }
}
