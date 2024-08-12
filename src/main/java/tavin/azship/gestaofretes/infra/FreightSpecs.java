package tavin.azship.gestaofretes.infra;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import tavin.azship.gestaofretes.domain.model.Freight;
import tavin.azship.gestaofretes.domain.repository.filter.FreightFilter;

import java.util.ArrayList;
import java.util.Map;

public class FreightSpecs {

    public static Specification<Freight> usingFilter(FreightFilter filter){
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if(filter.getProperties() != null){
                for (Map.Entry<String, String> entry : filter.getProperties().entrySet()){
                    predicates.add(builder.equal(root.joinMap("properties").value(), entry.getValue()));
                }
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
