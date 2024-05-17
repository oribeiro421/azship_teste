package tavin.azship.gestaofretes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tavin.azship.gestaofretes.dto.AddressCollectDTO;
import tavin.azship.gestaofretes.dto.AddressDeliveryDTO;

import java.util.List;

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

    public AddressDelivery(Long id, AddressDeliveryDTO dto){
        this.id = id;
        this.road = dto.road();
        this.neighborhood = dto.neighborhood();
        this.state = dto.state();
        this.number = dto.number();
    }
    public AddressDelivery(AddressDeliveryDTO dto){
        this.road = dto.road();
        this.neighborhood = dto.neighborhood();
        this.state = dto.state();
        this.number = dto.number();
    }

    public void active(){
        setActive(true);
    }
    public void disable(){
        setActive(false);
    }
}