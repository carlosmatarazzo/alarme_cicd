package br.com.fiap.alarme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_al_coletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coleta {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AL_COLETAS"
    )
    @SequenceGenerator(
            name = "SEQ_AL_COLETAS",
            sequenceName = "SEQ_AL_COLETAS",
            allocationSize = 1
    )
    @Column(name = "COLETA_ID")
    private Long coletaId;

    @Column(name = "RECIPIENTE_ID")
    private Long recipienteId;

    @Column(name = "DATA")
    private LocalDate data;

}
