package br.com.fiap.app.restaurante.application.port;

import br.com.fiap.app.restaurante.domain.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepositoryPort{

    List<Restaurante> findAll();

    List<Restaurante> findByNomeLikeIgnoreCaseAndAccent(String name);

    Optional<Restaurante> findById(Long id);

    Optional<Restaurante> findByDonoRestaurante(String donoRestaurante);

    Restaurante save(Restaurante restaurante);

    void deleteById(Long id);
}
