package br.com.fiap.alarme.controller;

import br.com.fiap.alarme.dto.ColetaCadastroDTO;
import br.com.fiap.alarme.dto.ColetaExibicaoDTO;
import br.com.fiap.alarme.service.ColetaService;
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
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    //OK
    @PostMapping("/coleta")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaExibicaoDTO salvar(
            @RequestBody @Valid ColetaCadastroDTO coleta) {
        return coletaService.salvarColeta(coleta);
    }

    //OK
    @GetMapping("/coleta")
    @ResponseStatus(HttpStatus.OK)
    public Page<ColetaExibicaoDTO> litarTodos(
            @PageableDefault(size = 20, page = 0) Pageable paginacao
    ) {
        return coletaService.listarTodos(paginacao);
    }

    //OK
    @GetMapping("/coleta/{coletaId}")
    public ResponseEntity<ColetaExibicaoDTO> buscarPorId(@PathVariable Long coletaId) {
        return ResponseEntity.ok(coletaService.buscarPorId(coletaId));
    }

    //OK
    @DeleteMapping("/coleta/{coletaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long coletaId) {
        coletaService.excluir(coletaId);
    }

    //OK
    @PutMapping("/coleta")
    public ResponseEntity<ColetaExibicaoDTO> atualizar(
            @RequestBody ColetaCadastroDTO coletaDTO) {
        try {
            ColetaExibicaoDTO coletaExibicaoDTO =
                    coletaService.atualizar(coletaDTO);
            return ResponseEntity.ok(coletaExibicaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
