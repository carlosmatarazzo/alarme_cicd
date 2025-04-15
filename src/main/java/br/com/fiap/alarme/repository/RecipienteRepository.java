package br.com.fiap.alarme.repository;

import br.com.fiap.alarme.model.Recipiente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipienteRepository extends JpaRepository<Recipiente, Long> {
}