package tavin.azship.gestaofretes.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.ClientDTO;
import tavin.azship.gestaofretes.api.dto.response.ClientResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.ClientUpdateDTO;
import tavin.azship.gestaofretes.domain.model.Client;
import tavin.azship.gestaofretes.domain.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAll(@RequestParam(required = false) Boolean excludeActive){
        if (excludeActive != null && excludeActive){
            return new ResponseEntity<>(this.clientService.getInactive(), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.clientService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.clientService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody @Valid ClientDTO data) {
        return new ResponseEntity<>(this.clientService.create(data),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id,@RequestBody @Valid ClientUpdateDTO data) {
        return new ResponseEntity<>(this.clientService.update(id, data),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
