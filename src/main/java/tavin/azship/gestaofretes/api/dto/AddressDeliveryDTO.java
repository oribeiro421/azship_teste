package tavin.azship.gestaofretes.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDeliveryDTO(

        @NotNull(message = "Rua não pode ser nulo")
        String road,

        @NotNull(message = "Bairro não pode ser nulo")
        String neighborhood,

        @NotNull(message = "Estado não pode ser nulo")
        String state,

        @NotBlank(message = "Numero não pode ser nulo")
        Integer number
) {
}
