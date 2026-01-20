package br.com.fiap.app.restaurante.application.dto.request;

import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtualizaRestauranteDtoTest {

    @Test
    void deveCriarObjetoUsandoBuilder() {
        Endereco endereco = new Endereco();
        HorarioFuncionamento horario = new HorarioFuncionamento();

        AtualizaRestauranteDto dto = AtualizaRestauranteDto.builder()
                .id(10L)
                .nome("Restaurante Builder")
                .endereco(endereco)
                .horarioFuncionamento(horario)
                .donoRestaurante("João")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .build();

        assertThat(dto.getId()).isEqualTo(10L);
        assertThat(dto.getNome()).isEqualTo("Restaurante Builder");
        assertThat(dto.getEndereco()).isEqualTo(endereco);
        assertThat(dto.getHorarioFuncionamento()).isEqualTo(horario);
        assertThat(dto.getDonoRestaurante()).isEqualTo("João");
        assertThat(dto.getTipoRestaurante()).isEqualTo(TipoRestaurante.RESTAURANTE);
    }

    @Test
    void deveCriarObjetoUsandoConstrutorDeCopiaSemCopiarId() {
        AtualizaRestauranteDto original = AtualizaRestauranteDto.builder()
                .id(99L)
                .nome("Restaurante Original")
                .endereco(new Endereco())
                .horarioFuncionamento(new HorarioFuncionamento())
                .donoRestaurante("Maria")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .build();

        AtualizaRestauranteDto copia = new AtualizaRestauranteDto(original);

        assertThat(copia.getId()).isNull(); // importante 👈
        assertThat(copia.getNome()).isEqualTo(original.getNome());
        assertThat(copia.getEndereco()).isEqualTo(original.getEndereco());
        assertThat(copia.getHorarioFuncionamento()).isEqualTo(original.getHorarioFuncionamento());
        assertThat(copia.getDonoRestaurante()).isEqualTo(original.getDonoRestaurante());
        assertThat(copia.getTipoRestaurante()).isEqualTo(original.getTipoRestaurante());
    }

    @Test
    void devePermitirUsoDeGettersESetters() {
        AtualizaRestauranteDto dto = new AtualizaRestauranteDto();

        dto.setId(5L);
        dto.setNome("Restaurante Setter");
        dto.setEndereco(new Endereco());
        dto.setHorarioFuncionamento(new HorarioFuncionamento());
        dto.setDonoRestaurante("Carlos");
        dto.setTipoRestaurante(TipoRestaurante.BAR);

        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getNome()).isEqualTo("Restaurante Setter");
        assertThat(dto.getDonoRestaurante()).isEqualTo("Carlos");
        assertThat(dto.getTipoRestaurante()).isEqualTo(TipoRestaurante.BAR);
    }
}
