package tavin.azship.gestaofretes.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record FreightDTO (

        @NotNull(message = "Cliente não pode ser nulo")
        Long clientId,

        @NotNull(message = "Motorista não pode ser nulo")
        Long driverId,

        @NotNull(message = "Local de coleta não pode ser nulo")
        List<Long> addressCollectId,

        @NotNull(message = "Local de entrega não pode ser nulo")
        List<Long> addressDeliveryId,

        @NotNull(message = "Propriedades não pode ser nulo")
        Map<String, String>properties
){
}
