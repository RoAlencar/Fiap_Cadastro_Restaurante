package br.com.fiap.app.restaurante.adapter.out.jpa;

import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.entities.RestauranteEntity;
import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.repositories.RestauranteJpaRepository;
import br.com.fiap.app.restaurante.domain.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RestauranteJpaRepositoryAdapterTest {

    @Mock
    private RestauranteJpaRepository restauranteJpaRepository;

    @InjectMocks
    private RestauranteJpaRepositoryPortAdapter restauranteJpaRepositoryPortAdapter;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }

    @Test
    void buscaTodosRestaurantes(){
        RestauranteEntity restauranteEntity = mock(RestauranteEntity.class);
        RestauranteEntity restauranteEntity2 = mock(RestauranteEntity.class);

        when(restauranteJpaRepository.findAll()).thenReturn(java.util.Arrays.asList(restauranteEntity, restauranteEntity2));
        when(restauranteEntity.toDomain()).thenReturn(new Restaurante());
        when(restauranteEntity2.toDomain()).thenReturn(new Restaurante());

        List<Restaurante> restaurantes = restauranteJpaRepositoryPortAdapter.findAll();

        assertEquals(2, restaurantes.size());
    }

    @Test
    void buscaRestaurantePorId(){
        Long idRestaurante = 1L;
        RestauranteEntity restauranteEntity = mock(RestauranteEntity.class);

        when(restauranteJpaRepository.findById(idRestaurante)).thenReturn(java.util.Optional.of(restauranteEntity));
        when(restauranteEntity.toDomain()).thenReturn(new Restaurante());

        java.util.Optional<Restaurante> restaurante = restauranteJpaRepositoryPortAdapter.findById(idRestaurante);

        assertTrue(restaurante.isPresent());
    }


    @Test
    void buscaRestaurantePorIdInvalido() {
        Long idRestaurante = 1L;

        when(restauranteJpaRepository.findById(idRestaurante)).thenReturn(java.util.Optional.empty());

        java.util.Optional<Restaurante> restaurante = restauranteJpaRepositoryPortAdapter.findById(idRestaurante);

        assertTrue(restaurante.isEmpty());
    }

    @Test
    void salveRestaurante(){
        Restaurante restaurante = mock(Restaurante.class);
        RestauranteEntity restauranteEntity = mock(RestauranteEntity.class);

        when(restaurante.toEntity()).thenReturn(restauranteEntity);
        when(restauranteJpaRepository.save(restauranteEntity)).thenReturn(restauranteEntity);
        when(restauranteEntity.toDomain()).thenReturn(restaurante);

        Restaurante restauranteSalvo = restauranteJpaRepositoryPortAdapter.save(restaurante);

        assertEquals(restaurante, restauranteSalvo);
    }

}
