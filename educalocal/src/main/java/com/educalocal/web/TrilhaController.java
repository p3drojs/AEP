package com.educalocal.web;

import com.educalocal.domain.*;
import com.educalocal.repository.InscricaoRepository;
import com.educalocal.repository.TrilhaRepository;
import com.educalocal.repository.UsuarioRepository;
import com.educalocal.service.RecomendacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trilhas")
public class TrilhaController {

    private final TrilhaRepository trilhaRepository;
    private final UsuarioRepository usuarioRepository;
    private final InscricaoRepository inscricaoRepository;
    private final RecomendacaoService recomendacaoService;

    public TrilhaController(TrilhaRepository trilhaRepository,
                            UsuarioRepository usuarioRepository,
                            InscricaoRepository inscricaoRepository,
                            RecomendacaoService recomendacaoService) {
        this.trilhaRepository = trilhaRepository;
        this.usuarioRepository = usuarioRepository;
        this.inscricaoRepository = inscricaoRepository;
        this.recomendacaoService = recomendacaoService;
    }

    @PostMapping
    public ResponseEntity<Trilha> criar(@Valid @RequestBody Trilha trilha) {
        return ResponseEntity.ok(trilhaRepository.save(trilha));
    }

    @GetMapping
    public List<Trilha> listar() {
        return trilhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trilha> buscarPorId(@PathVariable Long id) {
        return trilhaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{trilhaId}/inscricoes")
    public ResponseEntity<Inscricao> inscrever(@PathVariable Long trilhaId,
                                               @RequestParam Long usuarioId) {
        Trilha trilha = trilhaRepository.findById(trilhaId).orElseThrow();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();

        if (!trilha.getAtiva()) {
            return ResponseEntity.badRequest().build();
        }

        if (inscricaoRepository.findByAprendizAndTrilha(usuario, trilha).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setTrilha(trilha);
        inscricao.setAprendiz(usuario);
        inscricao.setStatus(StatusInscricao.ATIVA);
        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setConcluida(false);

        return ResponseEntity.ok(inscricaoRepository.save(inscricao));
    }

    @GetMapping("/recomendadas/{usuarioId}")
    public List<Trilha> recomendar(@PathVariable Long usuarioId) {
        return recomendacaoService.recomendarTrilhasParaUsuario(usuarioId);
    }
}
