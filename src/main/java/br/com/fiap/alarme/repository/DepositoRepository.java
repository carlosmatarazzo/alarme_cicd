package br.com.fiap.alarme.repository;

import br.com.fiap.alarme.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {
}