package br.com.fiap.alarme.service;

import br.com.fiap.alarme.dto.RecipienteCadastroDTO;
import br.com.fiap.alarme.dto.RecipienteExibicaoDTO;
import br.com.fiap.alarme.model.Recipiente;
import br.com.fiap.alarme.repository.RecipienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipienteService {

    @Autowired
    private RecipienteRepository recipienteRepository;

    //OK
    public RecipienteExibicaoDTO salvarRecipiente(RecipienteCadastroDTO recipienteDTO) {
        Recipiente recipiente = new Recipiente();
        BeanUtils.copyProperties(recipienteDTO, recipiente);
        Recipiente recipienteSalvo = recipienteRepository.save(recipiente);
        return new RecipienteExibicaoDTO(recipienteSalvo);
    }

    //OK
    public RecipienteExibicaoDTO buscarPorId(Long id){
        Optional<Recipiente> recipienteOptional =
                recipienteRepository.findById(id);

        if (recipienteOptional.isPresent()) {
            return new RecipienteExibicaoDTO(recipienteOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public Page<RecipienteExibicaoDTO> listarTodos(Pageable paginacao) {
        return recipienteRepository
                .findAll(paginacao)
                .map(RecipienteExibicaoDTO::new);
    }

    //OK
    public void excluir(Long id){
        Optional<Recipiente> recipienteOptional =
                recipienteRepository.findById(id);

        if (recipienteOptional.isPresent()){
            recipienteRepository.delete(recipienteOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public RecipienteExibicaoDTO atualizar(RecipienteCadastroDTO recipienteDTO) {
        Optional<Recipiente> recipienteOptional =
                recipienteRepository.findById(recipienteDTO.recipienteId());

        if (recipienteOptional.isPresent()) {
            Recipiente recipiente = new Recipiente();
            BeanUtils.copyProperties(recipienteDTO, recipiente);
            return new RecipienteExibicaoDTO(recipienteRepository.save(recipiente));
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

}