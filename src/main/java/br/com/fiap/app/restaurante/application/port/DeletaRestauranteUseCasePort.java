package br.com.fiap.app.restaurante.application.port;

import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;

public interface DeletaRestauranteUseCasePort {

    void deletaRestaurante(Long id) throws RestauranteNotFoundException;
}
