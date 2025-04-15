package br.com.fiap.alarme.dto;

import br.com.fiap.alarme.model.Coleta;

import java.time.LocalDate;

public record ColetaExibicaoDTO(
        Long coletaId,
        Long recipienteId,
        LocalDate data) {

    public ColetaExibicaoDTO(Coleta coleta) {
        this(
                coleta.getColetaId(),
                coleta.getRecipienteId(),
                coleta.getData());
    }
}