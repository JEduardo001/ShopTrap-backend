package com.shoptrap_ecommerce_backend.demo.dto.dtoEntity;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {
    @NotNull(message = "Id de usuario no puede ser nulo")
    @Min(1)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Email(message = "El Correo electrónico debe tener un formato válido")
    private String email;
    private LocalDateTime dateCreated;
    @NotNull(message = "Fecha de nacimiento no puede ser nulo")
    private LocalDate birthday;
}
