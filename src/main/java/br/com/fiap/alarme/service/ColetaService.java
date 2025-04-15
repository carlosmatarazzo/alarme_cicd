package br.com.fiap.alarme.service;

import br.com.fiap.alarme.dto.ColetaCadastroDTO;
import br.com.fiap.alarme.dto.ColetaExibicaoDTO;
import br.com.fiap.alarme.model.Coleta;
import br.com.fiap.alarme.model.Recipiente;
import br.com.fiap.alarme.repository.ColetaRepository;
import br.com.fiap.alarme.repository.RecipienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private RecipienteRepository recipienteRepository;

    //OK
    public ColetaExibicaoDTO salvarColeta(ColetaCadastroDTO coletaDTO) {
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaDTO, coleta);
        coleta = coletaRepository.save(coleta);

        // Atualiza a capacidade atual do recipiente associado
        Recipiente recipiente = recipienteRepository.findById(coleta.getRecipienteId())
                .orElseThrow(() -> new RuntimeException("Recipiente não encontrado!"));
        recipiente.setCapacidadeAtual(0); // Define a capacidade atual para zero

        // Salva as atualizações do recipiente
        recipienteRepository.save(recipiente);

        return new ColetaExibicaoDTO(coleta);
    }

    //OK
    public ColetaExibicaoDTO buscarPorId(Long id){
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(id);

        if (coletaOptional.isPresent()) {
            return new ColetaExibicaoDTO(coletaOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public Page<ColetaExibicaoDTO> listarTodos(Pageable paginacao) {
        return coletaRepository
                .findAll(paginacao)
                .map(ColetaExibicaoDTO::new);
    }

    //OK
    public void excluir(Long id){
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(id);

        if (coletaOptional.isPresent()){
            coletaRepository.delete(coletaOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public ColetaExibicaoDTO atualizar(ColetaCadastroDTO coletaDTO) {
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(coletaDTO.coletaId());

        if (coletaOptional.isPresent()) {
            Coleta coleta = new Coleta();
            BeanUtils.copyProperties(coletaDTO, coleta);
            return new ColetaExibicaoDTO(coletaRepository.save(coleta));
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

}
