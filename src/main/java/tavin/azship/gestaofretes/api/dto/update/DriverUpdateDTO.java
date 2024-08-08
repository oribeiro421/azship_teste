package tavin.azship.gestaofretes.api.dto.update;

import tavin.azship.gestaofretes.domain.model.Driver;

import java.util.Optional;

public record DriverUpdateDTO(

        String name,

        String cpf,

        String licenseNumber,

        String birthDate
) {

    public static void toEntityUpdate(Driver driver, DriverUpdateDTO dto){

        Optional.ofNullable(dto.name).ifPresent(driver::setName);
        Optional.ofNullable(dto.cpf).ifPresent(driver::setCpf);
        Optional.ofNullable(dto.licenseNumber).ifPresent(driver::setLicenseNumber);
        Optional.ofNullable(dto.birthDate).ifPresent(driver::setBirthDate);
    }
}
