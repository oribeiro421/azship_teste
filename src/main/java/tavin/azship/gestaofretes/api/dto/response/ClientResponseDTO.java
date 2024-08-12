package tavin.azship.gestaofretes.api.dto.response;

import tavin.azship.gestaofretes.domain.model.Client;
import tavin.azship.gestaofretes.domain.model.Freight;

import java.util.List;

public record ClientResponseDTO(

        Long id,
        List<Freight> freights,
        String cnpj,
        String email,
        Boolean active
) {

        public static ClientResponseDTO fromEntity(Client client){
                return new ClientResponseDTO(
                        client.getId(),
                        client.getFreights(),
                        client.getCnpj(),
                        client.getEmail(),
                        client.getActive()
                );
        }


}
