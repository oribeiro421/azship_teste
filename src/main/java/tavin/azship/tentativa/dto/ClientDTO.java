package tavin.azship.tentativa.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import tavin.azship.tentativa.model.Freight;

import java.util.List;

public record ClientDTO (String email, String cnpj, List<Freight> freights){
}
