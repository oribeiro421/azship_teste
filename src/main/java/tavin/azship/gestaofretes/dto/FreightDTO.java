package tavin.azship.gestaofretes.dto;

import tavin.azship.gestaofretes.model.Client;

import java.util.Map;

public record FreightDTO (Long clientId, Map<String, String>properties){
}
