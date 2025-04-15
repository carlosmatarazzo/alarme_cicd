package br.com.fiap.alarme.service;

import br.com.fiap.alarme.dto.DepositoCadastroDTO;
import br.com.fiap.alarme.dto.DepositoExibicaoDTO;
import br.com.fiap.alarme.dto.NotificacaoCadastroDTO;
import br.com.fiap.alarme.model.Deposito;
import br.com.fiap.alarme.model.Recipiente;
import br.com.fiap.alarme.repository.DepositoRepository;
import br.com.fiap.alarme.repository.RecipienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private RecipienteRepository recipienteRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    //OK
    public DepositoExibicaoDTO salvarDeposito(DepositoCadastroDTO depositoDTO) {
        Deposito deposito = new Deposito();
        BeanUtils.copyProperties(depositoDTO, deposito);
        deposito = depositoRepository.save(deposito);

        // Atualiza a capacidade atual do recipiente
        Recipiente recipiente = recipienteRepository.findById(deposito.getRecipienteId())
                .orElseThrow(() -> new RuntimeException("Recipiente não encontrado!"));
        recipiente.setCapacidadeAtual(recipiente.getCapacidadeAtual() + deposito.getPeso());

        // Verifica se a capacidade atual ultrapassa a capacidade total
        if (recipiente.getCapacidadeAtual() >= recipiente.getCapacidadeTotal()) {
            // Cria uma notificação
            NotificacaoCadastroDTO notificacaoDTO = new NotificacaoCadastroDTO(null, recipiente.getRecipienteId(), LocalDate.now());
            notificacaoService.salvarNotificacao(notificacaoDTO);
        }

        // Salva as atualizações do recipiente
        recipienteRepository.save(recipiente);

        return new DepositoExibicaoDTO(deposito);
    }

    //OK
    public DepositoExibicaoDTO buscarPorId(Long id){
        Optional<Deposito> depositoOptional =
                depositoRepository.findById(id);

        if (depositoOptional.isPresent()) {
            return new DepositoExibicaoDTO(depositoOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public Page<DepositoExibicaoDTO> listarTodos(Pageable paginacao) {
        return depositoRepository
                .findAll(paginacao)
                .map(DepositoExibicaoDTO::new);
    }

    //OK
    public void excluir(Long id){
        Optional<Deposito> depositoOptional =
                depositoRepository.findById(id);

        if (depositoOptional.isPresent()){
            depositoRepository.delete(depositoOptional.get());
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

    //OK
    public DepositoExibicaoDTO atualizar(DepositoCadastroDTO depositoDTO) {
        Optional<Deposito> depositoOptional =
                depositoRepository.findById(depositoDTO.depositoId());

        if (depositoOptional.isPresent()) {
            Deposito deposito = new Deposito();
            BeanUtils.copyProperties(depositoDTO, deposito);
            return new DepositoExibicaoDTO(depositoRepository.save(deposito));
        } else {
            throw new RuntimeException("Não encontrado!");
        }
    }

}