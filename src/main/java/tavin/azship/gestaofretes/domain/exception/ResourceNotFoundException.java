package tavin.azship.gestaofretes.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

    public enum Type{
        PROPERTY, ID, CLIENT, DELIVERY, COLLECT;
    }

    private Type type;

    public ResourceNotFoundException(Type type, String message){
        super(message);
        this.type = type;
    }
}
