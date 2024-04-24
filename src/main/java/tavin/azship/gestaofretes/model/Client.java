package tavin.azship.gestaofretes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tavin.azship.gestaofretes.dto.ClientDTO;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Freight> freights;
    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String email;

    public Client(ClientDTO data){
        this.freights = data.freights();
        this.cnpj = data.cnpj();
        this.email = data.email();
    }
    public Client(Long id, ClientDTO data){
        this.id = id;
        this.freights = data.freights();
        this.cnpj = data.cnpj();
        this.email = data.email();
    }

}
