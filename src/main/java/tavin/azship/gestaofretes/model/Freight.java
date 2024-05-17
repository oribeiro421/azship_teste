package tavin.azship.gestaofretes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tavin.azship.gestaofretes.dto.FreightDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Freight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "freight_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
    @ManyToMany
    @JoinTable(name = "freight_collect",
            joinColumns = @JoinColumn(name = "freight_id"),
            inverseJoinColumns = @JoinColumn(name = "address_collect_id"))
    private List<AddressCollect> addressCollect;
    @ManyToMany
    @JoinTable(name = "freight_delivery",
            joinColumns = @JoinColumn(name = "freight_id"),
            inverseJoinColumns = @JoinColumn(name = "address_delivery_id"))
    private List<AddressDelivery> addressDelivery;
    @ElementCollection
    @CollectionTable(name = "freight_properties", joinColumns = @JoinColumn(name = "freight_id"))
    @MapKeyColumn(name = "property_key")
    @Column(name = "property_value")
    private Map<String, String> properties;

    public Freight (FreightDTO data){
        this.properties = data.properties();
    }
    public Freight (Long id, FreightDTO data){
        this.id = id;
        this.properties = data.properties();
    }

}
