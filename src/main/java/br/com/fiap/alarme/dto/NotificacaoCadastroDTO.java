package br.com.fiap.alarme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NotificacaoCadastroDTO(
        Long notificacaoId,

        Long recipienteId,

        @NotNull(message = "A data é obrigatória!")
        LocalDate data

) {
}