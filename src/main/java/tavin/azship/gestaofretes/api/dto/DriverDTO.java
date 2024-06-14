package tavin.azship.gestaofretes.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DriverDTO (

        @NotBlank(message = "O nome não pode estar em branco")
        String name,

        @NotBlank(message = "O cpf não pode estar em branco")
        String cpf,

        @NotBlank(message = "A cnh não pode estar em branco")
        String licenseNumber,

        @NotBlank(message = "A data não pode estar em branco")
        String birthDate,

        List<Long> freightsId
) {
}
