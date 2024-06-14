package tavin.azship.gestaofretes.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tavin.azship.gestaofretes.domain.model.Freight;

import java.util.List;

public record ClientDTO (

        List<Freight> freights,

        @NotNull(message = "Cliente deve ter um cnpj")
        String cnpj,

        @NotNull(message = "Cliente deve ter um email")
        @Email(message = "Formato de email invalido")
        String email) {
}
