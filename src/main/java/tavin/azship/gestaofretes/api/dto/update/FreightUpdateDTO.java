package tavin.azship.gestaofretes.api.dto.update;


import tavin.azship.gestaofretes.domain.model.Freight;
import tavin.azship.gestaofretes.domain.service.AddressCollectService;
import tavin.azship.gestaofretes.domain.service.AddressDeliveryService;
import tavin.azship.gestaofretes.domain.service.ClientService;
import tavin.azship.gestaofretes.domain.service.DriverService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record FreightUpdateDTO(

        Long clientId,

        Long driverId,

        List<Long> addressCollectId,

        List<Long> addressDeliveryId,

        Map<String, String> properties
) {

    public static void toEntityUpdate(FreightUpdateDTO dto, Freight freight,
                                      ClientService clientService, DriverService driverService,
                                      AddressCollectService addressCollectService, AddressDeliveryService addressDeliveryService){

        Optional.ofNullable(dto.clientId)
                .map(clientService::seekOrFail)
                .ifPresent(freight::setClient);

        Optional.ofNullable(dto.driverId)
                .map(driverService::seekOrFail)
                .ifPresent(freight::setDriver);

        Optional.ofNullable(dto.addressCollectId)
                .map(ids -> ids.stream()
                        .map(addressCollectService::seekOrFail)
                        .toList())
                .ifPresent(freight::setAddressCollect);

        Optional.ofNullable(dto.addressDeliveryId)
                .map(ids -> ids.stream()
                        .map(addressDeliveryService::seekOrFail)
                        .toList())
                .ifPresent(freight::setAddressDelivery);

        Optional.ofNullable(dto.properties).ifPresent(freight::setProperties);

    }

}
