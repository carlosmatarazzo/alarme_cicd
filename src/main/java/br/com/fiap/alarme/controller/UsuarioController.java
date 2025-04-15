package br.com.fiap.alarme.controller;

import br.com.fiap.alarme.dto.UsuarioAtualizacaoDTO;
import br.com.fiap.alarme.dto.UsuarioCadastroDTO;
import br.com.fiap.alarme.dto.UsuarioExibicaoDTO;
import br.com.fiap.alarme.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody @Valid UsuarioCadastroDTO usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> listarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable Long usuarioId){
        return ResponseEntity.ok(usuarioService.buscarPorId(usuarioId));
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO atualizar(@RequestBody UsuarioAtualizacaoDTO usuario){
        return usuarioService.atualizar(usuario);
    }

}