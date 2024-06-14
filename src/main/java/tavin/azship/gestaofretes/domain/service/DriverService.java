package tavin.azship.gestaofretes.domain.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tavin.azship.gestaofretes.api.dto.DriverDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceEmptyException;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;
import tavin.azship.gestaofretes.domain.model.Driver;
import tavin.azship.gestaofretes.domain.repository.DriverRepository;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getAll(){
        return driverRepository.findAll();
    }

    public Driver seekOrFail(Long id){
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.Type.ID, "Id não encontrado"));
    }

    public Driver create(@Valid DriverDTO dto){
        validate(dto);
        Driver driver = new Driver(dto);
        return driverRepository.save(driver);
    }

    public Driver update(Long id, @Valid DriverDTO dto){
        seekOrFail(id);
        Driver driver = new Driver(id, dto);
        return driverRepository.save(driver);
    }

    public void delete(Long id){
        seekOrFail(id);
        driverRepository.deleteById(id);
    }

    private void validate(DriverDTO dto){
        if (dto.name().isEmpty()){
            throw new ResourceEmptyException(ResourceEmptyException.Type.NAME, "Nome esta vazio");
        }
        if (dto.cpf().isEmpty()){
            throw new ResourceEmptyException(ResourceEmptyException.Type.CPF, "Cpf esta vazio");
        }
        if (dto.licenseNumber().isEmpty()){
            throw new ResourceEmptyException(ResourceEmptyException.Type.LICENSE_NUMBER, "Numero da cnh esta vazia");
        }
        if (dto.birthDate().isEmpty()){
            throw new ResourceEmptyException(ResourceEmptyException.Type.BIRTH_DATE, "Data de nascimento esta vazia");
        }
    }
}
