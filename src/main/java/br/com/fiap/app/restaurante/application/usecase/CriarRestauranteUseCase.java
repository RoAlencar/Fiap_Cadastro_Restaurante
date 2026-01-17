package br.com.fiap.app.restaurante.application.usecase;

import br.com.fiap.app.restaurante.application.dto.request.CriarRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.CriarRestauranteResponse;
import br.com.fiap.app.restaurante.application.port.CriarRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.RestauranteRepositoryPort;
import br.com.fiap.app.restaurante.domain.Endereco;
import br.com.fiap.app.restaurante.domain.HorarioFuncionamento;
import br.com.fiap.app.restaurante.domain.Restaurante;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.DonoRestaurenteRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioAberturaRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioFechamentoRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RequestRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.TipoRestauranteRequiredException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarRestauranteUseCase implements CriarRestauranteUseCasePort {

    private final RestauranteRepositoryPort restauranteRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public CriarRestauranteResponse criarRestaurante(CriarRestauranteDto dto) throws RequestRequiredException, AddressRequiredException, HorarioAberturaRequiredException, DonoRestaurenteRequiredException, TipoRestauranteRequiredException, NameRequiredException, HorarioFechamentoRequiredException {

        validarRestaurante(dto);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setCep(dto.getEndereco().getCep());

        HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento();
        horarioFuncionamento.setAbertura(dto.getHorarioFuncionamento().getAbertura());
        horarioFuncionamento.setFechamento(dto.getHorarioFuncionamento().getFechamento());

        Restaurante restaurante = Restaurante.builder()
                .nome(dto.getNome())
                .donoRestaurante(dto.getDonoRestaurante())
                .endereco(endereco)
                .horarioFuncionamento(horarioFuncionamento)
                .tipoRestaurante(dto.getTipoRestaurante())
                .build();

        return modelMapper.map(restauranteRepositoryPort.save(restaurante), CriarRestauranteResponse.class);
    }

    private void validarRestaurante(CriarRestauranteDto dto) throws RequestRequiredException, NameRequiredException, DonoRestaurenteRequiredException,
            AddressRequiredException, TipoRestauranteRequiredException, HorarioAberturaRequiredException, HorarioFechamentoRequiredException {

        if(dto == null){
            throw new RequestRequiredException();
        }

        if(dto.getNome() == null){
            throw new NameRequiredException();
        }

        if(dto.getDonoRestaurante() == null){
            throw new DonoRestaurenteRequiredException();
        }

        if(dto.getEndereco() == null){
            throw new AddressRequiredException();
        }

        if(dto.getTipoRestaurante() == null){
            throw new TipoRestauranteRequiredException();
        }

        if(dto.getHorarioFuncionamento().getAbertura() == null){
            throw new HorarioAberturaRequiredException();
        }

        if(dto.getHorarioFuncionamento().getFechamento() == null){
            throw new HorarioFechamentoRequiredException();
        }
    }
}
