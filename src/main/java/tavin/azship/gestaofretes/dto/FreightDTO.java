package tavin.azship.gestaofretes.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

public record FreightDTO (
        @NotBlank
        Long clientId,
        @NotBlank
        List<Long> addressCollectId,
        @NotBlank
        List<Long> addressDeliveryId,
        @NotBlank
        Map<String, String>properties){
}
