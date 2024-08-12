package tavin.azship.gestaofretes.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.DriverDTO;
import tavin.azship.gestaofretes.api.dto.response.DriverResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.DriverUpdateDTO;
import tavin.azship.gestaofretes.domain.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverResponseDTO>> getAll(){
        return new ResponseEntity<>(driverService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(driverService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DriverResponseDTO> create(@Valid @RequestBody DriverDTO dto){
        return new ResponseEntity<>(driverService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> update(@PathVariable Long id,@Valid @RequestBody DriverUpdateDTO dto){
        return new ResponseEntity<>(driverService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        driverService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
