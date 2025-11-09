package com.educalocal.web;

import com.educalocal.domain.Avaliacao;
import com.educalocal.domain.Trilha;
import com.educalocal.domain.Usuario;
import com.educalocal.repository.AvaliacaoRepository;
import com.educalocal.repository.TrilhaRepository;
import com.educalocal.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;
    private final TrilhaRepository trilhaRepository;
    private final UsuarioRepository usuarioRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository,
                               TrilhaRepository trilhaRepository,
                               UsuarioRepository usuarioRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.trilhaRepository = trilhaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestParam Long trilhaId,
                                           @RequestParam Long avaliadorId,
                                           @RequestParam Long avaliadoId,
                                           @RequestParam Integer nota,
                                           @RequestParam(required = false) String comentario) {
        Trilha trilha = trilhaRepository.findById(trilhaId).orElseThrow();
        Usuario avaliador = usuarioRepository.findById(avaliadorId).orElseThrow();
        Usuario avaliado = usuarioRepository.findById(avaliadoId).orElseThrow();

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setTrilha(trilha);
        avaliacao.setAvaliador(avaliador);
        avaliacao.setAvaliado(avaliado);
        avaliacao.setNota(nota);
        avaliacao.setComentario(comentario);
        avaliacao.setDataHora(LocalDateTime.now());

        return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
    }

    @GetMapping("/trilha/{trilhaId}")
    public List<Avaliacao> listarPorTrilha(@PathVariable Long trilhaId) {
        Trilha trilha = trilhaRepository.findById(trilhaId).orElseThrow();
        return avaliacaoRepository.findByTrilha(trilha);
    }

    @GetMapping("/avaliado/{avaliadoId}")
    public List<Avaliacao> listarPorAvaliado(@PathVariable Long avaliadoId) {
        Usuario avaliado = usuarioRepository.findById(avaliadoId).orElseThrow();
        return avaliacaoRepository.findByAvaliado(avaliado);
    }
}
