package tavin.azship.gestaofretes.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.domain.service.AddresDeliveryStatusService;

@RestController
@RequestMapping("/api/address_delivery/status")
@RequiredArgsConstructor
public class AddresDeliveryStatusController {

    private final AddresDeliveryStatusService addresDeliveryStatusService;

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Long id){
        addresDeliveryStatusService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id){
        addresDeliveryStatusService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
