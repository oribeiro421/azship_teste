package tavin.azship.gestaofretes.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.api.dto.FreightDTO;
import tavin.azship.gestaofretes.api.dto.response.FreightResponseDTO;
import tavin.azship.gestaofretes.api.dto.update.FreightUpdateDTO;
import tavin.azship.gestaofretes.domain.repository.filter.FreightFilter;
import tavin.azship.gestaofretes.domain.service.FreightService;

import java.util.List;

@RestController
@RequestMapping("/api/freight")
@RequiredArgsConstructor
public class FreightController {

    private final FreightService freightService;

    @GetMapping
    public ResponseEntity<Page<FreightResponseDTO>> getAll(@RequestParam(required = false) String propertyValue,
                                                Pageable pageable) {
        FreightFilter filter = new FreightFilter();
        if (propertyValue != null) {
            filter.getProperties().put("properties", propertyValue);
        }
        return new ResponseEntity<>(this.freightService.getAll(filter, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreightResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.freightService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FreightResponseDTO> create(@Valid @RequestBody FreightDTO data){
        return new ResponseEntity<>(this.freightService.create(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreightResponseDTO> update(@PathVariable Long id,@Valid @RequestBody FreightUpdateDTO data){
        return new ResponseEntity<>(this.freightService.update(id, data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.freightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
