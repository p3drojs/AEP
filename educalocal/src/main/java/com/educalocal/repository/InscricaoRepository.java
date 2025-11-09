package com.educalocal.repository;

import com.educalocal.domain.Inscricao;
import com.educalocal.domain.StatusInscricao;
import com.educalocal.domain.Trilha;
import com.educalocal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    Optional<Inscricao> findByAprendizAndTrilha(Usuario aprendiz, Trilha trilha);

    List<Inscricao> findByAprendizAndStatus(Usuario aprendiz, StatusInscricao status);
}
