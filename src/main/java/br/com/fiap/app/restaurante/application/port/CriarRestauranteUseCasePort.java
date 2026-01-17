package br.com.fiap.app.restaurante.application.port;

import br.com.fiap.app.restaurante.application.dto.request.CriarRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.CriarRestauranteResponse;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.DonoRestaurenteRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioAberturaRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioFechamentoRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RequestRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.TipoRestauranteRequiredException;

public interface CriarRestauranteUseCasePort {

     CriarRestauranteResponse criarRestaurante(CriarRestauranteDto dto) throws AddressRequiredException, NameRequiredException, TipoRestauranteRequiredException, RequestRequiredException, HorarioAberturaRequiredException, DonoRestaurenteRequiredException, HorarioFechamentoRequiredException;
}
