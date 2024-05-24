package tavin.azship.gestaofretes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tavin.azship.gestaofretes.domain.service.FlowFreightService;

@RestController
@RequestMapping("/api/freight/{freightId}")
public class FlowFreightController {

    @Autowired
    private FlowFreightService freightService;
}
