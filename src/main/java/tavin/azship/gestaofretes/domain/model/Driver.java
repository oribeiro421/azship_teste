package tavin.azship.gestaofretes.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tavin.azship.gestaofretes.api.dto.DriverDTO;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String licenseNumber;
    @Column(nullable = false)
    private String birthDate;

    @OneToMany(mappedBy = "driver")
    @JsonBackReference
    private List<Freight> freights;

    public Driver (DriverDTO dto){
        this.name = dto.name();
        this.cpf = dto.cpf();
        this.licenseNumber = dto.licenseNumber();
        this.birthDate = dto.birthDate();
    }

    public Driver (Long id, DriverDTO dto){
        this.id = id;
        this.name = dto.name();
        this.cpf = dto.cpf();
        this.licenseNumber = dto.licenseNumber();
        this.birthDate = dto.birthDate();
    }

}
