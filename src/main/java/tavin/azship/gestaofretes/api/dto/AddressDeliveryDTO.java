package tavin.azship.gestaofretes.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;

public record AddressDeliveryDTO(

        @NotBlank(message = "Rua não pode estar em branco")
        String road,

        @NotBlank(message = "Bairro não pode estar em branco")
        String neighborhood,

        @NotBlank(message = "Estado não pode estar em branco")
        String state,

        @NotNull(message = "Numero não pode ser nulo")
        Integer number
) {

        public static void toEntity(AddressDelivery addressDelivery, AddressDeliveryDTO dto){

                addressDelivery.setRoad(dto.road());
                addressDelivery.setNeighborhood(dto.neighborhood());
                addressDelivery.setState(dto.state());
                addressDelivery.setNumber(dto.number());
        }
}
