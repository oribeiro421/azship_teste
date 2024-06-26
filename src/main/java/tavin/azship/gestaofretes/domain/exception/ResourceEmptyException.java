package tavin.azship.gestaofretes.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceEmptyException extends RuntimeException{

    public enum Type{
        EMAIL, CNPJ, NAME, CPF, LICENSE_NUMBER, BIRTH_DATE;
    }

    private Type type;

    public ResourceEmptyException(Type type, String message){
        super(message);
        this.type = type;
    }
}
