package br.com.fiap.alarme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_al_notificacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AL_NOTIFICACOES"
    )
    @SequenceGenerator(
            name = "SEQ_AL_NOTIFICACOES",
            sequenceName = "SEQ_AL_NOTIFICACOES",
            allocationSize = 1
    )
    @Column(name = "NOTIFICACAO_ID")
    private Long notificacaoId;

    @Column(name = "RECIPIENTE_ID")
    private Long recipienteId;

    @Column(name = "DATA")
    private LocalDate data;

}
