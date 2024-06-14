package tavin.azship.gestaofretes.api.dto;

import lombok.Builder;

@Builder
public record ExceptionDTO(

        String message,

        Integer statusCode
) {
}
