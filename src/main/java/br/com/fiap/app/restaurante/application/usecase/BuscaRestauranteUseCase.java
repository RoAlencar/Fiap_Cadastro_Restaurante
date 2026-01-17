package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.dto.response.BuscaRestauranteResponse;
import br.com.fiap.app.restaurante.application.port.BuscaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping
public class BuscaRestauranteUseCase implements BuscaRestauranteUseCasePort {

    private RestauranteRepositoryPort restauranteRepositoryPort;
    private ModelMapper modelMapper;


    @Override
    public List<BuscaRestauranteResponse> buscaTodosRestaurantes() {
        return restauranteRepositoryPort.findAll().stream()
                .map(entity-> modelMapper.map(entity, BuscaRestauranteResponse.class)).toList();
    }

    @Override
    public List<BuscaRestauranteResponse> buscaRestaurantePorNome(String nome) throws RestauranteNotFoundException {
        List<Restaurante> restaurantes = restauranteRepositoryPort.findByNomeLikeIgnoreCaseAndAccent(nome);

        if(restaurantes.isEmpty()){
            throw new RestauranteNotFoundException();
        }

        return restaurantes.stream()
                .map(entity-> modelMapper.map(entity, BuscaRestauranteResponse.class))
                .toList();
    }
}
