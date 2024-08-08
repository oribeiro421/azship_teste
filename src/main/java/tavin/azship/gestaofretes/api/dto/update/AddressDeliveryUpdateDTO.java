package tavin.azship.gestaofretes.api.dto.update;

import tavin.azship.gestaofretes.domain.model.AddressDelivery;

import java.util.Optional;

public record AddressDeliveryUpdateDTO(

        String road,
        String neighborhood,
        String state,
        Integer number
) {

    public static void toEntityUpdate(AddressDelivery addressDelivery, AddressDeliveryUpdateDTO addressDeliveryDTO){

        Optional.ofNullable(addressDeliveryDTO.road()).ifPresent(addressDelivery::setRoad);
        Optional.ofNullable(addressDeliveryDTO.neighborhood()).ifPresent(addressDelivery::setNeighborhood);
        Optional.ofNullable(addressDeliveryDTO.state()).ifPresent(addressDelivery::setState);
        Optional.ofNullable(addressDeliveryDTO.number()).ifPresent(addressDelivery::setNumber);
    }
}
