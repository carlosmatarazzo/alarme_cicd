package br.com.fiap.alarme.dto;

import br.com.fiap.alarme.model.Recipiente;
import br.com.fiap.alarme.model.RecipienteTipo;

public record RecipienteExibicaoDTO(
        Long recipienteId,
        RecipienteTipo tipo,
        Integer capacidadeTotal,
        Integer capacidadeAtual,
        String localizacao)
{

    public RecipienteExibicaoDTO(Recipiente recipiente) {
        this(
                recipiente.getRecipienteId(),
                recipiente.getTipo(),
                recipiente.getCapacidadeTotal(),
                recipiente.getCapacidadeAtual(),
                recipiente.getLocalizacao()
        );
    }
}