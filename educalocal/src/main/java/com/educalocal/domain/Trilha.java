package com.educalocal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trilhas")
@Getter
@Setter
public class Trilha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "text")
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private Integer vagas;

    @NotNull
    @Column(nullable = false)
    private Boolean ativa = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private Usuario mentor;

    @OneToMany(mappedBy = "trilha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Modulo> modulos;

    @ManyToMany
    @JoinTable(
            name = "trilhas_habilidades",
            joinColumns = @JoinColumn(name = "trilha_id"),
            inverseJoinColumns = @JoinColumn(name = "habilidade_id")
    )
    private Set<Habilidade> habilidades = new HashSet<>();
}
