package tavin.azship.tentativa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.tentativa.dto.FreightDTO;
import tavin.azship.tentativa.model.Freight;
import tavin.azship.tentativa.service.FreightService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/freight")
public class FreightController {

    @Autowired
    private FreightService freightService;

    @GetMapping
    public ResponseEntity<List<Freight>> getAll(){
        return new ResponseEntity<>(this.freightService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Freight> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.freightService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Freight> create(@RequestBody FreightDTO data) throws Exception {
        Freight freight = this.freightService.create(data);
        return new ResponseEntity<>(freight, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Freight> update(@PathVariable Long id, @RequestBody FreightDTO data) throws Exception {
        Freight freight = this.freightService.update(id, data);
        return new ResponseEntity<>(freight, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        this.freightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
