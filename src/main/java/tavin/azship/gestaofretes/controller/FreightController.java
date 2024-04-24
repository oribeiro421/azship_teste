package tavin.azship.gestaofretes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.dto.FreightDTO;
import tavin.azship.gestaofretes.model.Freight;
import tavin.azship.gestaofretes.service.FreightService;

import java.util.List;

@RestController
@RequestMapping("/api/freight")
public class FreightController {

    @Autowired
    private FreightService freightService;

    @GetMapping
    public ResponseEntity<Page<Freight>> getAll(Pageable pegeagle){
        return new ResponseEntity<>(this.freightService.getAll(pegeagle), HttpStatus.OK);
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<Freight> getByCodigo(@PathVariable String codigo){
        return new ResponseEntity<>(this.freightService.getByCodigo(codigo), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Freight> create(@RequestBody FreightDTO data){
        Freight freight = this.freightService.create(data);
        return new ResponseEntity<>(freight, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Freight> update(@PathVariable Long id, @RequestBody FreightDTO data){
        Freight freight = this.freightService.update(id, data);
        return new ResponseEntity<>(freight, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.freightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
