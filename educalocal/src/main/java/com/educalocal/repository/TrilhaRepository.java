package com.educalocal.repository;

import com.educalocal.domain.Trilha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrilhaRepository extends JpaRepository<Trilha, Long> {

    List<Trilha> findByAtivaTrue();
}
