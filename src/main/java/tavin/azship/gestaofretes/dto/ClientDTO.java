package tavin.azship.gestaofretes.dto;

import tavin.azship.gestaofretes.model.Freight;

import java.util.List;

public record ClientDTO (List<Freight> freights, String cnpj, String email) {
}
