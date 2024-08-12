package tavin.azship.gestaofretes.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.AddressDeliveryDTO;
import tavin.azship.gestaofretes.api.dto.response.AddressDeliveryResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.AddressDeliveryUpdateDTO;
import tavin.azship.gestaofretes.domain.model.AddressDelivery;
import tavin.azship.gestaofretes.domain.service.AddresDeliveryStatusService;
import tavin.azship.gestaofretes.domain.service.AddressDeliveryService;

import java.util.List;

@RestController
@RequestMapping("/api/address_delivery")
@RequiredArgsConstructor
public class AddressDeliveryController {

    private final AddressDeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<AddressDeliveryResponseDTO>> getAll(@RequestParam(required = false) Boolean excludeActive){
        if (excludeActive != null && excludeActive){
            return new ResponseEntity<>(deliveryService.getInactive(), HttpStatus.OK);
        }
        return new ResponseEntity<>(deliveryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDeliveryResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(deliveryService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDeliveryResponseDTO> create(@Valid @RequestBody AddressDeliveryDTO dto){
        return new ResponseEntity<>(deliveryService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDeliveryResponseDTO> update(@PathVariable Long id,@Valid @RequestBody AddressDeliveryUpdateDTO dto){
        return new ResponseEntity<>(deliveryService.update(id, dto), HttpStatus.OK);
    }

}
