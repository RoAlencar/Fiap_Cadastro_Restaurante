package br.com.fiap.app.restaurante.adapter.in.web;

import br.com.fiap.app.restaurante.application.dto.request.AtualizaRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.request.CriarRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.AtualizaRestauranteResponse;
import br.com.fiap.app.restaurante.application.dto.response.BuscaRestauranteResponse;
import br.com.fiap.app.restaurante.application.dto.response.CriarRestauranteResponse;
import br.com.fiap.app.restaurante.application.port.AtualizaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.BuscaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.CriarRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.DeletaRestauranteUseCasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistroRestauranteControllerTest {

    @Mock
    private CriarRestauranteUseCasePort criarRestauranteUseCase;

    @Mock
    private AtualizaRestauranteUseCasePort atualizaRestauranteUseCase;

    @Mock
    private BuscaRestauranteUseCasePort buscaRestauranteUseCase;

    @Mock
    private DeletaRestauranteUseCasePort deletaRestauranteUseCase;

    @InjectMocks
    private RegistroRestauranteController controller;

    private CriarRestauranteDto criarDto;
    private CriarRestauranteResponse criarResponse;

    private AtualizaRestauranteDto atualizaDto;
    private AtualizaRestauranteResponse atualizaResponse;

    @BeforeEach
    void setup() {
        criarDto = new CriarRestauranteDto();
        criarDto.setNome("Restaurante Teste");

        criarResponse = new CriarRestauranteResponse();
        criarResponse.setNome("Restaurante Teste");

        atualizaDto = new AtualizaRestauranteDto();
        atualizaDto.setId(1L);
        atualizaDto.setNome("Restaurante Atualizado");

        atualizaResponse = new AtualizaRestauranteResponse();
        atualizaResponse.setNome("Restaurante Atualizado");
    }

    @Test
    void deveBuscarTodosRestaurantesComSucesso() {
        // Arrange
        List<BuscaRestauranteResponse> lista = List.of(new BuscaRestauranteResponse());
        when(buscaRestauranteUseCase.buscaTodosRestaurantes()).thenReturn(lista);

        // Act
        ResponseEntity<List<BuscaRestauranteResponse>> response =
                controller.buscaTodosRestaurantes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lista, response.getBody());
        verify(buscaRestauranteUseCase).buscaTodosRestaurantes();
    }

    @Test
    void deveBuscarRestaurantePorNomeComSucesso() throws Exception {
        // Arrange
        List<BuscaRestauranteResponse> lista = List.of(new BuscaRestauranteResponse());
        when(buscaRestauranteUseCase.buscaRestaurantePorNome("Pizza"))
                .thenReturn(lista);

        // Act
        ResponseEntity<List<BuscaRestauranteResponse>> response =
                controller.buscaRestaurantePorNome("Pizza");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lista, response.getBody());
        verify(buscaRestauranteUseCase).buscaRestaurantePorNome("Pizza");
    }

    @Test
    void deveCriarRestauranteComSucesso() throws Exception {
        // Arrange
        when(criarRestauranteUseCase.criarRestaurante(criarDto))
                .thenReturn(criarResponse);

        // Act
        ResponseEntity<CriarRestauranteResponse> response =
                controller.criarRestaurante(criarDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(criarResponse, response.getBody());
        verify(criarRestauranteUseCase).criarRestaurante(criarDto);
    }

    @Test
    void deveAtualizarRestauranteComSucesso() throws Exception {
        // Arrange
        when(atualizaRestauranteUseCase.atualizaRestaurante(atualizaDto))
                .thenReturn(atualizaResponse);

        // Act
        ResponseEntity<AtualizaRestauranteResponse> response =
                controller.atualizaRestaurante(atualizaDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atualizaResponse, response.getBody());
        verify(atualizaRestauranteUseCase).atualizaRestaurante(atualizaDto);
    }

    @Test
    void deveDeletarRestauranteComSucesso() throws Exception {
        // Act
        ResponseEntity<Void> response = controller.deletaRestaurante(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletaRestauranteUseCase).deletaRestaurante(1L);
    }
}
