package tavin.azship.gestaofretes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tavin.azship.gestaofretes.model.Client;

import java.util.Map;

public record FreightDTO (
        @NotBlank
        Long clientId,
        @NotBlank
        Long addressCollectId,
        @NotBlank
        Long addressDeliveryId,
        @NotBlank
        Map<String, String>properties){
}
