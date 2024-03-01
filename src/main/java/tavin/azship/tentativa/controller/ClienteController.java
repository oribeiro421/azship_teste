package tavin.azship.tentativa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.tentativa.dto.ClientDTO;
import tavin.azship.tentativa.model.Client;
import tavin.azship.tentativa.service.ClienteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Client>> getAll(){
        return new ResponseEntity<>(this.clienteService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.clienteService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDTO data) throws RuntimeException{
        Client client = this.clienteService.create(data);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody ClientDTO data) throws Exception {
        Client client = this.clienteService.update(id, data);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        this.clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
