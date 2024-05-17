package tavin.azship.gestaofretes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.dto.ClientDTO;
import tavin.azship.gestaofretes.model.Client;
import tavin.azship.gestaofretes.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAll(@RequestParam(required = false) Boolean excludeActive){
        if (excludeActive != null && !excludeActive){
            return new ResponseEntity<>(this.clientService.getInactive(), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.clientService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.clientService.seekOrFail(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDTO data) throws Exception {
        Client client = this.clientService.create(data);
        return new ResponseEntity<>(client,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id,@RequestBody ClientDTO data) {
        Client client = this.clientService.update(id, data);
        return new ResponseEntity<>(client,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Long id){
        clientService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id){
        clientService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/activations")
    public ResponseEntity<Void> activations(@RequestBody List<Long> ids){
        clientService.active(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/disabless")
    public ResponseEntity<Void> disableMultiples(@RequestBody List<Long> ids){
        clientService.disable(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
