package tavin.azship.gestaofretes.api.dto.response;


import tavin.azship.gestaofretes.domain.model.Driver;

public record DriverResponseDTO(

        Long id,
        String name,
        String cpf,
        String licenseNumber,
        String birthDate
) {

        public static DriverResponseDTO fromEntity(Driver driver){
                return new DriverResponseDTO(
                        driver.getId(),
                        driver.getName(),
                        driver.getCpf(),
                        driver.getBirthDate(),
                        driver.getBirthDate()
                );
        }

}
