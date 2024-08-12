package tavin.azship.gestaofretes.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tavin.azship.gestaofretes.domain.model.Freight;
import tavin.azship.gestaofretes.domain.service.AddressCollectService;
import tavin.azship.gestaofretes.domain.service.AddressDeliveryService;
import tavin.azship.gestaofretes.domain.service.ClientService;
import tavin.azship.gestaofretes.domain.service.DriverService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        public static void toEntity(FreightDTO dto, Freight freight, ClientService clientService,
                                       DriverService driverService, AddressCollectService collectService,
                                       AddressDeliveryService deliveryService){

                freight.setClient(clientService.seekOrFail(dto.clientId()));
                freight.setDriver(driverService.seekOrFail(dto.driverId()));
                freight.setAddressCollect(collectService.seekOrFails(dto.addressCollectId()));
                freight.setAddressDelivery(deliveryService.seekOrFails(dto.addressDeliveryId()));
                freight.setProperties(dto.properties());
        }

}
