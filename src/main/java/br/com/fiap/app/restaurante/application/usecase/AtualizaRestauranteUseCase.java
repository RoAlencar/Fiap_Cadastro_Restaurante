package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.dto.request.AtualizaRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.AtualizaRestauranteResponse;
import br.com.fiap.app.restaurante.application.port.AtualizaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AtualizaRestauranteUseCase implements AtualizaRestauranteUseCasePort {

    private final RestauranteRepositoryPort restauranteRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public AtualizaRestauranteResponse atualizaRestaurante(AtualizaRestauranteDto dto) throws RestauranteNotFoundException, NoChangesDetectedException {

        Restaurante restauranteExistente = restauranteRepositoryPort.findById(dto.getId())
                .orElseThrow(RestauranteNotFoundException::new);

        Endereco enderecoAtualizado = convertEnderecoDtoToEndereco(dto.getEndereco());

        if(isUnchanged(dto, restauranteExistente, enderecoAtualizado)){
            throw new NoChangesDetectedException();
        }

        try {
            restauranteExistente.setNome(dto.getNome());
            restauranteExistente.setDonoRestaurante(dto.getDonoRestaurante());
            restauranteExistente.setTipoRestaurante(dto.getTipoRestaurante());
            restauranteExistente.setHorarioFuncionamento(dto.getHorarioFuncionamento());
            restauranteExistente.setEndereco(enderecoAtualizado);

            Restaurante restauranteAtualizado = restauranteRepositoryPort.save(restauranteExistente);
            return modelMapper.map(restauranteAtualizado, AtualizaRestauranteResponse.class);
        } catch (Exception ex) {
            throw new RestauranteNotFoundException();
        }
    }


    private boolean isUnchanged(AtualizaRestauranteDto dto, Restaurante restaurante, Endereco enderecoAtualizado) {
        return Objects.equals(dto.getNome(), restaurante.getNome()) &&
                Objects.equals(dto.getDonoRestaurante(), restaurante.getDonoRestaurante()) &&
                Objects.equals(dto.getTipoRestaurante(), restaurante.getTipoRestaurante()) &&
                Objects.equals(dto.getHorarioFuncionamento(), restaurante.getHorarioFuncionamento()) &&
                Objects.equals(enderecoAtualizado, restaurante.getEndereco());
    }

    private Endereco convertEnderecoDtoToEndereco(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return Endereco.builder()
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .cep(endereco.getCep())
                .build();
    }
}
