package br.com.fiap.alarme.controller;

import br.com.fiap.alarme.dto.DepositoCadastroDTO;
import br.com.fiap.alarme.dto.DepositoExibicaoDTO;
import br.com.fiap.alarme.service.DepositoService;
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
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    //OK
    @PostMapping("/deposito")
    @ResponseStatus(HttpStatus.CREATED)
    public DepositoExibicaoDTO salvar(
            @RequestBody @Valid DepositoCadastroDTO deposito) {
        return depositoService.salvarDeposito(deposito);
    }

    //OK
    @GetMapping("/deposito")
    @ResponseStatus(HttpStatus.OK)
    public Page<DepositoExibicaoDTO> litarTodos(
            @PageableDefault(size = 20, page = 0) Pageable paginacao
    ) {
        return depositoService.listarTodos(paginacao);
    }

    //OK
    @GetMapping("/deposito/{depositoId}")
    public ResponseEntity<DepositoExibicaoDTO> buscarPorId(@PathVariable Long depositoId) {
        return ResponseEntity.ok(depositoService.buscarPorId(depositoId));
    }

    //OK
    @DeleteMapping("/deposito/{depositoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long depositoId) {
        depositoService.excluir(depositoId);
    }

    //OK
    @PutMapping("/deposito")
    public ResponseEntity<DepositoExibicaoDTO> atualizar(
            @RequestBody DepositoCadastroDTO depositoDTO) {
        try {
            DepositoExibicaoDTO depositoExibicaoDTO =
                    depositoService.atualizar(depositoDTO);
            return ResponseEntity.ok(depositoExibicaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
