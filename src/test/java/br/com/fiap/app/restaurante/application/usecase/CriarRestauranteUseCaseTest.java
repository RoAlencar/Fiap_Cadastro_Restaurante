package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.dto.request.CriarRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.CriarRestauranteResponse;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.DonoRestaurenteRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioAberturaRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioFechamentoRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RequestRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.TipoRestauranteRequiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CriarRestauranteUseCaseTest {

    private RestauranteRepositoryPort restauranteRepositoryPort;
    private ModelMapper modelMapper;
    private CriarRestauranteUseCase criarRestauranteUseCase;

    @BeforeEach
    void setUp() {
        restauranteRepositoryPort = mock(RestauranteRepositoryPort.class);
        modelMapper = new ModelMapper();
        criarRestauranteUseCase = new CriarRestauranteUseCase(restauranteRepositoryPort, modelMapper);
    }


    @Test
    void deveCriarRestauranteComSucesso() throws RequestRequiredException, AddressRequiredException, HorarioAberturaRequiredException, DonoRestaurenteRequiredException, TipoRestauranteRequiredException, NameRequiredException, HorarioFechamentoRequiredException {

        CriarRestauranteDto dto = new CriarRestauranteDto();
        dto.setNome("Restaurante Exemplo");
        dto.setTipoRestaurante(TipoRestaurante.RESTAURANTE);
        dto.setDonoRestaurante("Dono Exemplo");
        dto.setEndereco(Endereco.builder()
                .logradouro("teste").numero("123").complemento("teste").cidade("teste").cep("12345-678").build());
        dto.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9, 0))
                .fechamento(LocalTime.of(18, 0)).build());

        Restaurante restaurante = Restaurante.builder()
                .nome(dto.getNome())
                .tipoRestaurante(dto.getTipoRestaurante())
                .donoRestaurante(dto.getDonoRestaurante())
                .endereco(dto.getEndereco())
                .horarioFuncionamento(dto.getHorarioFuncionamento())
                .build();

        when(restauranteRepositoryPort.save(any(Restaurante.class))).thenReturn(restaurante);

        CriarRestauranteResponse response = criarRestauranteUseCase.criarRestaurante(dto);

        assertNotNull(response);
        assertEquals("Restaurante Exemplo", response.getNome());
        assertEquals(TipoRestaurante.RESTAURANTE, response.getTipoRestaurante());
        assertEquals("Dono Exemplo", response.getDonoRestaurante());
    }

    @Test
    void deveLancarRequestRequiredExceptionQuandoDtoForNulo() {
        try {
            criarRestauranteUseCase.criarRestaurante(null);
        } catch (Exception e) {
            assertEquals(RequestRequiredException.class, e.getClass());
        }
    }

    @Test
    void deveLancarAddressRequiredExceptionQuandoDtoForNulo() {
        CriarRestauranteDto dto = new CriarRestauranteDto();
        dto.setNome("Restaurante Exemplo");
        dto.setTipoRestaurante(TipoRestaurante.RESTAURANTE);
        dto.setDonoRestaurante("Dono Exemplo");
        dto.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9, 0))
                .fechamento(LocalTime.of(18, 0)).build());
        try {
            criarRestauranteUseCase.criarRestaurante(dto);
        } catch (Exception e) {
            assertEquals(AddressRequiredException.class, e.getClass());
        }
    }

    @Test
    void deveLancarNameRequiredExceptionQuandoDtoForNulo() {
        CriarRestauranteDto dto = new CriarRestauranteDto();
        dto.setTipoRestaurante(TipoRestaurante.RESTAURANTE);
        dto.setDonoRestaurante("Dono Exemplo");
        dto.setEndereco(Endereco.builder()
                .logradouro("teste").numero("123").complemento("teste").cidade("teste").cep("12345-678").build());
        dto.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9, 0))
                .fechamento(LocalTime.of(18, 0)).build());
        try {
            criarRestauranteUseCase.criarRestaurante(dto);
        } catch (Exception e) {
            assertEquals(NameRequiredException.class, e.getClass());
        }
    }

    @Test
    void deveLancarTipoRestauranteRequiredExceptionQuandoDtoForNulo() {
        CriarRestauranteDto dto = new CriarRestauranteDto();
        dto.setNome("Restaurante Exemplo");
        dto.setDonoRestaurante("Dono Exemplo");
        dto.setEndereco(Endereco.builder()
                .logradouro("teste").numero("123").complemento("teste").cidade("teste").cep("12345-678").build());
        dto.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9, 0))
                .fechamento(LocalTime.of(18, 0)).build());
        try {
            criarRestauranteUseCase.criarRestaurante(dto);
        } catch (Exception e) {
            assertEquals(TipoRestauranteRequiredException.class, e.getClass());
        }
    }


    @Test
    void deveLancarDonoRestaurenteRequiredExceptionQuandoDtoForNulo() {
        CriarRestauranteDto dto = new CriarRestauranteDto();
        dto.setNome("Restaurante Exemplo");
        dto.setTipoRestaurante(TipoRestaurante.RESTAURANTE);
        dto.setEndereco(Endereco.builder()
                .logradouro("teste").numero("123").complemento("teste").cidade("teste").cep("12345-678").build());
        dto.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9, 0))
                .fechamento(LocalTime.of(18, 0)).build());
        try {
            criarRestauranteUseCase.criarRestaurante(dto);
        } catch (Exception e) {
            assertEquals(DonoRestaurenteRequiredException.class, e.getClass());
        }
    }

    @Test
    void deveLancarHorarioAberturaRequiredExceptionQuandoDtoForNulo() {
        CriarRestauranteDto dto = new CriarRestauranteDto();
        dto.setNome("Restaurante Exemplo");
        dto.setTipoRestaurante(TipoRestaurante.RESTAURANTE);
        dto.setDonoRestaurante("Dono Exemplo");
        dto.setEndereco(Endereco.builder()
                .logradouro("teste").numero("123").complemento("teste").cidade("teste").cep("12345-678").build());
        dto.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .fechamento(LocalTime.of(18, 0)).build());
        try {
            criarRestauranteUseCase.criarRestaurante(dto);
        } catch (Exception e) {
            assertEquals(HorarioAberturaRequiredException.class, e.getClass());
        }
    }


    @Test
    void deveLancarHorarioFechamentoRequiredExceptionQuandoDtoForNulo() {
        CriarRestauranteDto dto = new CriarRestauranteDto();
        dto.setNome("Restaurante Exemplo");
        dto.setTipoRestaurante(TipoRestaurante.RESTAURANTE);
        dto.setDonoRestaurante("Dono Exemplo");
        dto.setEndereco(Endereco.builder()
                .logradouro("teste").numero("123").complemento("teste").cidade("teste").cep("12345-678").build());
        dto.setHorarioFuncionamento(HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9, 0)).build());
        try {
            criarRestauranteUseCase.criarRestaurante(dto);
        } catch (Exception e) {
            assertEquals(HorarioFechamentoRequiredException.class, e.getClass());
        }
    }

}