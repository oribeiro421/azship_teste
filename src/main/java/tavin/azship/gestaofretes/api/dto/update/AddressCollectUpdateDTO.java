package tavin.azship.gestaofretes.api.dto.update;

import tavin.azship.gestaofretes.domain.model.AddressCollect;

import java.util.Optional;

public record AddressCollectUpdateDTO(

        String road,
        String neighborhood,
        String state,
        Integer number
) {

    public static void toEntityUpdate(AddressCollect addressCollect, AddressCollectUpdateDTO addressCollectDTO){

        Optional.ofNullable(addressCollectDTO.road()).ifPresent(addressCollect::setRoad);
        Optional.ofNullable(addressCollectDTO.neighborhood()).ifPresent(addressCollect::setNeighborhood);
        Optional.ofNullable(addressCollectDTO.state()).ifPresent(addressCollect::setState);
        Optional.ofNullable(addressCollectDTO.number()).ifPresent(addressCollect::setNumber);
    }
}
