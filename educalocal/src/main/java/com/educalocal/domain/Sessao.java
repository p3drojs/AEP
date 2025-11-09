package com.educalocal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessoes")
@Getter
@Setter
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trilha_id", nullable = false)
    private Trilha trilha;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private Usuario mentor;

    @ManyToOne
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Usuario aprendiz;

    @NotNull
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModoSessao modo;

    private String local;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSessao status;

    private Integer vagas;
}
