package br.com.fiap.alarme.controller;

import br.com.fiap.alarme.dto.NotificacaoCadastroDTO;
import br.com.fiap.alarme.dto.NotificacaoExibicaoDTO;
import br.com.fiap.alarme.service.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    //OK
    @PostMapping("/notificacao")
    @ResponseStatus(HttpStatus.CREATED)
    public NotificacaoExibicaoDTO salvar(
            @RequestBody @Valid NotificacaoCadastroDTO notificacao) {
        return notificacaoService.salvarNotificacao(notificacao);
    }

    //OK
    @GetMapping("/notificacao")
    @ResponseStatus(HttpStatus.OK)
    public Page<NotificacaoExibicaoDTO> litarTodos(
            @PageableDefault(size = 20, page = 0) Pageable paginacao
    ) {
        return notificacaoService.listarTodos(paginacao);
    }

    //OK
    @GetMapping("/notificacao/{notificacaoId}")
    public ResponseEntity<NotificacaoExibicaoDTO> buscarPorId(@PathVariable Long notificacaoId) {
        return ResponseEntity.ok(notificacaoService.buscarPorId(notificacaoId));
    }

    //OK
    @DeleteMapping("/notificacao/{notificacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long notificacaoId) {
        notificacaoService.excluir(notificacaoId);
    }

    //OK
    @PutMapping("/notificacao")
    public ResponseEntity<NotificacaoExibicaoDTO> atualizar(
            @RequestBody NotificacaoCadastroDTO notificacaoDTO) {
        try {
            NotificacaoExibicaoDTO notificacaoExibicaoDTO =
                    notificacaoService.atualizar(notificacaoDTO);
            return ResponseEntity.ok(notificacaoExibicaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
