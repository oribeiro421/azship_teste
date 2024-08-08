package tavin.azship.gestaofretes.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.domain.service.AddresCollectStatusService;

@RestController
@RequestMapping("/api/address_collect/status")
@RequiredArgsConstructor
public class AddresCollectStatusController {

    private final AddresCollectStatusService addresCollectStatusService;

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Long id){
        addresCollectStatusService.active(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id){
        addresCollectStatusService.disable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
