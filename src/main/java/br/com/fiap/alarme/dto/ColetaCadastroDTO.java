package br.com.fiap.alarme.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ColetaCadastroDTO(
        Long coletaId,

        Long recipienteId,

        @NotNull(message = "A data é obrigatória!")
        LocalDate data

) {
}