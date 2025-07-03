package com.wefit.desafio_backend.domain.dto;

import com.wefit.desafio_backend.domain.enums.PersonType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Person type is required")
    private String personType; // Será convertido para PersonType no serviço

    private String cpf;
    private String cnpj;
    private String cpfResponsible;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Mobile phone is required")
    private String mobilePhone;

    private String landlinePhone;

    @NotBlank(message = "Confirm email is required")
    @Email(message = "Invalid confirm email")
    private String confirmEmail;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Number is required")
    private String number;

    private String complement;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;

    @NotBlank(message = "State is required")
    private String state;

    @NotNull(message = "Terms acceptance is required")
    private Boolean termsAccepted;
}