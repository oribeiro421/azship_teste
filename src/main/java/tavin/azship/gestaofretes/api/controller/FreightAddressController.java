package tavin.azship.gestaofretes.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.domain.service.FreightAddressService;

@RestController
@RequestMapping("/api/freight/{freightId}/address")
@RequiredArgsConstructor
public class FreightAddressController {

    private final FreightAddressService freightAddress;

    @PutMapping("/collect/{collectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associationCollect(@PathVariable Long freightId, @PathVariable Long collectId){
        freightAddress.associationCollect(freightId, collectId);
    }
    @DeleteMapping("/collect/{collectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateCollect(@PathVariable Long freightId, @PathVariable Long collectId){
        freightAddress.disassociateCollect(freightId, collectId);
    }
    @PutMapping("/delivery/{deliveryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associationDelivery(@PathVariable Long freightId, @PathVariable Long deliveryId){
        freightAddress.associationDelivery(freightId, deliveryId);
    }
    @DeleteMapping("/delivery/{deliveryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateDelivery(@PathVariable Long freightId, @PathVariable Long deliveryId){
        freightAddress.disassociateDelivery(freightId, deliveryId);
    }
}
