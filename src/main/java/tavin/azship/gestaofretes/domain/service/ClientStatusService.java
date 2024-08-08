package tavin.azship.gestaofretes.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tavin.azship.gestaofretes.domain.model.Client;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientStatusService {

    private final ClientService clientService;

    @Transactional
    public void active(Long id){
        Client client = clientService.seekOrFail(id);

        client.active();
    }

    @Transactional
    public void disable(Long id){
        Client client = clientService.seekOrFail(id);

        client.disable();
    }

    @Transactional
    public void active(List<Long> ids){
        ids.forEach(this::active);
    }

    @Transactional
    public void disable(List<Long> ids){
        ids.forEach(this::disable);
    }
}
