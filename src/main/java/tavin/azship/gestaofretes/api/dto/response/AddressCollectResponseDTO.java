package tavin.azship.gestaofretes.api.dto.response;

import tavin.azship.gestaofretes.domain.model.AddressCollect;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;

import java.util.Optional;

public record AddressCollectResponseDTO(

        Long id,
        String road,
        String neighborhood,
        String state,
        Integer number,
        Boolean active
) {

    public static AddressCollectResponseDTO fromEntity(AddressCollect addressCollect){
        return new AddressCollectResponseDTO(
                addressCollect.getId(),
                addressCollect.getRoad(),
                addressCollect.getNeighborhood(),
                addressCollect.getState(),
                addressCollect.getNumber(),
                addressCollect.getActive()
        );
    }
}
