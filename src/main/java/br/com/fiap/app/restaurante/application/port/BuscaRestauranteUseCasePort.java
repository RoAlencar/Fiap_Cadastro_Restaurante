package br.com.fiap.app.restaurante.application.port;

import br.com.fiap.app.restaurante.application.dto.response.BuscaRestauranteResponse;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;

import java.util.List;

public interface BuscaRestauranteUseCasePort {

    List<BuscaRestauranteResponse> buscaTodosRestaurantes();

    List<BuscaRestauranteResponse> buscaRestaurantePorNome(String nome) throws RestauranteNotFoundException;
}
