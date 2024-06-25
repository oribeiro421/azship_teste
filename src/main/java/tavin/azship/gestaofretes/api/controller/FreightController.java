package tavin.azship.gestaofretes.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.FreightDTO;
import tavin.azship.gestaofretes.domain.model.Freight;
import tavin.azship.gestaofretes.domain.repository.filter.FreightFilter;
import tavin.azship.gestaofretes.domain.service.FreightService;

@CrossOrigin
@RestController
@RequestMapping("/api/freight")
public class FreightController {

    @Autowired
    private FreightService freightService;

    @GetMapping
    public ResponseEntity<Page<Freight>> getAll(@RequestParam(required = false) String propertyValue,
                                                Pageable pageable) {
        FreightFilter filter = new FreightFilter();
        if (propertyValue != null) {
            filter.getProperties().get(propertyValue);
        }
        return new ResponseEntity<>(this.freightService.getAll(filter, pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Freight> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.freightService.seekOrFail(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Freight> create(@Valid @RequestBody FreightDTO data){
        Freight freight = this.freightService.create(data);
        return new ResponseEntity<>(freight, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Freight> update(@PathVariable Long id,@Valid @RequestBody FreightDTO data){
        Freight freight = this.freightService.update(id, data);
        return new ResponseEntity<>(freight, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.freightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
