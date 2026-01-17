package br.com.fiap.app.restaurante.application.dto.response;

import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
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
public class RestauranteBaseResponse {

    private Long id;
    private String nome;
    private Endereco endereco;
    private HorarioFuncionamento horarioFuncionamento;
    private String donoRestaurante;
    private TipoRestaurante tipoRestaurante;

    public RestauranteBaseResponse(RestauranteBaseResponse restauranteBaseResponse) {
        this.id = restauranteBaseResponse.getId();
        this.nome = restauranteBaseResponse.getNome();
        this.endereco = restauranteBaseResponse.getEndereco();
        this.horarioFuncionamento = restauranteBaseResponse.getHorarioFuncionamento();
        this.donoRestaurante = restauranteBaseResponse.getDonoRestaurante();
        this.tipoRestaurante = restauranteBaseResponse.getTipoRestaurante();
    }
}
