package com.educalocal.repository;

import com.educalocal.domain.TipoUsuario;
import com.educalocal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByTipo(TipoUsuario tipo);
}
