package tavin.azship.gestaofretes.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tavin.azship.gestaofretes.domain.model.AddressCollect;

public record AddressCollectDTO(

        @NotBlank(message = "Rua não pode estar em branco")
        String road,

        @NotBlank(message = "Bairro não pode estar em branco")
        String neighborhood,

        @NotBlank(message = "Estado não pode estar em branco")
        String state,

        @NotNull(message = "Numero não pode ser nulo")
        Integer number
) {

        public static void toEntity(AddressCollect addressCollect, AddressCollectDTO dto){

                addressCollect.setRoad(dto.road());
                addressCollect.setNeighborhood(dto.neighborhood());
                addressCollect.setState(dto.state());
                addressCollect.setNumber(dto.number());
        }

}
