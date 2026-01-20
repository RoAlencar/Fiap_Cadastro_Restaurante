package br.com.fiap.app.restaurante.adapter.in.web;

import br.com.fiap.app.restaurante.application.dto.request.AtualizaRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.request.CriarRestauranteDto;
import br.com.fiap.app.restaurante.application.dto.response.AtualizaRestauranteResponse;
import br.com.fiap.app.restaurante.application.dto.response.BuscaRestauranteResponse;
import br.com.fiap.app.restaurante.application.dto.response.CriarRestauranteResponse;
import br.com.fiap.app.restaurante.application.port.AtualizaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.BuscaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.CriarRestauranteUseCasePort;
import br.com.fiap.app.restaurante.application.port.DeletaRestauranteUseCasePort;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.DonoRestaurenteRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioAberturaRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.HorarioFechamentoRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RequestRequiredException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.RestauranteNotFoundException;
import br.com.fiap.app.restaurante.infrastructure.exception.custom.TipoRestauranteRequiredException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("app/v1/restaurantes")
@RequiredArgsConstructor
public class RegistroRestauranteController {

    private final CriarRestauranteUseCasePort criarRestauranteUseCase;
    private final AtualizaRestauranteUseCasePort atualizaRestauranteUseCase;
    private final BuscaRestauranteUseCasePort buscaRestauranteUseCase;
    private final DeletaRestauranteUseCasePort deletaRestauranteUseCase;

    @GetMapping
    public ResponseEntity<List<BuscaRestauranteResponse>> buscaTodosRestaurantes() {
        log.info("[Restaurante - Busca todos os restaurantes] Iniciando Processo");
        return new ResponseEntity<>(buscaRestauranteUseCase.buscaTodosRestaurantes(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<BuscaRestauranteResponse>> buscaRestaurantePorNome(@PathVariable String name)
            throws RestauranteNotFoundException {
        log.info("[Restaurante - Busca por Nome do Restaurante] Iniciando Processo");
        List<BuscaRestauranteResponse> buscaRestauranteResponseDto = buscaRestauranteUseCase.buscaRestaurantePorNome(name);
        return new ResponseEntity<>(buscaRestauranteResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CriarRestauranteResponse> criarRestaurante(@RequestBody CriarRestauranteDto dto) throws RequestRequiredException,
            AddressRequiredException, HorarioAberturaRequiredException, DonoRestaurenteRequiredException, TipoRestauranteRequiredException,
            NameRequiredException, HorarioFechamentoRequiredException {
        log.info("[Restaurante - Criar Restaurante] Iniciando Processo");
        return new ResponseEntity<>(criarRestauranteUseCase.criarRestaurante(dto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AtualizaRestauranteResponse> atualizaRestaurante(@Valid @RequestBody AtualizaRestauranteDto dto) throws NoChangesDetectedException, RestauranteNotFoundException {
        log.info("[Restaurante - Atualizar Restaurante] Iniciando processo.");
        AtualizaRestauranteResponse response = atualizaRestauranteUseCase.atualizaRestaurante(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaRestaurante(@PathVariable Long id) throws RestauranteNotFoundException {
        log.info("[Restaurante - Deletar Restaurante] Iniciando processo.");
        deletaRestauranteUseCase.deletaRestaurante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
