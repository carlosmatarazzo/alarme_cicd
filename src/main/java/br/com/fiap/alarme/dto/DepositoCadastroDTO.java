package br.com.fiap.alarme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DepositoCadastroDTO(
        Long depositoId,

        Long recipienteId,

        @NotNull(message = "O peso é obrigatório!")
        Integer peso,

        @NotNull(message = "A data é obrigatória!")
        LocalDate data

) {
}