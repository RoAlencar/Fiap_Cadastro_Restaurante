package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.port.DeletaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletaUsuarioUseCase implements DeletaRestauranteUseCasePort {

    private final RestauranteRepositoryPort restauranteRepositoryPort;

    @Override
    public void deletaRestaurante(Long id) throws RestauranteNotFoundException {
        Restaurante restaurante = restauranteRepositoryPort.findById(id).orElseThrow(RestauranteNotFoundException::new);
        restauranteRepositoryPort.deleteById(restaurante.getId());
    }
}
