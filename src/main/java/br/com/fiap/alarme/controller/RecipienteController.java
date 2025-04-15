package br.com.fiap.alarme.controller;

import br.com.fiap.alarme.dto.RecipienteCadastroDTO;
import br.com.fiap.alarme.dto.RecipienteExibicaoDTO;
import br.com.fiap.alarme.service.RecipienteService;
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
public class RecipienteController {

    @Autowired
    private RecipienteService recipienteService;

    //OK
    @PostMapping("/recipiente")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipienteExibicaoDTO salvar(
            @RequestBody @Valid RecipienteCadastroDTO recipiente) {
        return recipienteService.salvarRecipiente(recipiente);
    }

    //OK
    @GetMapping("/recipiente")
    @ResponseStatus(HttpStatus.OK)
    public Page<RecipienteExibicaoDTO> litarTodos(
            @PageableDefault(size = 20, page = 0) Pageable paginacao
    ) {
        return recipienteService.listarTodos(paginacao);
    }

    //OK
    @GetMapping("/recipiente/{recipienteId}")
    public ResponseEntity<RecipienteExibicaoDTO> buscarPorId(@PathVariable Long recipienteId) {
        return ResponseEntity.ok(recipienteService.buscarPorId(recipienteId));
    }

    //OK
    @DeleteMapping("/recipiente/{recipienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long recipienteId) {
        recipienteService.excluir(recipienteId);
    }

    //OK
    @PutMapping("/recipiente")
    public ResponseEntity<RecipienteExibicaoDTO> atualizar(
            @RequestBody RecipienteCadastroDTO recipienteDTO) {
        try {
            RecipienteExibicaoDTO recipienteExibicaoDTO =
                    recipienteService.atualizar(recipienteDTO);
            return ResponseEntity.ok(recipienteExibicaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
