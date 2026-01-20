package br.com.fiap.app.restaurante.utils;

import br.com.fiap.app.restaurante.adapter.out.jpa.restaurante.entities.RestauranteEntity;
import br.com.fiap.app.restaurante.application.dto.request.AtualizaRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.request.CriarRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.CriarRestauranteResponse;
import br.com.fiap.app.restaurante.application.dto.response.RestauranteBaseResponse;
import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;

import java.time.LocalTime;

public class RestauranteTestUtils {

    private RestauranteTestUtils() {
    }

    public static Restaurante gerarRestaurante() {
        return Restaurante.builder()
                .id(1L)
                .nome("Restaurante")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .donoRestaurante("user")
                .horarioFuncionamento(HorarioFuncionamento.builder()
                        .abertura(LocalTime.of(9,0))
                        .fechamento(LocalTime.of(18,0))
                        .build())
                .endereco(Endereco.builder()
                        .logradouro("Av. Paulista")
                        .numero("1000")
                        .complemento("N/A")
                        .cidade("São Paulo")
                        .cep("01310-100")
                        .build())
                .build();
    }

    public static Restaurante gerarRestauranteAtualizado() {
        return Restaurante.builder()
                .id(1L)
                .nome("Restaurante de testes")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .donoRestaurante("user")
                .horarioFuncionamento(HorarioFuncionamento.builder()
                        .abertura(LocalTime.of(9,0))
                        .fechamento(LocalTime.of(18,0))
                        .build())
                .endereco(Endereco.builder()
                        .logradouro("Av. Paulista")
                        .numero("1000")
                        .complemento("N/A")
                        .cidade("São Paulo")
                        .cep("01310-100")
                        .build())
                .build();
    }

    public static CriarRestauranteDto gerarCriarRestauranteDto() {
        return CriarRestauranteDto.builder()
                .nome("Restaurante de testes")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .donoRestaurante("user")
                .horarioFuncionamento(HorarioFuncionamento.builder()
                        .abertura(LocalTime.of(9,0))
                        .fechamento(LocalTime.of(18,0))
                        .build())
                .endereco(Endereco.builder()
                        .logradouro("Av. Paulista")
                        .numero("1000")
                        .complemento("N/A")
                        .cidade("São Paulo")
                        .cep("01310-100")
                        .build())
                .build();
    }

    public static RestauranteEntity gerarRestauranteEntity() {
        return RestauranteEntity.builder()
                .id(1L)
                .nome("Restaurante de testes")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .donoRestaurante("user")
                .horarioFuncionamento(HorarioFuncionamento.builder()
                        .abertura(LocalTime.of(9,0))
                        .fechamento(LocalTime.of(18,0))
                        .build())
                .endereco(Endereco.builder()
                        .logradouro("Av. Paulista")
                        .numero("1000")
                        .complemento("N/A")
                        .cidade("São Paulo")
                        .cep("01310-100")
                        .build())
                .build();
    }

    public static CriarRestauranteResponse gerarCriarRestauranteResponse() {
        return new CriarRestauranteResponse(RestauranteBaseResponse.builder()
                .id(1L)
                .nome("Restaurante de testes")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .donoRestaurante("user")
                .horarioFuncionamento(HorarioFuncionamento.builder()
                        .abertura(LocalTime.of(9,0))
                        .fechamento(LocalTime.of(18,0))
                        .build())
                .endereco(Endereco.builder()
                        .logradouro("Av. Paulista")
                        .numero("1000")
                        .complemento("N/A")
                        .cidade("São Paulo")
                        .cep("01310-100")
                        .build())
                .build());
    }

}
