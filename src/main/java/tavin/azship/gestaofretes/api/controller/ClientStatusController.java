package tavin.azship.gestaofretes.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.domain.service.ClientStatusService;

import java.util.List;

@RestController
@RequestMapping("/api/client/status")
@RequiredArgsConstructor
public class ClientStatusController {

    private final ClientStatusService clientStatusService;

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Long id){
        clientStatusService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id){
        clientStatusService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/activations")
    public ResponseEntity<Void> activations(@RequestBody List<Long> ids){
        clientStatusService.active(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/disabless")
    public ResponseEntity<Void> disableMultiples(@RequestBody List<Long> ids){
        clientStatusService.disable(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
