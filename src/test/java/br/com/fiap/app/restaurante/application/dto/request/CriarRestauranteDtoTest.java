package br.com.fiap.app.restaurante.application.dto.request;

import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriarRestauranteDtoTest {

    @Test
    void deveCriarDtoComConstrutorPadraoESetter() {

        CriarRestauranteDto dto = new CriarRestauranteDto();
        Endereco endereco = Endereco.builder()
                .logradouro("Rua A")
                .numero("123")
                .complemento("N/A")
                .cidade("São Paulo")
                .cep("01000-000")
                .build();

        HorarioFuncionamento horarioFuncionamento = HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9,0))
                .fechamento(LocalTime.of(18,0))
                .build();

        dto.setNome("Restaurante");
        dto.setEndereco(endereco);
        dto.setHorarioFuncionamento(horarioFuncionamento);
        dto.setDonoRestaurante("Teste");
        dto.setTipoRestaurante(TipoRestaurante.RESTAURANTE);

        assertEquals(TipoRestaurante.RESTAURANTE, dto.getTipoRestaurante());
        assertEquals("Restaurante", dto.getNome());
        assertEquals(dto.getEndereco(), endereco);
        assertEquals(dto.getHorarioFuncionamento(), horarioFuncionamento);
        assertEquals("Teste",dto.getDonoRestaurante());
    }

    @Test
    void deveCriarDtoComConstrutorDeCopia(){

        Endereco endereco = Endereco.builder()
                .logradouro("Rua A")
                .numero("123")
                .complemento("N/A")
                .cidade("São Paulo")
                .cep("01000-000")
                .build();

        HorarioFuncionamento horarioFuncionamento = HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9,0))
                .fechamento(LocalTime.of(18,0))
                .build();

        CriarRestauranteDto original =  CriarRestauranteDto.builder()
                .nome("teste")
                .endereco(endereco)
                .horarioFuncionamento(horarioFuncionamento)
                .donoRestaurante("teste")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .build();

        CriarRestauranteDto copia = new CriarRestauranteDto(original);

        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getEndereco(), copia.getEndereco());
        assertEquals(original.getHorarioFuncionamento(), copia.getHorarioFuncionamento());
        assertEquals(original.getDonoRestaurante(), copia.getDonoRestaurante());
        assertEquals(original.getTipoRestaurante(), copia.getTipoRestaurante());
    }
}
