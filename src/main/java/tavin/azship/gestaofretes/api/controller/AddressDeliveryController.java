package tavin.azship.gestaofretes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.AddressDeliveryDTO;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;
import tavin.azship.gestaofretes.domain.service.AddressDeliveryService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/address_delivery")
public class AddressDeliveryController {

    @Autowired
    private AddressDeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<AddressDelivery>> getAll(@RequestParam(required = false) Boolean excludeActive){
        if (excludeActive != null && !excludeActive){
            return new ResponseEntity<>(deliveryService.getInactive(), HttpStatus.OK);
        }
        return new ResponseEntity<>(deliveryService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressDelivery> getById(@PathVariable Long id){
        return new ResponseEntity<>(deliveryService.seekOrFail(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AddressDelivery> create(@RequestBody AddressDeliveryDTO dto){
        return new ResponseEntity<>(deliveryService.create(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AddressDelivery> update(@PathVariable Long id, @RequestBody AddressDeliveryDTO dto){
        return new ResponseEntity<>(deliveryService.update(id, dto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        deliveryService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Long id){
        deliveryService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id){
        deliveryService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
