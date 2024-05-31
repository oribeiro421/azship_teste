package tavin.azship.gestaofretes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.AddressCollectDTO;
import tavin.azship.gestaofretes.domain.model.AddressCollect;
import tavin.azship.gestaofretes.domain.service.AddressCollectService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/address_collect")
public class AddressCollectController {

    @Autowired
    private AddressCollectService collectService;

    @GetMapping
    public ResponseEntity<List<AddressCollect>> getAll(@RequestParam(required = false) Boolean excludeActive){
        if (excludeActive != null && excludeActive){
            return new ResponseEntity<>(collectService.getInactive(), HttpStatus.OK);
        }
        return new ResponseEntity<>(collectService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressCollect> getById(@PathVariable Long id){
        return new ResponseEntity<>(collectService.seekOrFail(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AddressCollect> create(@RequestBody AddressCollectDTO dto){
        return new ResponseEntity<>(collectService.create(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AddressCollect> update(@PathVariable Long id, @RequestBody AddressCollectDTO dto){
        return new ResponseEntity<>(collectService.update(id, dto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        collectService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Long id){
        collectService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id){
        collectService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
