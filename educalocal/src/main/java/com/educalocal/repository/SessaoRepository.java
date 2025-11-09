package com.educalocal.repository;

import com.educalocal.domain.Sessao;
import com.educalocal.domain.StatusSessao;
import com.educalocal.domain.Trilha;
import com.educalocal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    List<Sessao> findByTrilhaAndStatus(Trilha trilha, StatusSessao status);

    List<Sessao> findByMentorAndDataHoraAfter(Usuario mentor, LocalDateTime dataHora);

    List<Sessao> findByAprendizAndDataHoraAfter(Usuario aprendiz, LocalDateTime dataHora);
}
