package br.com.fiap.alarme.dto;

// Não existe esse arquivo DTO na versão do professor
public record UsuarioAtualizacaoDTO(
        Long usuarioId,
        String nome,
        String email,
        //adicionado para tentar fazer gravar o ROle, pois ainda não grava ao criar o usuário pelo Insomnia
        String role
) {
}

