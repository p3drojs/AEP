package com.educalocal.repository;

import com.educalocal.domain.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {

    Optional<Habilidade> findByNome(String nome);
}
