package tavin.azship.gestaofretes.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tavin.azship.gestaofretes.api.dto.AddressDeliveryDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String road;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private Integer number;
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
