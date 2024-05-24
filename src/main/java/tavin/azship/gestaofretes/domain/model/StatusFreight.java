package tavin.azship.gestaofretes.domain.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum StatusFreight {

    CREATED("Criado"),
    CONFIRMED("Confirmado", CREATED),
    IN_TRANSIT("Em transito", CONFIRMED),
    DELIVERED("Entregue", IN_TRANSIT),
    CANCELED("Cancelado", CREATED,CONFIRMED, IN_TRANSIT);


    @Getter
    private String description;
    private List<StatusFreight> statusPrevious;

    StatusFreight(String description, StatusFreight... statusPrevious){
        this.description = description;
        this.statusPrevious = Arrays.asList(statusPrevious);
    }

    public boolean cannotChange(StatusFreight newStatus){
        return !newStatus.statusPrevious.contains(this);
    }
    public boolean canChange(StatusFreight newStatus){
        return !cannotChange(newStatus);
    }
}
