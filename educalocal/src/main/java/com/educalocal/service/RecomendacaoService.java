package com.educalocal.service;

import com.educalocal.domain.Habilidade;
import com.educalocal.domain.Trilha;
import com.educalocal.domain.Usuario;
import com.educalocal.repository.TrilhaRepository;
import com.educalocal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    private final UsuarioRepository usuarioRepository;
    private final TrilhaRepository trilhaRepository;

    public RecomendacaoService(UsuarioRepository usuarioRepository, TrilhaRepository trilhaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.trilhaRepository = trilhaRepository;
    }

    public List<Trilha> recomendarTrilhasParaUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Set<Habilidade> habilidadesUsuario = usuario.getHabilidades();
        List<Trilha> trilhasAtivas = trilhaRepository.findByAtivaTrue();

        Map<Trilha, Integer> pontuacao = new HashMap<>();

        for (Trilha trilha : trilhasAtivas) {
            Set<Habilidade> intersecao = new HashSet<>(trilha.getHabilidades());
            intersecao.retainAll(habilidadesUsuario);
            int score = intersecao.size();
            if (score > 0) {
                pontuacao.put(trilha, score);
            }
        }

        return pontuacao.entrySet().stream()
                .sorted(Map.Entry.<Trilha, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
