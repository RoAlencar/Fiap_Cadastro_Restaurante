package br.com.fiap.app.restaurante.domain;

import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.entities.RestauranteEntity;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import br.com.fiap.app.restaurante.utils.RestauranteTestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RestauranteTest {

    @Test
    void validarRestaurante() {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante");
        restaurante.setTipoRestaurante(TipoRestaurante.RESTAURANTE);
        restaurante.setDonoRestaurante("DonoRestaurante");
        restaurante.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9,0))
                .fechamento(LocalTime.of(18,0))
                .build());
        restaurante.setEndereco(Endereco.builder()
                .logradouro("Av. Paulista")
                .numero("1000")
                .complemento("N/A")
                .cidade("São Paulo")
                .cep("01310-100")
                .build());

        assertThat(restaurante.getId()).isEqualTo(1L);
        assertThat(restaurante.getNome()).isEqualTo("Restaurante");
        assertThat(restaurante.getTipoRestaurante()).isEqualTo(TipoRestaurante.RESTAURANTE);
        assertThat(restaurante.getDonoRestaurante()).isEqualTo("DonoRestaurante");
        assertThat(restaurante.getHorarioFuncionamento()).isNotNull();
        assertThat(restaurante.getEndereco()).isNotNull();
    }

    @Test
    void restaurenteBuilder(){
        Long id = 1L;
        String nome = "Restaurante";
        TipoRestaurante tipoRestaurante = TipoRestaurante.RESTAURANTE;
        String donoRestaurante = "user";
        LocalTime abertura = LocalTime.of(9,0);
        LocalTime fechamento = LocalTime.of(18,0);
        String logradouro = "Av. Paulista";
        String numero = "1000";
        String complemento = "N/A";
        String cidade = "São Paulo";
        String cep = "01310-100";


        Restaurante restaurante = RestauranteTestUtils.gerarRestaurante();

        assertThat(restaurante.getId()).isEqualTo(id);
        assertThat(restaurante.getNome()).isEqualTo(nome);
        assertThat(restaurante.getTipoRestaurante()).isEqualTo(tipoRestaurante);
        assertThat(restaurante.getDonoRestaurante()).isEqualTo(donoRestaurante);
        assertThat(restaurante.getHorarioFuncionamento().getAbertura()).isEqualTo(abertura);
        assertThat(restaurante.getHorarioFuncionamento().getFechamento()).isEqualTo(fechamento);
        assertThat(restaurante.getEndereco().getLogradouro()).isEqualTo(logradouro);
        assertThat(restaurante.getEndereco().getNumero()).isEqualTo(numero);
        assertThat(restaurante.getEndereco().getComplemento()).isEqualTo(complemento);
        assertThat(restaurante.getEndereco().getCidade()).isEqualTo(cidade);
        assertThat(restaurante.getEndereco().getCep()).isEqualTo(cep);
    }

    @Test
    void deveRetornarEntityComCamposNulosQuandoRestauranteNulo(){

        Restaurante restaurante = new Restaurante();

        RestauranteEntity entity = restaurante.toEntity();

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getNome());
        assertNull(entity.getTipoRestaurante());
        assertNull(entity.getDonoRestaurante());
        assertNull(entity.getHorarioFuncionamento());
        assertNull(entity.getEndereco());
    }
}
