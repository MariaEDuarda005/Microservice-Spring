package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

// Utilizando já as valdações
public record UserRecordDto(@NotBlank String name,
                            @NotBlank @Email String email) {
}
