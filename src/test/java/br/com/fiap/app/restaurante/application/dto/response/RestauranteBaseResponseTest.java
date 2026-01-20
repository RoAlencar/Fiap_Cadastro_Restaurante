package br.com.fiap.app.restaurante.application.dto.response;

import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestauranteBaseResponseTest {

    @Test
    void deveCriarObjetoUsandoBuilder() {
        Endereco endereco = new Endereco();
        HorarioFuncionamento horario = new HorarioFuncionamento();

        RestauranteBaseResponse response = RestauranteBaseResponse.builder()
                .id(1L)
                .nome("Restaurante Teste")
                .endereco(endereco)
                .horarioFuncionamento(horario)
                .donoRestaurante("João")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .build();

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getNome()).isEqualTo("Restaurante Teste");
        assertThat(response.getEndereco()).isEqualTo(endereco);
        assertThat(response.getHorarioFuncionamento()).isEqualTo(horario);
        assertThat(response.getDonoRestaurante()).isEqualTo("João");
        assertThat(response.getTipoRestaurante()).isEqualTo(TipoRestaurante.RESTAURANTE);
    }

    @Test
    void deveCriarObjetoUsandoConstrutorDeCopia() {
        RestauranteBaseResponse original = RestauranteBaseResponse.builder()
                .id(2L)
                .nome("Restaurante Original")
                .endereco(new Endereco())
                .horarioFuncionamento(new HorarioFuncionamento())
                .donoRestaurante("Maria")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .build();

        RestauranteBaseResponse copia = new RestauranteBaseResponse(original);

        assertThat(copia).usingRecursiveComparison()
                .isEqualTo(original);
    }

    @Test
    void devePermitirUsoDeGettersESetters() {
        RestauranteBaseResponse response = new RestauranteBaseResponse();

        response.setId(3L);
        response.setNome("Restaurante Setter");
        response.setEndereco(new Endereco());
        response.setHorarioFuncionamento(new HorarioFuncionamento());
        response.setDonoRestaurante("Carlos");
        response.setTipoRestaurante(TipoRestaurante.RESTAURANTE);

        assertThat(response.getId()).isEqualTo(3L);
        assertThat(response.getNome()).isEqualTo("Restaurante Setter");
        assertThat(response.getDonoRestaurante()).isEqualTo("Carlos");
        assertThat(response.getTipoRestaurante()).isEqualTo(TipoRestaurante.RESTAURANTE);
    }
}
