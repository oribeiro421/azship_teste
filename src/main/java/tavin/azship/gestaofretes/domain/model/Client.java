package tavin.azship.gestaofretes.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tavin.azship.gestaofretes.api.dto.ClientDTO;

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
    private Boolean active = Boolean.TRUE;


    public void active(){
        setActive(true);
    }

    public void disable(){
        setActive(false);
    }

    public boolean isActive(){
        return active;
    }

}
