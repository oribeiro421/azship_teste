package tavin.azship.gestaofretes.api.dto.response;

import tavin.azship.gestaofretes.domain.model.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public record FreightResponseDTO(

        Long id,
        List<AddressCollect> addressCollect,
        List<AddressDelivery> addressDelivery,
        OffsetDateTime creationDate,
        OffsetDateTime confirmed,
        OffsetDateTime inTransit,
        OffsetDateTime delivered,
        OffsetDateTime canceled,
        Driver driver,
        StatusFreight status,
        Map<String, String> properties
) {

    public static FreightResponseDTO fromEntity(Freight freight){
        return new FreightResponseDTO(
                freight.getId(),
                freight.getAddressCollect(),
                freight.getAddressDelivery(),
                freight.getCreationDate(),
                freight.getConfirmed(),
                freight.getInTransit(),
                freight.getDelivered(),
                freight.getCanceled(),
                freight.getDriver(),
                freight.getStatus(),
                freight.getProperties()
        );
    }
}
