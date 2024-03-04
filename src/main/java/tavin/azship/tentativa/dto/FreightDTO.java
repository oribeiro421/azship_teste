package tavin.azship.tentativa.dto;

import tavin.azship.tentativa.model.Client;

import java.util.List;
import java.util.Map;

public record FreightDTO (Long clientId, Map<String, String> properties){
}
