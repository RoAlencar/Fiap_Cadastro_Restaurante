package br.com.fiap.app.restaurante.application.port;

import br.com.fiap.app.restaurante.application.dto.request.AtualizaRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.AtualizaRestauranteResponse;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;

public interface AtualizaRestauranteUseCasePort {
    AtualizaRestauranteResponse atualizaRestaurante (AtualizaRestauranteDto dto) throws RestauranteNotFoundException, NoChangesDetectedException;
}
