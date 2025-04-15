package br.com.fiap.alarme.dto;

import br.com.fiap.alarme.model.Notificacao;
import java.time.LocalDate;

public record NotificacaoExibicaoDTO(
        Long notificacaoId,
        Long recipienteId,
        LocalDate data) {

    public NotificacaoExibicaoDTO(Notificacao notificacao) {
        this(
                notificacao.getNotificacaoId(),
                notificacao.getRecipienteId(),
                notificacao.getData());
    }
}