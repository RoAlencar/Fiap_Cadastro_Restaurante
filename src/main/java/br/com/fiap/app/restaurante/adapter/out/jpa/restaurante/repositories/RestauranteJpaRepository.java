package br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.repositories;

import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteJpaRepository extends JpaRepository<RestauranteEntity, Long> {

    @Query(value = "SELECT * FROM core_restaurante WHERE unaccent(LOWER(nome)) LIKE unaccent(LOWER(CONCAT('%', :nome, '%')))", nativeQuery = true)
    List<Optional<RestauranteEntity>> findByNomeLikeIgnoreCaseAndAccent(String nome);

    Optional<RestauranteEntity> findByDonoRestaurante(String donoRestaurante);

}
