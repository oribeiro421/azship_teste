package tavin.azship.gestaofretes.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import tavin.azship.gestaofretes.api.dto.FreightDTO;
import tavin.azship.gestaofretes.domain.exception.ResourceNotFoundException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

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

    @CreationTimestamp
    private OffsetDateTime creationDate;
    private OffsetDateTime confirmed;
    private OffsetDateTime inTransit;
    private OffsetDateTime delivered;
    private OffsetDateTime canceled;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private StatusFreight status = StatusFreight.CREATED;

    @ElementCollection
    @CollectionTable(name = "freight_properties", joinColumns = @JoinColumn(name = "freight_id"))
    @MapKeyColumn(name = "property_key")
    @Column(name = "property_value")
    private Map<String, String> properties;


    public boolean associationCollect(AddressCollect addressCollect){
        return getAddressCollect().add(addressCollect);
    }

    public boolean disassociateCollect(AddressCollect addressCollect){
        return getAddressCollect().remove(addressCollect);
    }

    public boolean associationDelivery(AddressDelivery addressDelivery){
        return getAddressDelivery().add(addressDelivery);
    }

    public boolean disassociateDelivery(AddressDelivery addressDelivery){
        return getAddressDelivery().remove(addressDelivery);
    }

    public void confirmed(){
        setStatus(StatusFreight.CONFIRMED);
        setConfirmed(OffsetDateTime.now());
    }

    public void inTransit(){
        setStatus(StatusFreight.IN_TRANSIT);
        setInTransit(OffsetDateTime.now());
    }

    public void delivered(){
        setStatus(StatusFreight.DELIVERED);
        setDelivered(OffsetDateTime.now());
    }

    public void canceled(){
        setStatus(StatusFreight.CANCELED);
        setDelivered(OffsetDateTime.now());
    }

    private void setStatus(StatusFreight newStatus){
        if (getStatus().cannotChange(newStatus)){
            throw new ResourceNotFoundException(ResourceNotFoundException.Type.FLOW_FREIGHT,
                    String.format("Status do frete %s não pode ser alterado de %s para %s",
                    getId(), getStatus().getDescription(), newStatus.getDescription()));
        }
        this.status = newStatus;
    }

}
