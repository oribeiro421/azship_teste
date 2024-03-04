package tavin.azship.tentativa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import tavin.azship.tentativa.dto.FreightDTO;

import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Freight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "freight_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
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
