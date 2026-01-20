package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeletaRestauranteUseCaseTest {

    private RestauranteRepositoryPort restauranteRepositoryPort;
    private DeletaRestauranteUseCase deletaRestauranteUseCase;

    @BeforeEach
    void setUp() {
        restauranteRepositoryPort = mock(RestauranteRepositoryPort.class);
        deletaRestauranteUseCase = new DeletaRestauranteUseCase(restauranteRepositoryPort);
    }

    @Test
    void deveDeletarRestauranteComSucesso() throws RestauranteNotFoundException {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);

        when(restauranteRepositoryPort.findById(1L)).thenReturn(java.util.Optional.of(restaurante));

        deletaRestauranteUseCase.deletaRestaurante(1L);

        verify(restauranteRepositoryPort, times(1)).findById(1L);
        verify(restauranteRepositoryPort, times(1)).deleteById(1L);

    }

    @Test
    void deveLancarRestauranteNotFoundExceptionQuandoRestauranteNaoExiste() {
        when(restauranteRepositoryPort.findById(1L)).thenReturn(java.util.Optional.empty());

       assertThrows(RestauranteNotFoundException.class, () -> deletaRestauranteUseCase.deletaRestaurante(1L));

        verify(restauranteRepositoryPort, times(1)).findById(1L);
        verify(restauranteRepositoryPort, times(0)).deleteById(1L);
    }
}
