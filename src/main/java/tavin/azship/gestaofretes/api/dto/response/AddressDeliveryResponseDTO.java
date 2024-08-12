package tavin.azship.gestaofretes.api.dto.response;

import jakarta.persistence.Column;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;

import java.util.Optional;

public record AddressDeliveryResponseDTO(

        Long id,
        String road,
        String neighborhood,
        String state,
        Integer number,
        Boolean active
) {

    public static AddressDeliveryResponseDTO fromEntity(AddressDelivery addressDelivery){
        return new AddressDeliveryResponseDTO(
                addressDelivery.getId(),
                addressDelivery.getRoad(),
                addressDelivery.getNeighborhood(),
                addressDelivery.getState(),
                addressDelivery.getNumber(),
                addressDelivery.getActive()
        );
    }
}
