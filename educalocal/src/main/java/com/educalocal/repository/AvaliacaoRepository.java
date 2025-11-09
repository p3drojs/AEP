package com.educalocal.repository;

import com.educalocal.domain.Avaliacao;
import com.educalocal.domain.Trilha;
import com.educalocal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByTrilha(Trilha trilha);

    List<Avaliacao> findByAvaliado(Usuario avaliado);
}
