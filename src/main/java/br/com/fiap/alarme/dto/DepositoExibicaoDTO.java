package br.com.fiap.alarme.dto;

import br.com.fiap.alarme.model.Deposito;
import java.time.LocalDate;

public record DepositoExibicaoDTO(
        Long depositoId,
        Long recipienteId,
        Integer peso,
        LocalDate data) {

    public DepositoExibicaoDTO(Deposito deposito) {
        this(
                deposito.getDepositoId(),
                deposito.getRecipienteId(),
                deposito.getPeso(),
                deposito.getData());
    }
}