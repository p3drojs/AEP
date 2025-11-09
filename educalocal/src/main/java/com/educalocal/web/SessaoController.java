package com.educalocal.web;

import com.educalocal.domain.*;
import com.educalocal.repository.SessaoRepository;
import com.educalocal.repository.TrilhaRepository;
import com.educalocal.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {

    private final SessaoRepository sessaoRepository;
    private final TrilhaRepository trilhaRepository;
    private final UsuarioRepository usuarioRepository;

    public SessaoController(SessaoRepository sessaoRepository,
                            TrilhaRepository trilhaRepository,
                            UsuarioRepository usuarioRepository) {
        this.sessaoRepository = sessaoRepository;
        this.trilhaRepository = trilhaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<Sessao> agendar(@RequestParam Long trilhaId,
                                          @RequestParam Long mentorId,
                                          @RequestParam Long aprendizId,
                                          @RequestParam ModoSessao modo,
                                          @RequestParam String local,
                                          @RequestParam String dataHoraIso,
                                          @RequestParam(required = false) Integer vagas) {
        Trilha trilha = trilhaRepository.findById(trilhaId).orElseThrow();
        Usuario mentor = usuarioRepository.findById(mentorId).orElseThrow();
        Usuario aprendiz = usuarioRepository.findById(aprendizId).orElseThrow();

        Sessao sessao = new Sessao();
        sessao.setTrilha(trilha);
        sessao.setMentor(mentor);
        sessao.setAprendiz(aprendiz);
        sessao.setModo(modo);
        sessao.setLocal(local);
        sessao.setDataHora(LocalDateTime.parse(dataHoraIso));
        sessao.setStatus(StatusSessao.AGENDADA);
        sessao.setVagas(vagas);

        return ResponseEntity.ok(sessaoRepository.save(sessao));
    }

    @GetMapping("/mentor/{mentorId}")
    public List<Sessao> listarPorMentor(@PathVariable Long mentorId) {
        Usuario mentor = usuarioRepository.findById(mentorId).orElseThrow();
        return sessaoRepository.findByMentorAndDataHoraAfter(mentor, LocalDateTime.now());
    }

    @GetMapping("/aprendiz/{aprendizId}")
    public List<Sessao> listarPorAprendiz(@PathVariable Long aprendizId) {
        Usuario aprendiz = usuarioRepository.findById(aprendizId).orElseThrow();
        return sessaoRepository.findByAprendizAndDataHoraAfter(aprendiz, LocalDateTime.now());
    }
}
