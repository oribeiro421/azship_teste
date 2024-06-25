package tavin.azship.gestaofretes.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ExceptionDTO(

        List<String> message,

        Integer statusCode
) {
}
