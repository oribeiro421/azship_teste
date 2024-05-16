package tavin.azship.gestaofretes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressCollectDTO(
        @NotNull
        String road,
        @NotNull
        String neighborhood,
        @NotNull
        String state,
        @NotBlank
        Integer number) {
}
