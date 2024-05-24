package tavin.azship.gestaofretes.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class FreightFilter {

    private Map<String, String> properties = new HashMap<>();

}
