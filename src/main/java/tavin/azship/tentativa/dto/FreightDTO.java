package tavin.azship.tentativa.dto;

import tavin.azship.tentativa.model.Client;

import java.util.List;
import java.util.Map;

public record FreightDTO (Client client, Map<String, String> properties){
}
