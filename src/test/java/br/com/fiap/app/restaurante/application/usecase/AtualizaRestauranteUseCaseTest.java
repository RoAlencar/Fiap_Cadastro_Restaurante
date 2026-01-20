package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.dto.request.AtualizaRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.AtualizaRestauranteResponse;
import br.com.fiap.app.restaurante.application.dto.response.RestauranteBaseResponse;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.domain.enums.TipoRestaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtualizaRestauranteUseCaseTest {

    @Mock
    private RestauranteRepositoryPort restauranteRepositoryPort;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AtualizaRestauranteUseCase useCase;

    private Restaurante restauranteExistente;
    private AtualizaRestauranteDto dto;
    private AtualizaRestauranteResponse responseMock;

    @BeforeEach
    void setup() {

        Endereco endereco = Endereco.builder()
                .logradouro("Rua A")
                .numero("123")
                .cidade("SP")
                .cep("00000-000")
                .build();

        HorarioFuncionamento horarioFuncionamento = HorarioFuncionamento.builder()
                .abertura(LocalTime.of(9, 0))
                .fechamento(LocalTime.of(18, 0)).build();

        restauranteExistente = Restaurante.builder()
                .id(1L)
                .nome("Restaurante Antigo")
                .donoRestaurante("João")
                .tipoRestaurante(TipoRestaurante.BAR)
                .endereco(endereco)
                .horarioFuncionamento(horarioFuncionamento)
                .build();
    }

    @Test
    void deveAtualizarRestauranteComSucesso() throws NoChangesDetectedException, RestauranteNotFoundException {
        // Arrange
        dto = AtualizaRestauranteDto.builder()
                .id(1L)
                .nome("Restaurante Novo")
                .donoRestaurante("Maria")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .endereco(restauranteExistente.getEndereco())
                .horarioFuncionamento(restauranteExistente.getHorarioFuncionamento())
                .build();

        Restaurante restauranteAtualizado = Restaurante.builder()
                .id(1L)
                .nome("Restaurante Novo")
                .donoRestaurante("Maria")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .endereco(restauranteExistente.getEndereco())
                .horarioFuncionamento(restauranteExistente.getHorarioFuncionamento())
                .build();

        RestauranteBaseResponse baseResponse = RestauranteBaseResponse.builder()
                .id(1L)
                .nome("Restaurante Novo")
                .endereco(restauranteExistente.getEndereco())
                .horarioFuncionamento(restauranteExistente.getHorarioFuncionamento())
                .donoRestaurante("Maria")
                .tipoRestaurante(TipoRestaurante.RESTAURANTE)
                .build();

        responseMock = new AtualizaRestauranteResponse(baseResponse);

        when(restauranteRepositoryPort.findById(1L)).thenReturn(Optional.of(restauranteExistente));
        when(restauranteRepositoryPort.save(any(Restaurante.class))).thenReturn(restauranteAtualizado);
        when(modelMapper.map(any(Restaurante.class), any())).thenReturn(responseMock);

        // Act
        AtualizaRestauranteResponse response = useCase.atualizaRestaurante(dto);

        // Assert
        assertNotNull(response);
        assertEquals("Restaurante Novo", response.getNome());
        verify(restauranteRepositoryPort).save(any(Restaurante.class));
    }


    @Test
    void deveLancarExcecaoQuandoNaoHaAlteracoes() {
        dto = AtualizaRestauranteDto.builder()
                .id(1L)
                .nome(restauranteExistente.getNome())
                .donoRestaurante(restauranteExistente.getDonoRestaurante())
                .tipoRestaurante(restauranteExistente.getTipoRestaurante())
                .endereco(restauranteExistente.getEndereco())
                .horarioFuncionamento(restauranteExistente.getHorarioFuncionamento())
                .build();

        when(restauranteRepositoryPort.findById(1L)).thenReturn(Optional.of(restauranteExistente));

        assertThrows(
               NoChangesDetectedException.class,
                () -> useCase.atualizaRestaurante(dto)
        );
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        dto = AtualizaRestauranteDto.builder().id(99L).build();
        when(restauranteRepositoryPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
                RestauranteNotFoundException.class,
                () -> useCase.atualizaRestaurante(dto)
        );
    }

}
