package tavin.azship.gestaofretes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tavin.azship.gestaofretes.model.Freight;

import java.util.List;

public record ClientDTO (
        List<Freight> freights,
        @NotNull(message = "Cliente deve ter um cnpj")
        String cnpj,
        @NotBlank(message = "Cliente deve ter um email")
        @Email
        String email) {
}
