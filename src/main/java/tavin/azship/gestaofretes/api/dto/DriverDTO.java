package tavin.azship.gestaofretes.api.dto;


import jakarta.validation.constraints.NotBlank;
import tavin.azship.gestaofretes.domain.model.Driver;

import java.util.List;
import java.util.Optional;

public record DriverDTO (

        @NotBlank(message = "O nome não pode estar em branco")
        String name,

        @NotBlank(message = "O cpf não pode estar em branco")
        String cpf,

        @NotBlank(message = "A cnh não pode estar em branco")
        String licenseNumber,

        @NotBlank(message = "A data não pode estar em branco")
        String birthDate
) {

        public static void toEntity(Driver driver, DriverDTO dto){

                driver.setName(dto.name());
                driver.setCpf(dto.cpf());
                driver.setLicenseNumber(dto.licenseNumber());
                driver.setBirthDate(dto.birthDate());

        }

}
