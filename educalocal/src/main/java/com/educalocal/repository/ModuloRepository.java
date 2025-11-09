package com.educalocal.repository;

import com.educalocal.domain.Modulo;
import com.educalocal.domain.Trilha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    List<Modulo> findByTrilhaOrderByOrdemAsc(Trilha trilha);
}
