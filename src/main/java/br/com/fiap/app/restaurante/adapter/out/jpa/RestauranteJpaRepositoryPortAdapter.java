package br.com.fiap.app.restaurante.adapter.out.jpa;

import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.entities.RestauranteEntity;
import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.repositories.RestauranteJpaRepository;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Restaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestauranteJpaRepositoryPortAdapter implements RestauranteRepositoryPort {

    private final RestauranteJpaRepository restauranteJpaRepository;

    @Override
    public List<Restaurante> findAll() {
        return restauranteJpaRepository.findAll().stream().map(RestauranteEntity::toDomain).toList();
    }

    @Override
    public List<Restaurante> findByNomeLikeIgnoreCaseAndAccent(String nome) {
        return restauranteJpaRepository.findByNomeLikeIgnoreCaseAndAccent(nome).stream()
                .filter(Optional::isPresent)
                .map(optionalEntity -> optionalEntity.get().toDomain())
                .toList();
    }

    @Override
    public Optional<Restaurante> findById(Long id) {
        return restauranteJpaRepository.findById(id).map(RestauranteEntity::toDomain);
    }

    @Override
    public Optional<Restaurante> findByDonoRestaurante(String donoRestaurante) {
        return restauranteJpaRepository.findByDonoRestaurante(donoRestaurante).map(RestauranteEntity::toDomain);
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        return restauranteJpaRepository.save(restaurante.toEntity()).toDomain();
    }

    @Override
    public void deleteById(Long id) {
        restauranteJpaRepository.deleteById(id);
    }
}
