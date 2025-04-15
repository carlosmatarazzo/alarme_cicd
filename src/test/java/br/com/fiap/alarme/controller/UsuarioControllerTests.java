package br.com.fiap.alarme.controller;

import br.com.fiap.alarme.config.security.TokenService;
import br.com.fiap.alarme.config.security.VerificarToken;
import br.com.fiap.alarme.dto.UsuarioExibicaoDTO;
import br.com.fiap.alarme.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private TokenService tokenService; // necessário porque VerificarToken o injeta

    @MockBean
    private VerificarToken verificarToken; // necessário porque está no SecurityConfig

    @Test
    void deveRetornarListaDeUsuariosComStatus200() throws Exception {
        UsuarioExibicaoDTO usuario1 = new UsuarioExibicaoDTO(1L, "João", "joao@email.com");
        UsuarioExibicaoDTO usuario2 = new UsuarioExibicaoDTO(2L, "Maria", "maria@email.com");

        when(usuarioService.listarTodos()).thenReturn(List.of(usuario1, usuario2));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].usuarioId").value(1L))
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[1].usuarioId").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }
}
