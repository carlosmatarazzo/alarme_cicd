package br.com.fiap.alarme.service;

import br.com.fiap.alarme.dto.UsuarioAtualizacaoDTO;
import br.com.fiap.alarme.dto.UsuarioCadastroDTO;
import br.com.fiap.alarme.dto.UsuarioExibicaoDTO;
import br.com.fiap.alarme.exception.UsuarioNaoEncontradoException;
import br.com.fiap.alarme.model.Usuario;
import br.com.fiap.alarme.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO){

        String senhaCriptografada = new
                BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDTO(usuarioSalvo);

    }

    public UsuarioExibicaoDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioExibicaoDTO::new).orElseThrow(() ->
                new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + id));
    }


    public List<UsuarioExibicaoDTO> listarTodos(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDTO::new)
                .toList();
    }

    public void excluir(Long id){
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    public UsuarioExibicaoDTO atualizar(UsuarioAtualizacaoDTO usuarioDTO){
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(usuarioDTO.usuarioId());

        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            BeanUtils.copyProperties(usuarioDTO, usuario, "senha");
            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            return new UsuarioExibicaoDTO(usuarioAtualizado);
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

}