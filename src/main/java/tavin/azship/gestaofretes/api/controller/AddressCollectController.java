package tavin.azship.gestaofretes.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.AddressCollectDTO;
import tavin.azship.gestaofretes.api.dto.response.AddressCollectResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.AddressCollectUpdateDTO;
import tavin.azship.gestaofretes.domain.model.AddressCollect;
import tavin.azship.gestaofretes.domain.service.AddresCollectStatusService;
import tavin.azship.gestaofretes.domain.service.AddressCollectService;

import java.util.List;

@RestController
@RequestMapping("/api/address_collect")
@RequiredArgsConstructor
public class AddressCollectController {

    private final AddressCollectService collectService;

    @GetMapping
    public ResponseEntity<List<AddressCollectResponseDTO>> getAll(@RequestParam(required = false) Boolean excludeActive){
        if (excludeActive != null && excludeActive){
            return new ResponseEntity<>(collectService.getInactive(), HttpStatus.OK);
        }
        return new ResponseEntity<>(collectService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressCollectResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(collectService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressCollectResponseDTO> create(@Valid  @RequestBody AddressCollectDTO dto){
        return new ResponseEntity<>(collectService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressCollectResponseDTO> update(@PathVariable Long id,@Valid @RequestBody AddressCollectUpdateDTO dto){
        return new ResponseEntity<>(collectService.update(id, dto), HttpStatus.OK);
    }

}
