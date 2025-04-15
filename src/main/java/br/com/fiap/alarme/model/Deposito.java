package br.com.fiap.alarme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "tb_al_depositos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deposito {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AL_DEPOSITOS"
    )
    @SequenceGenerator(
            name = "SEQ_AL_DEPOSITOS",
            sequenceName = "SEQ_AL_DEPOSITOS",
            allocationSize = 1
    )
    @Column(name = "DEPOSITO_ID")
    private Long depositoId;

    @Column(name = "RECIPIENTE_ID")
    private Long recipienteId;

    @Column(name = "PESO")
    private Integer peso;

    @Column(name = "DATA")
    private LocalDate data;

}
