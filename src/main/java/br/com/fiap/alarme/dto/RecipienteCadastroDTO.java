package br.com.fiap.alarme.dto;

import br.com.fiap.alarme.model.RecipienteTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecipienteCadastroDTO(
        Long recipienteId,

        @NotNull(message = "O tipo é obrigatório!")
        RecipienteTipo tipo,

        @NotNull(message = "A capacidade total é obrigatória!")
        Integer capacidadeTotal,

        @NotBlank(message = "A localizacao é obrigatória!")
        String localizacao

) {
}