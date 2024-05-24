package tavin.azship.gestaofretes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.domain.service.FreightService;

@RestController
@RequestMapping("/api/freight/{freightId}/address")
public class FreightAddressController {

    @Autowired
    private FreightService freightService;

    @PutMapping("/{collectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associationCollect(@PathVariable Long freightId, @PathVariable Long collectId){
        freightService.associationCollect(freightId, collectId);
    }
    @DeleteMapping("/{collectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateCollect(@PathVariable Long freightId, @PathVariable Long collectId){
        freightService.disassociateCollect(freightId, collectId);
    }
    @PutMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associationDelivery(@PathVariable Long freightId, @PathVariable Long deliveryId){
        freightService.associationDelivery(freightId, deliveryId);
    }
    @DeleteMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateDelivery(@PathVariable Long freightId, @PathVariable Long deliveryId){
        freightService.disassociateDelivery(freightId, deliveryId);
    }
}
