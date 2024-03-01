package tavin.azship.tentativa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tavin.azship.tentativa.dto.ClientDTO;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String cnpj;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Freight> freights;

    public Client (ClientDTO data){
        this.email = data.email();
        this.cnpj = data.cnpj();
        this.freights = data.freights();
    }

    public Client (Long id, ClientDTO data){
        this.id = id;
        this.email = data.email();
        this.cnpj = data.cnpj();
        this.freights = data.freights();
    }

}
