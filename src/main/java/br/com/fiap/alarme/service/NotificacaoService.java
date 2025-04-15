package br.com.fiap.alarme.service;

import br.com.fiap.alarme.dto.NotificacaoCadastroDTO;
import br.com.fiap.alarme.dto.NotificacaoExibicaoDTO;
import br.com.fiap.alarme.model.Notificacao;
import br.com.fiap.alarme.repository.NotificacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    //OK
    public NotificacaoExibicaoDTO salvarNotificacao(NotificacaoCadastroDTO notificacaoDTO) {
        Notificacao notificacao = new Notificacao();
        BeanUtils.copyProperties(notificacaoDTO, notificacao);
        Notificacao notificacaoSalvo = notificacaoRepository.save(notificacao);
        return new NotificacaoExibicaoDTO(notificacaoSalvo);
    }

    //OK
    public NotificacaoExibicaoDTO buscarPorId(Long id){
        Optional<Notificacao> notificacaoOptional =
                notificacaoRepository.findById(id);

        if (notificacaoOptional.isPresent()) {
            return new NotificacaoExibicaoDTO(notificacaoOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public Page<NotificacaoExibicaoDTO> listarTodos(Pageable paginacao) {
        return notificacaoRepository
                .findAll(paginacao)
                .map(NotificacaoExibicaoDTO::new);
    }

    //OK
    public void excluir(Long id){
        Optional<Notificacao> notificacaoOptional =
                notificacaoRepository.findById(id);

        if (notificacaoOptional.isPresent()){
            notificacaoRepository.delete(notificacaoOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public NotificacaoExibicaoDTO atualizar(NotificacaoCadastroDTO notificacaoDTO) {
        Optional<Notificacao> notificacaoOptional =
                notificacaoRepository.findById(notificacaoDTO.notificacaoId());

        if (notificacaoOptional.isPresent()) {
            Notificacao notificacao = new Notificacao();
            BeanUtils.copyProperties(notificacaoDTO, notificacao);
            return new NotificacaoExibicaoDTO(notificacaoRepository.save(notificacao));
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

}
