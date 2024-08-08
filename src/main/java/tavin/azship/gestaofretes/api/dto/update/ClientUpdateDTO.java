package tavin.azship.gestaofretes.api.dto.update;


import tavin.azship.gestaofretes.api.dto.ClientDTO;
import tavin.azship.gestaofretes.domain.model.Client;

import java.util.Optional;

public record ClientUpdateDTO(

        String cnpj,

        String email
) {

    public static void toEntityUpdate(Client client, ClientUpdateDTO dto){

        Optional.ofNullable(dto.cnpj()).ifPresent(client::setCnpj);
        Optional.ofNullable(dto.email()).ifPresent(client::setEmail);
    }
}
