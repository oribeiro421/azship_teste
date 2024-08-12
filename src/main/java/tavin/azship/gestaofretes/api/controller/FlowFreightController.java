package tavin.azship.gestaofretes.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tavin.azship.gestaofretes.domain.service.FlowFreightService;

@RestController
@RequestMapping("/api/freight/flow")
@RequiredArgsConstructor
public class FlowFreightController {

    private final FlowFreightService freightService;


    @PutMapping("/confirmed/{id}")
    public void confirmed(@PathVariable Long id){
        freightService.confirmed(id);
    }

    @PutMapping("/inTransit/{id}")
    public void inTransit(@PathVariable Long id){
        freightService.inTransit(id);
    }

    @PutMapping("/delivered/{id}")
    public void delivered(@PathVariable Long id){
        freightService.delivered(id);
    }

    @DeleteMapping("/canceled/{id}")
    public void canceled(@PathVariable Long id){
        freightService.canceled(id);
    }
}
