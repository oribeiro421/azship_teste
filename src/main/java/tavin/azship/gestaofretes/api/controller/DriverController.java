package tavin.azship.gestaofretes.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.DriverDTO;
import tavin.azship.gestaofretes.api.dto.update.DriverUpdateDTO;
import tavin.azship.gestaofretes.domain.model.Driver;
import tavin.azship.gestaofretes.domain.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Driver>> getAll(){
        return new ResponseEntity<>(driverService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getById(@PathVariable Long id){
        return new ResponseEntity<>(driverService.seekOrFail(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Driver> create(@Valid @RequestBody DriverDTO dto){
        Driver driver = driverService.create(dto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> update(@PathVariable Long id,@Valid @RequestBody DriverUpdateDTO dto){
        Driver driver = driverService.update(id, dto);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        driverService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
