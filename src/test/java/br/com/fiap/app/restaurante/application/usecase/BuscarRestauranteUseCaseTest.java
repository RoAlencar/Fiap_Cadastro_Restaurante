package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.dto.response.BuscaRestauranteResponse;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import br.com.fiap.app.restaurante.utils.RestauranteTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscarRestauranteUseCaseTest {

    private RestauranteRepositoryPort restauranteRepositoryPort;
    private ModelMapper modelMapper;
    private BuscaRestauranteUseCase buscaRestauranteUseCase;

    @BeforeEach
    void setUp() {
        restauranteRepositoryPort = Mockito.mock(RestauranteRepositoryPort.class);
        modelMapper = new ModelMapper();
        buscaRestauranteUseCase = new BuscaRestauranteUseCase(restauranteRepositoryPort, modelMapper);
    }

    @Test
    void buscaRestauranteUseCaseTest() {
        Restaurante restaurante = RestauranteTestUtils.gerarRestaurante();

        Restaurante restaurante2 = RestauranteTestUtils.gerarRestaurante();
        restaurante2.setId(2L);
        restaurante2.setNome("Restaurante B");

        when(restauranteRepositoryPort.findAll()).thenReturn(List.of(restaurante, restaurante2));

        List<BuscaRestauranteResponse> resultado = buscaRestauranteUseCase.buscaTodosRestaurantes();

        assertEquals(2, resultado.size());
        assertEquals("Restaurante", resultado.get(0).getNome());
        assertEquals("Restaurante B", resultado.get(1).getNome());

        verify(restauranteRepositoryPort,times(1)).findAll();
    }


    @Test
    void deveBuscarRestaurantePorNome() throws RestauranteNotFoundException {
        Restaurante restaurante = RestauranteTestUtils.gerarRestaurante();

        when(restauranteRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("Restaurante")).thenReturn(Collections.singletonList(restaurante));

        List<BuscaRestauranteResponse> resultado = buscaRestauranteUseCase.buscaRestaurantePorNome("Restaurante");

        assertEquals("Restaurante", resultado.get(0).getNome());

        verify(restauranteRepositoryPort, times(1)).findByNomeLikeIgnoreCaseAndAccent("Restaurante");
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontradoPorNome() {
        when(restauranteRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("Inexistente"))
                .thenReturn(Collections.emptyList());

        try {
            buscaRestauranteUseCase.buscaRestaurantePorNome("Inexistente");
        } catch (RestauranteNotFoundException e) {
            assertEquals(null, e.getMessage());
        }

        verify(restauranteRepositoryPort, times(1)).findByNomeLikeIgnoreCaseAndAccent("Inexistente");
    }
}
