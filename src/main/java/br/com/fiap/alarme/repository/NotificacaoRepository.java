package br.com.fiap.alarme.repository;

import br.com.fiap.alarme.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}