package com.wefit.desafio_backend.domain.entity;

import com.wefit.desafio_backend.domain.enums.PersonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_user", // Nome da tabela alterado para evitar conflito
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"cpf"}, name = "uk_user_cpf"),
           @UniqueConstraint(columnNames = {"cnpj"}, name = "uk_user_cnpj")
       })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String email; // Usando email como ID único, já que CPF/CNPJ podem não ser obrigatórios juntos

    @Enumerated(EnumType.STRING)
    private PersonType personType; // "FISICA" ou "JURIDICA"

    @Column(unique = true, nullable = true) // Pode ser nulo, mas se existir deve ser único
    private String cpf; // Obrigatório para FISICA

    @Column(unique = true, nullable = true) // Pode ser nulo, mas se existir deve ser único
    private String cnpj; // Obrigatório para JURIDICA
    private String cpfResponsible; // CPF do responsável, aplicável para JURIDICA
    private String fullName;
    private String mobilePhone;
    private String landlinePhone;
    private String confirmEmail;
    private String postalCode;
    private String street;
    private String number;
    private String complement;
    private String city;
    private String neighborhood;
    private String state;
    private boolean termsAccepted;
}
