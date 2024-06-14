package tavin.azship.gestaofretes.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

public record FreightDTO (

        @NotBlank(message = "Cliente não pode ser nulo")
        Long clientId,

        @NotBlank(message = "Motorista não pode ser nulo")
        Long driverId,

        @NotBlank(message = "Local de coleta não pode ser nulo")
        List<Long> addressCollectId,

        @NotBlank(message = "Local de entrega não pode ser nulo")
        List<Long> addressDeliveryId,

        @NotBlank(message = "Propriedades não pode ser nulo")
        Map<String, String>properties
){
}
