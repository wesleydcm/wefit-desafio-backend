package com.wefit.desafio_backend.application.service;

import com.wefit.desafio_backend.domain.entity.User;
import com.wefit.desafio_backend.domain.enums.PersonType;
import com.wefit.desafio_backend.domain.dto.UserRegistrationDTO;
import com.wefit.desafio_backend.infrastructure.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;

    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUser(@Valid UserRegistrationDTO dto) {
        // Validação de email
        if (!dto.getEmail().equals(dto.getConfirmEmail())) {
            throw new IllegalArgumentException("Emails do not match");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }

        // Validação de personType e campos obrigatórios
        PersonType personType = PersonType.valueOf(dto.getPersonType().toUpperCase());
        if (personType == PersonType.FISICA) {
            if (dto.getCpf() == null || dto.getCpf().trim().isEmpty()) {
                throw new IllegalArgumentException("CPF is required for individual");
            }
            if (!isValidCPF(dto.getCpf())) {
                throw new IllegalArgumentException("Invalid CPF");
            }
            // Verificar se o CPF já existe
            if (userRepository.existsByCpf(dto.getCpf())) {
                throw new IllegalStateException("CPF already registered");
            }
            if (dto.getCnpj() != null && !dto.getCnpj().trim().isEmpty()) {
                throw new IllegalArgumentException("CNPJ should not be provided for individual");
            }
            if (dto.getCpfResponsible() != null && !dto.getCpfResponsible().trim().isEmpty()) {
                throw new IllegalArgumentException("CPF of responsible should not be provided for individual");
            }
        } else if (personType == PersonType.JURIDICA) {
            if (dto.getCnpj() == null || dto.getCnpj().trim().isEmpty()) {
                throw new IllegalArgumentException("CNPJ is required for legal entity");
            }
            if (!isValidCNPJ(dto.getCnpj())) {
                throw new IllegalArgumentException("Invalid CNPJ");
            }
            // Verificar se o CNPJ já existe
            if (userRepository.existsByCnpj(dto.getCnpj())) {
                throw new IllegalStateException("CNPJ already registered");
            }
            if (dto.getCpf() != null && !dto.getCpf().trim().isEmpty()) {
                throw new IllegalArgumentException("CPF should not be provided for legal entity");
            }
            if (dto.getCpfResponsible() == null || dto.getCpfResponsible().trim().isEmpty()) {
                throw new IllegalArgumentException("CPF of responsible is required for legal entity");
            }
            if (!isValidCPF(dto.getCpfResponsible())) {
                throw new IllegalArgumentException("Invalid CPF of responsible");
            }
        }

        // Mapeamento para entidade
        User user = new User(
                dto.getEmail(),
                personType,
                dto.getCpf(),
                dto.getCnpj(),
                dto.getCpfResponsible(),
                dto.getFullName(),
                dto.getMobilePhone(),
                dto.getLandlinePhone(),
                dto.getPostalCode(),
                dto.getStreet(),
                dto.getNumber(),
                dto.getComplement(),
                dto.getCity(),
                dto.getNeighborhood(),
                dto.getState(),
                dto.getTermsAccepted()
        );

        userRepository.save(user);
    }

    private boolean isValidCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    private boolean isValidCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }
}