package br.com.fiap.app.restaurante.domain;

import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.entities.RestauranteEntity;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurante {

    private Long id;
    private String nome;
    private Endereco endereco;
    private HorarioFuncionamento horarioFuncionamento;
    private String donoRestaurante;
    private TipoRestaurante tipoRestaurante;


    public Restaurante(Restaurante restaurante) {}

    public RestauranteEntity toEntity() {
        return RestauranteEntity.builder()
                .id(this.id)
                .nome(this.nome)
                .endereco(this.endereco)
                .horarioFuncionamento(this.horarioFuncionamento)
                .donoRestaurante(this.donoRestaurante)
                .tipoRestaurante(this.tipoRestaurante)
                .build();
    }
}
