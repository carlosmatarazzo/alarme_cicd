package br.com.fiap.alarme.repository;

import br.com.fiap.alarme.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}